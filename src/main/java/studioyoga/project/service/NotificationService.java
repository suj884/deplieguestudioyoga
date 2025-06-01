package studioyoga.project.service;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import studioyoga.project.model.User;
import studioyoga.project.model.Classes;

/**
 * Servicio para el envío de notificaciones por correo electrónico relacionadas
 * con reservas de clases.
 * <p>
 * Permite enviar correos de confirmación y cancelación de reservas a los
 * usuarios.
 */
@Service
public class NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Envía un correo electrónico de confirmación de reserva al usuario.
     *
     * @param user  Usuario que ha realizado la reserva.
     * @param clase Clase reservada.
     */
    public void sendReservationEmail(User user, Classes clase) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM yyyy", new Locale("es"));
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("Reserva confirmada en Studio Yoga");
        email.setText("¡Hola " + user.getName() + "!\n\n" +
                "Has reservado la clase: " + clase.getTitle() +
                "\nFecha: " + clase.getEventDate().format(formatter) +
                "\nHora: " + clase.getTimeInit() + " - " + clase.getTimeEnd() +
                "\n\n¡Te esperamos!\nStudio Yoga");
        mailSender.send(email);
    }

    /**
     * Envía un correo electrónico de notificación de cancelación de reserva al
     * usuario.
     *
     * @param user  Usuario que ha cancelado la reserva.
     * @param clase Clase cuya reserva ha sido cancelada.
     */
    public void sendCancelReservationEmail(User user, Classes clase) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM yyyy", new Locale("es"));
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("Reserva cancelada en Studio Yoga");
        email.setText("Hola " + user.getName() + ",\n\n" +
                "Has cancelado tu reserva para la clase: " + clase.getTitle() +
                "\nFecha: " + clase.getEventDate().format(formatter) +
                "\nHora: " + clase.getTimeInit() + " - " + clase.getTimeEnd() +
                "\n\nEsperamos verte pronto.\nStudio Yoga");
        mailSender.send(email);
    }
}
