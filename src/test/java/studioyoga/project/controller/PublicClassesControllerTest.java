package studioyoga.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import studioyoga.project.dto.ClassesDTO;
import studioyoga.project.exception.AlreadyReservedException;
import studioyoga.project.exception.NoSpotsAvailableException;
import studioyoga.project.model.Classes;
import studioyoga.project.model.User;
import studioyoga.project.service.ClassesService;
import studioyoga.project.service.NotificationService;
import studioyoga.project.service.ReservationService;
import studioyoga.project.service.UserService;
import studioyoga.project.model.Reservation;
import java.security.Principal;
import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class PublicClassesControllerTest {

    @Mock
    private ClassesService classesService;
    @Mock
    private UserService userService;
    @Mock
    private ReservationService reservationService;
    @Mock
    private NotificationService notificationService;
    @Mock
    private Model model;
    @Mock
    private Principal principal;
    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private PublicClassesController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void showClasses_authenticatedUser_addsAttributesAndReturnsView() {
        ClassesDTO dto = mock(ClassesDTO.class);
        Classes classes = mock(Classes.class);
        when(dto.getClasses()).thenReturn(classes);
        when(classes.getEventDate()).thenReturn(LocalDate.now());
        when(classes.getTimeInit()).thenReturn(java.time.LocalTime.of(9, 0));
        when(classesService.findUpcomingClassesWithSpots()).thenReturn(List.of(dto));
        when(principal.getName()).thenReturn("test@mail.com");
        User user = new User();
        when(userService.findByEmail("test@mail.com")).thenReturn(user);
        when(reservationService.findByUser(user)).thenReturn(List.of());

        String view = controller.showClasses(model, principal);

        verify(model).addAttribute(eq("clasesPorFecha"), any());
        verify(model).addAttribute(eq("idsReservados"), any());
        assertThat(view).isEqualTo("user/classes");
    }

    @Test
    void showClasses_anonymousUser_addsEmptyIdsReservados() {
        ClassesDTO dto = mock(ClassesDTO.class);
        Classes classes = mock(Classes.class);
        when(dto.getClasses()).thenReturn(classes);
        when(classes.getEventDate()).thenReturn(LocalDate.now());
        when(classes.getTimeInit()).thenReturn(java.time.LocalTime.of(9, 0));
        when(classesService.findUpcomingClassesWithSpots()).thenReturn(List.of(dto));

        String view = controller.showClasses(model, null);

        verify(model).addAttribute(eq("clasesPorFecha"), any());
        verify(model).addAttribute(eq("idsReservados"), eq(List.of()));
        assertThat(view).isEqualTo("user/classes");
    }

    @Test
    void reserveClass_successfulReservation() throws Exception {
        when(principal.getName()).thenReturn("test@mail.com");
        User user = new User();
        Classes clase = new Classes();
        when(userService.findByEmail("test@mail.com")).thenReturn(user);
        when(reservationService.reserveClass(user, 1)).thenReturn(clase);

        String view = controller.reserveClass(1, principal, redirectAttributes);

        verify(reservationService).reserveClass(user, 1);
        verify(notificationService).sendReservationEmail(user, clase);
        verify(redirectAttributes).addFlashAttribute(eq("success"), anyString());
        assertThat(view).isEqualTo("redirect:/classes");
    }

    @Test
    void reserveClass_noSpotsAvailable() throws Exception {
        when(principal.getName()).thenReturn("test@mail.com");
        User user = new User();
        when(userService.findByEmail("test@mail.com")).thenReturn(user);
        doThrow(new NoSpotsAvailableException()).when(reservationService).reserveClass(user, 1);

        String view = controller.reserveClass(1, principal, redirectAttributes);

        verify(redirectAttributes).addFlashAttribute(eq("error"), contains("plazas"));
        assertThat(view).isEqualTo("redirect:/classes");
    }

    @Test
    void reserveClass_alreadyReserved() throws Exception {
        when(principal.getName()).thenReturn("test@mail.com");
        User user = new User();
        when(userService.findByEmail("test@mail.com")).thenReturn(user);
        doThrow(new AlreadyReservedException()).when(reservationService).reserveClass(user, 1);

        String view = controller.reserveClass(1, principal, redirectAttributes);

        verify(redirectAttributes).addFlashAttribute(eq("error"), contains("Ya has reservado"));
        assertThat(view).isEqualTo("redirect:/classes");
    }

    @Test
    void myReservations_authenticatedUser() {
        when(principal.getName()).thenReturn("test@mail.com");
        User user = new User();
        when(userService.findByEmail("test@mail.com")).thenReturn(user);
        when(reservationService.findByUser(user)).thenReturn(List.of());

        String view = controller.myReservations(model, principal);

        verify(model).addAttribute(eq("reservations"), any());
        assertThat(view).isEqualTo("user/my-reservations");
    }

    @Test
    void myReservations_anonymousUser_redirectsToLogin() {
        String view = controller.myReservations(model, null);
        assertThat(view).isEqualTo("redirect:/login");
    }

    @Test
    void cancelReservation_authenticatedUser_success() throws Exception {
        when(principal.getName()).thenReturn("test@mail.com");
        User user = new User();
        Classes clase = new Classes();
        when(userService.findByEmail("test@mail.com")).thenReturn(user);
        when(reservationService.cancelReservation(user, 1)).thenReturn(clase);

        String view = controller.cancelReservation(1, principal, redirectAttributes);

        verify(reservationService).cancelReservation(user, 1);
        verify(notificationService).sendCancelReservationEmail(user, clase);
        verify(redirectAttributes).addFlashAttribute(eq("success"), anyString());
        assertThat(view).isEqualTo("redirect:/classes/my-reservations");
    }

    @Test
    void cancelReservation_authenticatedUser_exception() throws Exception {
        when(principal.getName()).thenReturn("test@mail.com");
        User user = new User();
        when(userService.findByEmail("test@mail.com")).thenReturn(user);
        doThrow(new RuntimeException()).when(reservationService).cancelReservation(user, 1);

        String view = controller.cancelReservation(1, principal, redirectAttributes);

        verify(redirectAttributes).addFlashAttribute(eq("error"), contains("No es posible"));
        assertThat(view).isEqualTo("redirect:/classes/my-reservations");
    }

    @Test
    void cancelReservation_anonymousUser_redirectsToLogin() {
        String view = controller.cancelReservation(1, null, redirectAttributes);
        assertThat(view).isEqualTo("redirect:/login");
    }

@Test
void confirmCancelReservation_authenticatedUser_found() {
    when(principal.getName()).thenReturn("test@mail.com");
    User user = new User();
    Reservation reservation = mock(Reservation.class); // <-- CORREGIDO
    when(userService.findByEmail("test@mail.com")).thenReturn(user);
    when(reservationService.findByUserAndId(user, 1)).thenReturn(reservation);

    String view = controller.confirmCancelReservation(1, principal, model);

    verify(model).addAttribute("reservation", reservation);
    assertThat(view).isEqualTo("user/confirm-cancel-reservation");
}

    @Test
    void confirmCancelReservation_authenticatedUser_notFound() {
        when(principal.getName()).thenReturn("test@mail.com");
        User user = new User();
        when(userService.findByEmail("test@mail.com")).thenReturn(user);
        when(reservationService.findByUserAndId(user, 1)).thenReturn(null);

        String view = controller.confirmCancelReservation(1, principal, model);

        assertThat(view).isEqualTo("redirect:/classes/my-reservations");
    }

    @Test
    void confirmCancelReservation_anonymousUser_redirectsToLogin() {
        String view = controller.confirmCancelReservation(1, null, model);
        assertThat(view).isEqualTo("redirect:/login");
    }
}
