package studioyoga.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import studioyoga.project.dto.ClassesDTO;
import studioyoga.project.model.Classes;
import studioyoga.project.repository.ClassRepository;
import studioyoga.project.repository.ReservationRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ClassesServiceTest {

    private ClassRepository classRepository;
    private ReservationService reservationService;
    private ReservationRepository reservationRepository;
    private ClassesService classesService;

    @BeforeEach
    void setUp() {
        classRepository = mock(ClassRepository.class);
        reservationService = mock(ReservationService.class);
        reservationRepository = mock(ReservationRepository.class);
        classesService = new ClassesService();

        // Inyectar los mocks usando reflexión
        Arrays.stream(ClassesService.class.getDeclaredFields()).forEach(field -> {
            field.setAccessible(true);
            try {
                if (field.getType().equals(ClassRepository.class)) {
                    field.set(classesService, classRepository);
                } else if (field.getType().equals(ReservationService.class)) {
                    field.set(classesService, reservationService);
                } else if (field.getType().equals(ReservationRepository.class)) {
                    field.set(classesService, reservationRepository);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }

@Test
void findAll_returnsDistinctList() {
    Classes c1 = new Classes();
    c1.setId(1);
    Classes c2 = new Classes();
    c2.setId(2);
    // Usa dos veces la MISMA instancia para simular duplicado
    when(classRepository.findAll()).thenReturn(List.of(c1, c2, c1));
    List<Classes> result = classesService.findAll();
    assertThat(result).containsExactlyInAnyOrder(c1, c2);
}

    @Test
    void findAllPageable_returnsPage() {
        Page<Classes> page = new PageImpl<>(List.of(new Classes()));
        Pageable pageable = mock(Pageable.class);
        when(classRepository.findAll(pageable)).thenReturn(page);
        assertThat(classesService.findAll(pageable)).isEqualTo(page);
    }

    @Test
    void findById_returnsOptional() {
        Classes c = new Classes();
        when(classRepository.findById(1)).thenReturn(Optional.of(c));
        assertThat(classesService.findById(1)).contains(c);
    }

    @Test
    void save_callsRepositorySave() {
        Classes c = new Classes();
        when(classRepository.save(c)).thenReturn(c);
        assertThat(classesService.save(c)).isEqualTo(c);
    }

    @Test
    void deleteById_callsRepositoryDeleteById() {
        classesService.deleteById(5);
        verify(classRepository).deleteById(5);
    }

    @Test
    void deleteClassAndReservations_deletesReservationsAndClass() {
        classesService.deleteClassAndReservations(10);
        verify(reservationRepository).deleteByClassesId(10);
        verify(classRepository).deleteById(10);
    }

    @Test
    void findUpcomingClasses_returnsList() {
        List<Classes> list = List.of(new Classes());
        when(classRepository.findByEventDateAfterOrderByEventDateAsc(any())).thenReturn(list);
        assertThat(classesService.findUpcomingClasses()).isEqualTo(list);
    }

    @Test
    void findUpcomingClassesWithSpots_returnsDTOList() {
        Classes c = new Classes();
        c.setId(1);
        c.setCapacity(10);
        when(classRepository.findByActiveTrueOrderByEventDateAsc()).thenReturn(List.of(c));
        when(reservationService.countByClassId(1)).thenReturn(3);

        List<ClassesDTO> result = classesService.findUpcomingClassesWithSpots();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getClasses()).isEqualTo(c);
        assertThat(result.get(0).getSpotsLeft()).isEqualTo(7);
    }

    @Test
    void findAllOrderedByDateTime_returnsList() {
        List<Classes> list = List.of(new Classes());
        when(classRepository.findAllByOrderByEventDateAscTimeInitAsc()).thenReturn(list);
        assertThat(classesService.findAllOrderedByDateTime()).isEqualTo(list);
    }

    @Test
    void normalizeString_removesAccents() {
        String input = "áéíóúÁÉÍÓÚñÑ";
        String expected = "aeiouAEIOUnN";
        assertThat(ClassesService.normalizeString(input)).isEqualTo(expected);
    }

    @Test
    void isAllowedSchedule_validCombination_returnsTrue() {
        assertThat(classesService.isAllowedSchedule("LUNES", "10:00", "Yoga Suave")).isTrue();
    }

    @Test
    void isAllowedSchedule_invalidCombination_returnsFalse() {
        assertThat(classesService.isAllowedSchedule("DOMINGO", "10:00", "Yoga Suave")).isFalse();
    }

    @Test
    void existsByDateTime_returnsTrueIfCountGreaterThanZero() {
        when(classRepository.countByEventDateAndTimeInit(any(), any())).thenReturn(1);
        assertThat(classesService.existsByDateTime(LocalDate.now(), LocalTime.NOON)).isTrue();
    }

    @Test
    void existsByDateTimeExcludingId_returnsTrueIfCountGreaterThanZero() {
        when(classRepository.countByEventDateAndTimeInitAndIdNot(any(), any(), anyInt())).thenReturn(2);
        assertThat(classesService.existsByDateTimeExcludingId(LocalDate.now(), LocalTime.NOON, 5)).isTrue();
    }

    @Test
    void countReservationsForClass_returnsCount() {
        when(reservationRepository.countByClassesId(3)).thenReturn(7);
        assertThat(classesService.countReservationsForClass(3)).isEqualTo(7);
    }

    @Test
    void canCreateClass_validAndNotExists_returnsTrue() {
        LocalDate date = LocalDate.of(2024, 6, 3); // LUNES
        LocalTime time = LocalTime.of(10, 0);
        String className = "Yoga Suave";
        when(classRepository.countByEventDateAndTimeInit(date, time)).thenReturn(0);
        assertThat(classesService.canCreateClass(date, time, className)).isTrue();
    }

    @Test
    void canCreateClass_invalidSchedule_returnsFalse() {
        LocalDate date = LocalDate.of(2024, 6, 3); // LUNES
        LocalTime time = LocalTime.of(15, 0);
        String className = "Yoga Suave";
        assertThat(classesService.canCreateClass(date, time, className)).isFalse();
    }

    @Test
    void createWeeklyClasses_createsClassesOrAddsToNotCreated() {
        LocalDate startDate = LocalDate.of(2024, 6, 3); // LUNES
        LocalTime time = LocalTime.of(10, 0);
        String className = "Yoga Suave";
        when(classRepository.countByEventDateAndTimeInit(any(), any())).thenReturn(0);

        classesService.createWeeklyClasses(startDate, 2, time, className);

        verify(classRepository, times(2)).save(any());
    }
}