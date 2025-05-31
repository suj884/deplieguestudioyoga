package studioyoga.project.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import studioyoga.project.model.Reservation;
import studioyoga.project.service.ClassesService;
import studioyoga.project.service.ReservationService;
import studioyoga.project.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * Controlador para la gestión de reservas en el panel de administración.
 * Permite listar, buscar, crear, editar, eliminar y activar/desactivar
 * reservas.
 */
@Controller
@RequestMapping("/admin/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ClassesService classesService;

    @Autowired
    private UserService userService;

    /**
     * Muestra la lista de reservas, permite filtrar por usuario o clase, o mostrar
     * todas.
     *
     * @param user      Nombre del usuario para filtrar (opcional).
     * @param className Nombre de la clase para filtrar (opcional).
     * @param all       Si es true, muestra todas las reservas.
     * @param model     Modelo para pasar datos a la vista.
     * @return Vista de administración de reservas.
     */
    @GetMapping("/manage-reservations")
    public String manageReservations(
            @RequestParam(required = false) String user,
            @RequestParam(required = false) String className,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Boolean all,
            Model model) {
       Pageable pageable = PageRequest.of(page, size);
    Page<Reservation> reservationsPage;

    if (Boolean.TRUE.equals(all)) {
        reservationsPage = reservationService.findAll(pageable);
    } else if ((user != null && !user.isEmpty()) || (className != null && !className.isEmpty())) {
        reservationsPage = reservationService.findByUserOrClass(user, className, pageable);
    } else {
        reservationsPage = reservationService.findAll(pageable);
    }

    model.addAttribute("reservationsPage", reservationsPage);
    model.addAttribute("user", user);
    model.addAttribute("className", className);
    model.addAttribute("page", page);
    model.addAttribute("size", size);

    if (reservationsPage.isEmpty()) {
        model.addAttribute("info", "No existen reservas con ese criterio");
    }

    return "admin/manage-reservations";
}

    /**
     * Muestra el formulario para crear una nueva reserva.
     *
     * @param model Modelo para pasar datos a la vista.
     * @return Vista del formulario de creación de reserva.
     */
    @GetMapping("/createReservation")
    public String showCreateForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("classes", classesService.findAll());
        model.addAttribute("users", userService.findAll());
        return "admin/form-reservation";
    }

    /**
     * Guarda una nueva reserva.
     *
     * @param reservation        Objeto Reservation a guardar.
     * @param redirectAttributes Atributos para mensajes flash en la redirección.
     * @return Redirección a la vista de administración de reservas.
     */
    @PostMapping("/save")
    public String saveReservation(@ModelAttribute Reservation reservation, RedirectAttributes redirectAttributes) {
        reservation.setDateReservation(LocalDateTime.now());
        reservationService.save(reservation);
        redirectAttributes.addFlashAttribute("message", "Reserva guardada correctamente");
        return "redirect:/admin/reservations/manage-reservations";
    }

    @GetMapping("/editReservation/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Optional<Reservation> optional = reservationService.findById(id);
        if (optional.isEmpty()) {
            model.addAttribute("error", "Reserva no encontrada");
            return "redirect:/admin/reservations/manage-reservations";
        }
        model.addAttribute("reservation", optional.get());
        model.addAttribute("classes", classesService.findAll());
        model.addAttribute("users", userService.findAll());
        return "admin/form-reservation";
    }

    /**
     * Elimina una reserva por su ID.
     *
     * @param id ID de la reserva a eliminar.
     * @return Redirección a la vista de administración de reservas.
     */
    @GetMapping("/deleteReservation/{id}")
    public String deleteReservation(@PathVariable Integer id) {
        reservationService.deleteById(id);
        return "redirect:/admin/manage-reservations";
    }

    /**
     * Activa o desactiva una reserva (cambia su estado activo).
     *
     * @param id ID de la reserva a activar o desactivar.
     * @return Redirección a la vista de administración de reservas.
     */
    @GetMapping("/toggleReservation/{id}")
    public String toggleActive(@PathVariable Integer id) {
        reservationService.toggleActive(id);
        return "redirect:/admin/reservations/manage-reservations";
    }

    @GetMapping("/confirm-delete/{id}")
    public String confirmDelete(@PathVariable Integer id, Model model) {
        Optional<Reservation> reservationOptional = reservationService.findById(id);
        if (!reservationOptional.isPresent()) {
            // Manejar el caso de que la reserva no exista
            model.addAttribute("error", "Reserva no encontrada");
            return "redirect:/admin/reservations/manage-reservations";
        }
        Reservation reservation = reservationOptional.get();
        model.addAttribute("message", "¿Seguro que quieres eliminar la reserva de " +
                reservation.getUser().getName() + " en la clase " +
                reservation.getClasses().getTitle() + "?");
        model.addAttribute("action", "/admin/reservations/delete/" + id);
        model.addAttribute("cancelUrl", "/admin/reservations/manage-reservations");
        return "admin/confirm-delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteReservation(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        reservationService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Reserva eliminada correctamente.");
        return "redirect:/admin/reservations/manage-reservations";
    }

}