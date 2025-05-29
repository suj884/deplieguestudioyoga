package studioyoga.project.service;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import studioyoga.project.model.User;
import studioyoga.project.model.Classes;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender mailSender;

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
