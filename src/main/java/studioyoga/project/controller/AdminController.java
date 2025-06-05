package studioyoga.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import studioyoga.project.repository.BlogPostRepository;
import studioyoga.project.repository.ClassRepository;
import studioyoga.project.repository.EventRepository;
import studioyoga.project.repository.GuideSectionRepository;
import studioyoga.project.repository.ReservationRepository;
import studioyoga.project.repository.TicketRepository;
import studioyoga.project.repository.UserRepository;

/**
 * Controlador para la administración del sistema.
 * Proporciona acceso al panel de administración y muestra estadísticas
 * generales del sistema,
 * como el total de usuarios, eventos, clases, reservas, secciones de la guía y
 * publicaciones del blog.
 * <p>
 * Todas las rutas de este controlador están bajo el prefijo "/admin".
 */
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private UserRepository userRepository; // Repositorio para acceder a los usuarios

    @Autowired
    private EventRepository eventRepository; // Repositorio para acceder a los eventos

    @Autowired
    private BlogPostRepository blogPostRepository; // Repositorio para acceder a las publicaciones del blog

    @Autowired
    private ClassRepository classRepository; // Repositorio para acceder a las clases

    @Autowired
    private ReservationRepository reservationRepository; // Repositorio para acceder a las reservas

    @Autowired
    private GuideSectionRepository guideSectionRepository; // Repositorio para acceder a las secciones de la guía

    @Autowired
    private TicketRepository ticketRepository; // Repositorio para acceder a los tickets

    /**
     * Muestra el panel de administración con estadísticas generales del sistema.
     * Añade al modelo los totales de usuarios, eventos, clases, reservas, secciones
     * de la guía,
     * publicaciones del blog e incidencias (tickets).
     *
     * @param model Modelo para pasar los datos a la vista.
     * @return Vista del panel de administración.
     */
    @GetMapping("/dash-board")
    public String showAdminPanel(Model model) {
        long totalClass = classRepository.count();
        long totalEvents = eventRepository.count();
        long totalUsers = userRepository.count();
        long totalReservations = reservationRepository.count();
        long totalGuideSections = guideSectionRepository.count();
        long totalBlog = blogPostRepository.count();
        long totalTickets = ticketRepository.count();
        model.addAttribute("totalUsers", totalUsers);
        model.addAttribute("totalEvents", totalEvents);
        model.addAttribute("totalClass", totalClass);
        model.addAttribute("totalReservations", totalReservations);
        model.addAttribute("totalGuideSections", totalGuideSections);
        model.addAttribute("totalBlog", totalBlog);
        model.addAttribute("totalTickets", totalTickets);
        return "admin/dash-board";
    }


}