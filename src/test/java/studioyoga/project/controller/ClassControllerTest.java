package studioyoga.project.controller;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import studioyoga.project.model.Classes;
import studioyoga.project.service.ClassesService;

class ClassControllerTest {

    @InjectMocks
    private ClassController controller;

    @Mock
    private ClassesService classesService;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @Captor
    private ArgumentCaptor<Object> captor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testManageClasses() {
        Page<Classes> page = new PageImpl<>(Collections.singletonList(new Classes()));
        when(classesService.findAll(any(Pageable.class))).thenReturn(page);

        String view = controller.manageClasses(0, 10, model);

        verify(model).addAttribute("classesPage", page);
        verify(model).addAttribute("page", 0);
        verify(model).addAttribute("size", 10);
        assertThat(view).isEqualTo("admin/manage-classes");
    }

    @Test
    void testShowCreateForm() {
        String view = controller.showCreateForm(model);

        verify(model).addAttribute(eq("classes"), any(Classes.class));
        assertThat(view).isEqualTo("admin/form-classes");
    }

    @Test
    void testSaveClass_NotAllowedSchedule() {
        Classes c = new Classes();
        c.setTitle("Yoga");
        c.setEventDate(LocalDate.of(2024, 6, 1));
        c.setTimeInit(LocalTime.of(10, 0));

        when(classesService.isAllowedSchedule(anyString(), anyString(), anyString())).thenReturn(false);

        String view = controller.saveClass(c, redirectAttributes);

        verify(redirectAttributes).addFlashAttribute(eq("error"), contains("No puedes registrar esa clase"));
        assertThat(view).isEqualTo("redirect:/admin/classes/new");
    }

    @Test
    void testSaveClass_DuplicateOnCreate() {
        Classes c = new Classes();
        c.setTitle("Yoga");
        c.setEventDate(LocalDate.of(2024, 6, 1));
        c.setTimeInit(LocalTime.of(10, 0));

        when(classesService.isAllowedSchedule(anyString(), anyString(), anyString())).thenReturn(true);
        when(classesService.existsByDateTime(any(), any())).thenReturn(true);

        String view = controller.saveClass(c, redirectAttributes);

        verify(redirectAttributes).addFlashAttribute(eq("error"), contains("Ya existe una clase registrada"));
        assertThat(view).isEqualTo("redirect:/admin/classes/new");
    }

    @Test
    void testSaveClass_DuplicateOnEdit() {
        Classes c = new Classes();
        c.setId(1);
        c.setTitle("Yoga");
        c.setEventDate(LocalDate.of(2024, 6, 1));
        c.setTimeInit(LocalTime.of(10, 0));

        when(classesService.isAllowedSchedule(anyString(), anyString(), anyString())).thenReturn(true);
        when(classesService.existsByDateTimeExcludingId(any(), any(), eq(1))).thenReturn(true);

        String view = controller.saveClass(c, redirectAttributes);

        verify(redirectAttributes).addFlashAttribute(eq("error"), contains("Ya existe una clase registrada"));
        assertThat(view).isEqualTo("redirect:/admin/classes/new");
    }

    @Test
    void testSaveClass_SuccessOnCreate() {
        Classes c = new Classes();
        c.setTitle("Yoga");
        c.setEventDate(LocalDate.of(2024, 6, 1));
        c.setTimeInit(LocalTime.of(10, 0));

        when(classesService.isAllowedSchedule(anyString(), anyString(), anyString())).thenReturn(true);
        when(classesService.existsByDateTime(any(), any())).thenReturn(false);

        String view = controller.saveClass(c, redirectAttributes);

        verify(classesService).save(c);
        verify(redirectAttributes).addFlashAttribute(eq("success"), contains("Clase guardada correctamente"));
        assertThat(view).isEqualTo("redirect:/admin/classes/manage-classes");
    }

    @Test
    void testShowEditForm_Found() {
        Classes c = new Classes();
        c.setId(1);
        when(classesService.findById(1)).thenReturn(Optional.of(c));

        String view = controller.showEditForm(1, model);

        verify(model).addAttribute("classes", c);
        assertThat(view).isEqualTo("admin/form-classes");
    }

    @Test
    void testShowEditForm_NotFound() {
        when(classesService.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> controller.showEditForm(99, model))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Clase no encontrada");
    }

    @Test
    void testDeleteClass() {
        String view = controller.deleteClass(7, redirectAttributes);

        verify(classesService).deleteClassAndReservations(7);
        verify(redirectAttributes).addFlashAttribute(eq("success"), contains("Clase eliminada correctamente"));
        assertThat(view).isEqualTo("redirect:/admin/classes/manage-classes");
    }

    @Test
    void testConfirmDelete_WithReservations() {
        Classes c = new Classes();
        c.setId(8);
        c.setTitle("Yoga Avanzado");
        when(classesService.findById(8)).thenReturn(Optional.of(c));
        when(classesService.countReservationsForClass(8)).thenReturn(3);

        String view = controller.confirmDelete(8, model);

        verify(model).addAttribute(eq("message"), contains("Yoga Avanzado"));
        verify(model).addAttribute(eq("action"), eq("/admin/classes/delete/8"));
        verify(model).addAttribute(eq("cancelUrl"), eq("/admin/classes/manage-classes"));
        assertThat(view).isEqualTo("admin/confirm-delete");
    }

    @Test
    void testConfirmDelete_NoReservations() {
        Classes c = new Classes();
        c.setId(9);
        c.setTitle("Yoga Básico");
        when(classesService.findById(9)).thenReturn(Optional.of(c));
        when(classesService.countReservationsForClass(9)).thenReturn(0);

        String view = controller.confirmDelete(9, model);

        verify(model).addAttribute(eq("message"), contains("Yoga Básico"));
        verify(model).addAttribute(eq("action"), eq("/admin/classes/delete/9"));
        verify(model).addAttribute(eq("cancelUrl"), eq("/admin/classes/manage-classes"));
        assertThat(view).isEqualTo("admin/confirm-delete");
    }

    @Test
    void testConfirmDelete_NotFound() {
        when(classesService.findById(100)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> controller.confirmDelete(100, model))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Clase no encontrada");
    }
}
