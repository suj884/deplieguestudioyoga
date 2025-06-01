package studioyoga.project.controller;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

class ContactControllerTest {

    @InjectMocks
    private ContactController controller;

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private RedirectAttributes redirectAttributes;

    @Captor
    private ArgumentCaptor<SimpleMailMessage> mailCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShowContactForm() {
        String view = controller.showContactForm();
        assertThat(view).isEqualTo("form-contact");
    }

    @Test
    void testSendContact() {
        String nombre = "Juan";
        String email = "juan@email.com";
        String mensaje = "Hola, quiero información.";
        
        // Usamos doNothing() porque el método send es void
        doNothing().when(mailSender).send(any(SimpleMailMessage.class));
        when(redirectAttributes.addFlashAttribute(eq("success"), anyString())).thenReturn(redirectAttributes);

        String view = controller.sendContact(nombre, email, mensaje, redirectAttributes);

        // Verifica que se envió el correo
        verify(mailSender).send(mailCaptor.capture());
        SimpleMailMessage sentMail = mailCaptor.getValue();
        assertThat(sentMail.getTo()).containsExactly("studioyogasevilla@gmail.com");
        assertThat(sentMail.getSubject()).contains(nombre);
        assertThat(sentMail.getText()).contains(nombre, email, mensaje);

        // Verifica que se añadió el mensaje flash de éxito
        verify(redirectAttributes).addFlashAttribute(eq("success"), contains("Mensaje enviado correctamente"));
        assertThat(view).isEqualTo("redirect:/form-contact");
    }
}
