package studioyoga.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import studioyoga.project.model.Classes;
import studioyoga.project.model.User;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class NotificationServiceTest {

    private JavaMailSender mailSender;
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        mailSender = mock(JavaMailSender.class);
        notificationService = new NotificationService();
        // Inyectar el mock usando reflexión porque el campo es private y @Autowired
        var field = NotificationService.class.getDeclaredFields()[0];
        field.setAccessible(true);
        try {
            field.set(notificationService, mailSender);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void sendReservationEmail_sendsCorrectEmail() {
        User user = new User();
        user.setEmail("test@email.com");
        user.setName("Juan");
        Classes clase = new Classes();
        clase.setTitle("Yoga Básico");
        clase.setEventDate(LocalDate.of(2024, 6, 5));
        clase.setTimeInit(LocalTime.of(10, 0));
        clase.setTimeEnd(LocalTime.of(11, 0));

        notificationService.sendReservationEmail(user, clase);

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(captor.capture());
        SimpleMailMessage email = captor.getValue();

        assertThat(email.getTo()).contains("test@email.com");
        assertThat(email.getSubject()).contains("Reserva confirmada");
        assertThat(email.getText()).contains("Juan");
        assertThat(email.getText()).contains("Yoga Básico");
        assertThat(email.getText()).contains("10:00");
        assertThat(email.getText()).contains("11:00");
    }

    @Test
    void sendCancelReservationEmail_sendsCorrectEmail() {
        User user = new User();
        user.setEmail("test@email.com");
        user.setName("Ana");
        Classes clase = new Classes();
        clase.setTitle("Yoga Avanzado");
        clase.setEventDate(LocalDate.of(2024, 6, 6));
        clase.setTimeInit(LocalTime.of(12, 0));
        clase.setTimeEnd(LocalTime.of(13, 0));

        notificationService.sendCancelReservationEmail(user, clase);

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(captor.capture());
        SimpleMailMessage email = captor.getValue();

        assertThat(email.getTo()).contains("test@email.com");
        assertThat(email.getSubject()).contains("Reserva cancelada");
        assertThat(email.getText()).contains("Ana");
        assertThat(email.getText()).contains("Yoga Avanzado");
        assertThat(email.getText()).contains("12:00");
        assertThat(email.getText()).contains("13:00");
    }
}