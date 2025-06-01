package studioyoga.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import studioyoga.project.service.TicketService;

/**
 * Controlador para la gestión de incidencias (tickets) desde el panel de
 * administración.
 * <p>
 * Permite listar todas las incidencias y cambiar su estado.
 * Todas las rutas de este controlador están bajo el prefijo "/admin/support".
 */
@Controller
@RequestMapping("/admin/support")
public class AdminSupportController {
    private final TicketService ticketService;

    /**
     * Inyecta el servicio de tickets para la gestión de incidencias.
     *
     * @param ticketService Servicio de tickets.
     */
    public AdminSupportController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /**
     * Muestra la lista de todas las incidencias (tickets) en la vista de soporte
     * administrativo.
     *
     * @param model Modelo para pasar los datos a la vista.
     * @return Nombre de la vista de soporte administrativo.
     */
    @GetMapping
    public String listTickets(Model model) {
        model.addAttribute("tickets", ticketService.findAll());
        return "admin/support"; // admin/support.html
    }

    /**
     * Cambia el estado de una incidencia (ticket) según el identificador y el nuevo
     * estado proporcionados.
     *
     * @param id     Identificador del ticket.
     * @param estado Nuevo estado a asignar al ticket.
     * @return Redirección a la vista de soporte administrativo.
     */
    @PostMapping("/status")
    public String changeStatus(@RequestParam Long id, @RequestParam String estado) {
        ticketService.changeStatus(id, estado);
        return "redirect:/admin/support";
    }
}
