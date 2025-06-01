package studioyoga.project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import studioyoga.project.constants.RedirConstants;
import studioyoga.project.model.Event;
import studioyoga.project.service.EventService;

/**
 * Controlador para la gestión de eventos en el panel de administración.
 * <p>
 * Permite listar, crear, editar, guardar, eliminar y activar/desactivar
 * eventos.
 * Todas las rutas de este controlador están bajo el prefijo "/admin/events".
 */
@Controller
@RequestMapping("/admin/events")
public class EventsController {

    @Autowired
    private EventService eventService;

    /**
     * Muestra la lista de eventos para administración.
     *
     * @param model Modelo para pasar datos a la vista.
     * @return Vista de administración de eventos.
     */
    @GetMapping("/manage-events")
    public String manageEvents(Model model) {
        model.addAttribute("events", eventService.findAll());
        return "admin/manage-events";
    }

    /**
     * Muestra el formulario para crear un nuevo evento.
     *
     * @param model Modelo para pasar datos a la vista.
     * @return Vista del formulario de creación de evento.
     */
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("event", new Event());
        return "admin/form-events";
    }

    /**
     * Guarda un evento nuevo o editado.
     *
     * @param event              Objeto Event a guardar.
     * @param redirectAttributes Atributos para mensajes flash en la redirección.
     * @return Redirección a la vista de administración de eventos.
     */
    @PostMapping("/save")
    public String saveEvent(@ModelAttribute Event event, RedirectAttributes redirectAttributes) {
        eventService.save(event);
        redirectAttributes.addFlashAttribute("success", "Evento guardado correctamente.");
        return RedirConstants.REDIRECT_ADMIN_EVENTS;
    }

    /**
     * Muestra el formulario para editar un evento existente.
     *
     * @param id                 ID del evento a editar.
     * @param model              Modelo para pasar datos a la vista.
     * @param redirectAttributes Atributos para mensajes flash en la redirección.
     * @return Vista del formulario de edición de evento o redirección si no se
     *         encuentra.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Event> eventOpt = eventService.findById(id);
        if (!eventOpt.isPresent()) {
            redirectAttributes.addFlashAttribute("error", "Evento no encontrado");
            return RedirConstants.REDIRECT_ADMIN_EVENTS;
        }
        model.addAttribute("event", eventOpt.get());
        return "admin/form-events";
    }

    /**
     * Elimina el evento por su ID tras confirmación.
     *
     * @param id                 ID del evento a eliminar.
     * @param redirectAttributes Atributos para mensajes flash en la redirección.
     * @return Redirección a la vista de administración de eventos.
     */
    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Integer id) {
        eventService.deleteById(id);
        return "redirect:/admin/manage-events";
    }

    /**
     * Activa o desactiva un evento (cambia su estado activo).
     *
     * @param id ID del evento a activar o desactivar.
     * @return Redirección a la vista de administración de eventos.
     */
    @GetMapping("/toggle/{id}")
    public String toggleActive(@PathVariable Integer id) {
        eventService.toggleActive(id);
        return "redirect:/admin/events/manage-events";
    }

    /**
     * Muestra la vista de confirmación antes de eliminar un evento.
     *
     * @param id    ID del evento a eliminar.
     * @param model Modelo para pasar datos a la vista.
     * @return Vista de confirmación de eliminación.
     */
    @GetMapping("/confirm-delete/{id}")
    public String confirmDelete(@PathVariable Integer id, Model model) {
        Event event = eventService.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        model.addAttribute("message", "¿Seguro que quieres eliminar el evento: '" + event.getTitle() + "'?");
        model.addAttribute("action", "/admin/events/delete/" + id);
        model.addAttribute("cancelUrl", "/admin/events/manage-events");
        return "admin/confirm-delete";
    }

    /**
     * Elimina el evento por su ID tras confirmación (vía POST).
     *
     * @param id                 ID del evento a eliminar.
     * @param redirectAttributes Atributos para mensajes flash en la redirección.
     * @return Redirección a la vista de administración de eventos.
     */
    @PostMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        eventService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Evento eliminado correctamente.");
        return "redirect:/admin/events/manage-events";
    }
}