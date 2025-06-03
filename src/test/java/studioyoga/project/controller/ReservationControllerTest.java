package studioyoga.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import studioyoga.project.model.Classes;
import studioyoga.project.model.Reservation;
import studioyoga.project.model.User;
import studioyoga.project.service.ClassesService;
import studioyoga.project.service.ReservationService;
import studioyoga.project.service.UserService;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;
    @Mock
    private ClassesService classesService;
    @Mock
    private UserService userService;
    @Mock
    private Model model;
    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private ReservationController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void manageReservations_allTrue_returnsPageAndView() {
        Page<Reservation> page = new PageImpl<>(List.of(new Reservation()));
        when(reservationService.findAll(any(Pageable.class))).thenReturn(page);

        String view = controller.manageReservations(null, null, 0, 10, true, model);

        verify(model).addAttribute("reservationsPage", page);
        verify(model).addAttribute("user", null);
        verify(model).addAttribute("className", null);
        verify(model).addAttribute("page", 0);
        verify(model).addAttribute("size", 10);
        assertThat(view).isEqualTo("admin/manage-reservations");
    }

    @Test
    void manageReservations_withUserOrClass_filtersCorrectly() {
        Page<Reservation> page = new PageImpl<>(List.of(new Reservation()));
        when(reservationService.findByUserOrClass("user", null, PageRequest.of(0, 10))).thenReturn(page);

        String view = controller.manageReservations("user", null, 0, 10, null, model);

        verify(model).addAttribute("reservationsPage", page);
        assertThat(view).isEqualTo("admin/manage-reservations");
    }

    @Test
    void manageReservations_emptyPage_addsInfo() {
        Page<Reservation> page = new PageImpl<>(List.of());
        when(reservationService.findAll(any(Pageable.class))).thenReturn(page);

        String view = controller.manageReservations(null, null, 0, 10, null, model);

        verify(model).addAttribute("info", "No existen reservas con ese criterio");
        assertThat(view).isEqualTo("admin/manage-reservations");
    }

    @Test
    void showCreateForm_addsAttributesAndReturnsView() {
        when(classesService.findAll()).thenReturn(List.of(new Classes()));
        when(userService.findAll()).thenReturn(List.of(new User()));

        String view = controller.showCreateForm(model);

        verify(model).addAttribute(eq("reservation"), any(Reservation.class));
        verify(model).addAttribute(eq("classes"), any());
        verify(model).addAttribute(eq("users"), any());
        assertThat(view).isEqualTo("admin/form-reservation");
    }

    @Test
    void saveReservation_setsDateSavesAndRedirects() {
        Reservation reservation = new Reservation();
        String view = controller.saveReservation(reservation, redirectAttributes);

        assertThat(reservation.getDateReservation()).isNotNull();
        verify(reservationService).save(reservation);
        verify(redirectAttributes).addFlashAttribute(eq("message"), anyString());
        assertThat(view).isEqualTo("redirect:/admin/reservations/manage-reservations");
    }

    @Test
    void showEditForm_found_addsAttributesAndReturnsView() {
        Reservation reservation = new Reservation();
        when(reservationService.findById(1)).thenReturn(Optional.of(reservation));
        when(classesService.findAll()).thenReturn(List.of(new Classes()));
        when(userService.findAll()).thenReturn(List.of(new User()));

        String view = controller.showEditForm(1, model);

        verify(model).addAttribute("reservation", reservation);
        verify(model).addAttribute(eq("classes"), any());
        verify(model).addAttribute(eq("users"), any());
        assertThat(view).isEqualTo("admin/form-reservation");
    }

    @Test
    void showEditForm_notFound_addsErrorAndRedirects() {
        when(reservationService.findById(1)).thenReturn(Optional.empty());

        String view = controller.showEditForm(1, model);

        verify(model).addAttribute(eq("error"), anyString());
        assertThat(view).isEqualTo("redirect:/admin/reservations/manage-reservations");
    }

    @Test
    void deleteReservationByGet_deletesAndRedirects() {
        String view = controller.deleteReservation(1);
        verify(reservationService).deleteById(1);
        assertThat(view).isEqualTo("redirect:/admin/manage-reservations");
    }

    @Test
    void toggleActive_togglesAndRedirects() {
        String view = controller.toggleActive(2);
        verify(reservationService).toggleActive(2);
        assertThat(view).isEqualTo("redirect:/admin/reservations/manage-reservations");
    }

    @Test
    void confirmDelete_found_addsAttributesAndReturnsView() {
        Reservation reservation = new Reservation();
        User user = new User();
        user.setName("TestUser");
        Classes classes = new Classes();
        classes.setTitle("YogaClass");
        reservation.setUser(user);
        reservation.setClasses(classes);

        when(reservationService.findById(5)).thenReturn(Optional.of(reservation));

        String view = controller.confirmDelete(5, model);

        verify(model).addAttribute(eq("message"), contains("TestUser"));
        verify(model).addAttribute(eq("action"), eq("/admin/reservations/delete/5"));
        verify(model).addAttribute(eq("cancelUrl"), eq("/admin/reservations/manage-reservations"));
        assertThat(view).isEqualTo("admin/confirm-delete");
    }

    @Test
    void confirmDelete_notFound_addsErrorAndRedirects() {
        when(reservationService.findById(5)).thenReturn(Optional.empty());

        String view = controller.confirmDelete(5, model);

        verify(model).addAttribute(eq("error"), anyString());
        assertThat(view).isEqualTo("redirect:/admin/reservations/manage-reservations");
    }

    @Test
    void deleteReservationByPost_deletesAndRedirectsWithSuccess() {
        String view = controller.deleteReservation(7, redirectAttributes);
        verify(reservationService).deleteById(7);
        verify(redirectAttributes).addFlashAttribute(eq("success"), anyString());
        assertThat(view).isEqualTo("redirect:/admin/reservations/manage-reservations");
    }
}
