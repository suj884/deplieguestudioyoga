package studioyoga.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import studioyoga.project.exception.AlreadyReservedException;
import studioyoga.project.exception.NoSpotsAvailableException;
import studioyoga.project.model.Classes;
import studioyoga.project.model.Reservation;
import studioyoga.project.model.User;
import studioyoga.project.repository.ClassRepository;
import studioyoga.project.repository.ReservationRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceTest {

    private ReservationRepository reservationRepository;
    private ClassRepository classRepository;
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        reservationRepository = mock(ReservationRepository.class);
        classRepository = mock(ClassRepository.class);
        reservationService = new ReservationService();
        // Inyectar los mocks usando reflexión
        var fields = ReservationService.class.getDeclaredFields();
        for (var field : fields) {
            field.setAccessible(true);
            try {
                if (field.getType().equals(ReservationRepository.class)) {
                    field.set(reservationService, reservationRepository);
                } else if (field.getType().equals(ClassRepository.class)) {
                    field.set(reservationService, classRepository);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    void findById_returnsOptional() {
        Reservation reservation = new Reservation();
        when(reservationRepository.findById(1)).thenReturn(Optional.of(reservation));
        Optional<Reservation> result = reservationService.findById(1);
        assertThat(result).contains(reservation);
    }

    @Test
    void save_callsRepositorySave() {
        Reservation reservation = new Reservation();
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        Reservation result = reservationService.save(reservation);
        assertThat(result).isEqualTo(reservation);
    }

    @Test
    void deleteById_callsRepositoryDeleteById() {
        reservationService.deleteById(5);
        verify(reservationRepository, times(1)).deleteById(5);
    }

    @Test
    void findByClassId_returnsList() {
        List<Reservation> list = List.of(new Reservation());
        when(reservationRepository.findByClassesId(2)).thenReturn(list);
        List<Reservation> result = reservationService.findByClassId(2);
        assertThat(result).isEqualTo(list);
    }

    @Test
    void findByUser_returnsList() {
        User user = new User();
        List<Reservation> list = List.of(new Reservation());
        when(reservationRepository.findByUser(user)).thenReturn(list);
        List<Reservation> result = reservationService.findByUser(user);
        assertThat(result).isEqualTo(list);
    }

    @Test
    void countByClassId_returnsCount() {
        when(reservationRepository.findByClassesId(3)).thenReturn(List.of(new Reservation(), new Reservation()));
        int count = reservationService.countByClassId(3);
        assertThat(count).isEqualTo(2);
    }

    @Test
    void toggleActive_changesActiveAndSaves() {
        Reservation reservation = new Reservation();
        reservation.setActive(false);
        when(reservationRepository.findById(1)).thenReturn(Optional.of(reservation));
        when(reservationRepository.save(any())).thenReturn(reservation);

        reservationService.toggleActive(1);

        assertThat(reservation.isActive()).isTrue();
        verify(reservationRepository).save(reservation);
    }

    @Test
    void updateReservation_modifiesClassAndSaves() {
        User user = new User();
        user.setId(1);
        Classes oldClass = new Classes();
        oldClass.setId(10);
        Classes newClass = new Classes();
        newClass.setId(20);
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setClasses(oldClass);

        when(reservationRepository.findById(100)).thenReturn(Optional.of(reservation));
        when(classRepository.findById(20)).thenReturn(Optional.of(newClass));
        when(reservationRepository.save(any())).thenReturn(reservation);

        reservationService.updateReservation(user, 100, 20);

        assertThat(reservation.getClasses()).isEqualTo(newClass);
        verify(reservationRepository).save(reservation);
    }

    @Test
    void findByIdAndUser_returnsReservation() {
        User user = new User();
        Reservation reservation = new Reservation();
        when(reservationRepository.findByIdAndUser(1, user)).thenReturn(Optional.of(reservation));
        Reservation result = reservationService.findByIdAndUser(1, user);
        assertThat(result).isEqualTo(reservation);
    }

    @Test
    void findAll_returnsAllReservations() {
        List<Reservation> list = List.of(new Reservation());
        when(reservationRepository.findAll()).thenReturn(list);
        List<Reservation> result = reservationService.findAll();
        assertThat(result).isEqualTo(list);
    }

    @Test
    void deleteByUserId_callsRepositoryDeleteByUserId() {
        reservationService.deleteByUserId(7);
        verify(reservationRepository).deleteByUserId(7);
    }

    @Test
    void reserveClass_successfulReservation() throws Exception {
        User user = new User();
        user.setId(1);
        Classes yogaClass = new Classes();
        yogaClass.setId(2);
        yogaClass.setCapacity(2);

        when(classRepository.findById(2)).thenReturn(Optional.of(yogaClass));
        when(reservationRepository.findByClassesId(2)).thenReturn(List.of());
        when(reservationRepository.findByUser(user)).thenReturn(List.of());
        when(reservationRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Classes result = reservationService.reserveClass(user, 2);
        assertThat(result).isEqualTo(yogaClass);
        verify(reservationRepository).save(any());
    }

    @Test
    void reserveClass_throwsNoSpotsAvailableException() {
        User user = new User();
        user.setId(1);
        Classes yogaClass = new Classes();
        yogaClass.setId(2);
        yogaClass.setCapacity(1);

        when(classRepository.findById(2)).thenReturn(Optional.of(yogaClass));
        when(reservationRepository.findByClassesId(2)).thenReturn(List.of(new Reservation()));

        assertThatThrownBy(() -> reservationService.reserveClass(user, 2))
            .isInstanceOf(NoSpotsAvailableException.class);
    }

    @Test
    void reserveClass_throwsAlreadyReservedException() {
        User user = new User();
        user.setId(1);
        Classes yogaClass = new Classes();
        yogaClass.setId(2);
        yogaClass.setCapacity(2);

        Reservation existingReservation = new Reservation();
        existingReservation.setUser(user);
        Classes reservedClass = new Classes();
        reservedClass.setId(2);
        existingReservation.setClasses(reservedClass);

        when(classRepository.findById(2)).thenReturn(Optional.of(yogaClass));
        when(reservationRepository.findByClassesId(2)).thenReturn(List.of());
        when(reservationRepository.findByUser(user)).thenReturn(List.of(existingReservation));

        assertThatThrownBy(() -> reservationService.reserveClass(user, 2))
            .isInstanceOf(AlreadyReservedException.class);
    }

    @Test
    void cancelReservation_successfulCancel() {
        User user = new User();
        user.setId(1);
        Classes yogaClass = new Classes();
        yogaClass.setId(2);
        Reservation reservation = new Reservation();
        reservation.setId(10);
        reservation.setUser(user);
        reservation.setClasses(yogaClass);

        when(reservationRepository.findById(10)).thenReturn(Optional.of(reservation));

        Classes result = reservationService.cancelReservation(user, 10);

        assertThat(result).isEqualTo(yogaClass);
        verify(reservationRepository).delete(reservation);
    }

    @Test
    void findAllPageable_returnsPage() {
        Page<Reservation> page = new PageImpl<>(List.of(new Reservation()));
        Pageable pageable = mock(Pageable.class);
        when(reservationRepository.findAll(pageable)).thenReturn(page);

        Page<Reservation> result = reservationService.findAll(pageable);
        assertThat(result).isEqualTo(page);
    }

    @Test
    void findByUserOrClassPageable_returnsPage() {
        Page<Reservation> page = new PageImpl<>(List.of(new Reservation()));
        Pageable pageable = mock(Pageable.class);
        when(reservationRepository.findByUserNameContainingIgnoreCaseAndClassesTitleContainingIgnoreCase("u", "c", pageable)).thenReturn(page);

        Page<Reservation> result = reservationService.findByUserOrClass("u", "c", pageable);
        assertThat(result).isEqualTo(page);
    }
}
