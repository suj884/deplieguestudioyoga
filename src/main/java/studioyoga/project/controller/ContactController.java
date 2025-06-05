package studioyoga.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador para la gestión del formulario de contacto.
 * <p>
 * Permite enviar mensajes de contacto por correo electrónico al administrador
 * del sitio.
 * Gestiona las rutas "/contact" (GET y POST).
 */
@Controller
public class ContactController {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Muestra el formulario de contacto para que el usuario pueda enviar un
     * mensaje.
     *
     * @return Nombre de la vista del formulario de contacto.
     */
    @GetMapping("/contact")
    public String showContactForm() {
        return "form-contact";
    }

    /**
     * Procesa el envío del formulario de contacto.
     * <p>
     * Envía un correo electrónico al administrador con los datos del mensaje
     * recibido
     * y, adicionalmente, envía un correo de confirmación al usuario que ha
     * rellenado el formulario.
     * Añade un mensaje flash de éxito para mostrar al usuario tras la redirección.
     *
     * @param nombre             Nombre del remitente introducido en el formulario.
     * @param email              Correo electrónico del remitente.
     * @param mensaje            Mensaje enviado por el usuario.
     * @param redirectAttributes Atributos para mensajes flash en la redirección.
     * @return Redirección a la página de contacto con mensaje de éxito.
     */
    @PostMapping("/contact")
    public String sendContact(
            @RequestParam String nombre,
            @RequestParam String email,
            @RequestParam String mensaje,
            RedirectAttributes redirectAttributes) {

        // Correo al administrador
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("studioyogasevilla@gmail.com");
        mailMessage.setSubject("Nuevo mensaje de contacto de " + nombre);
        mailMessage.setText("Nombre: " + nombre + "\nEmail: " + email + "\n\nMensaje:\n" + mensaje);
        mailSender.send(mailMessage);

        // Correo de confirmación al usuario
        SimpleMailMessage confirmationMessage = new SimpleMailMessage();
        confirmationMessage.setTo(email);
        confirmationMessage.setSubject("Confirmación de contacto - Studio Yoga");
        confirmationMessage.setText(
                "Hola " + nombre + ",\n\n" +
                        "Hemos recibido tu mensaje y te responderemos lo antes posible.\n\n" +
                        "Este es un resumen de tu mensaje:\n" +
                        mensaje + "\n\n" +
                        "Gracias por ponerte en contacto con Studio Yoga.\n\n" +
                        "Un saludo,\nEl equipo de Studio Yoga");
        mailSender.send(confirmationMessage);

        redirectAttributes.addFlashAttribute("success",
                "¡Mensaje enviado correctamente! Nos pondremos en contacto contigo pronto.");
        return "redirect:/form-contact";
    }

}