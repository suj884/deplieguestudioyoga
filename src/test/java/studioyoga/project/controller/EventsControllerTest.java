package studioyoga.project.controller;

import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import studioyoga.project.model.Event;
import studioyoga.project.service.EventService;

class EventsControllerTest {

    @Mock
    private EventService eventService;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private EventsController eventsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        MockMvcBuilders.standaloneSetup(eventsController).build();
    }

    @Test
    void testManageEvents() {
        when(eventService.findAll()).thenReturn(Arrays.asList(new Event(), new Event()));
        String view = eventsController.manageEvents(model);
        verify(model).addAttribute(eq("events"), any());
        assert view.equals("admin/manage-events");
    }

    @Test
    void testShowCreateForm() {
        String view = eventsController.showCreateForm(model);
        verify(model).addAttribute(eq("event"), any(Event.class));
        assert view.equals("admin/form-events");
    }

    @Test
    void testSaveEvent() {
        Event event = new Event();
        String view = eventsController.saveEvent(event, redirectAttributes);
        verify(eventService).save(event);
        verify(redirectAttributes).addFlashAttribute(eq("success"), anyString());
        assert view.equals("redirect:/admin/events/manage-events");
    }

    @Test
    void testShowEditForm_Found() {
        Event event = new Event();
        when(eventService.findById(1)).thenReturn(Optional.of(event));
        String view = eventsController.showEditForm(1, model, redirectAttributes);
        verify(model).addAttribute("event", event);
        assert view.equals("admin/form-events");
    }

    @Test
    void testShowEditForm_NotFound() {
        when(eventService.findById(1)).thenReturn(Optional.empty());
        String view = eventsController.showEditForm(1, model, redirectAttributes);
        verify(redirectAttributes).addFlashAttribute(eq("error"), anyString());
        assert view.equals("redirect:/admin/events/manage-events");
    }

    @Test
    void testDeleteEventByGet() {
        String view = eventsController.deleteEvent(1);
        verify(eventService).deleteById(1);
        assert view.equals("redirect:/admin/manage-events");
    }

    @Test
    void testToggleActive() {
        String view = eventsController.toggleActive(1);
        verify(eventService).toggleActive(1);
        assert view.equals("redirect:/admin/events/manage-events");
    }

    @Test
    void testConfirmDelete() {
        Event event = new Event();
        event.setTitle("Yoga Fest");
        when(eventService.findById(1)).thenReturn(Optional.of(event));
        String view = eventsController.confirmDelete(1, model);
        verify(model).addAttribute(eq("message"), contains("Yoga Fest"));
        verify(model).addAttribute(eq("action"), eq("/admin/events/delete/1"));
        verify(model).addAttribute(eq("cancelUrl"), eq("/admin/events/manage-events"));
        assert view.equals("admin/confirm-delete");
    }

    @Test
    void testDeleteEventByPost() {
        String view = eventsController.deleteEvent(1, redirectAttributes);
        verify(eventService).deleteById(1);
        verify(redirectAttributes).addFlashAttribute(eq("success"), anyString());
        assert view.equals("redirect:/admin/events/manage-events");
    }
}
