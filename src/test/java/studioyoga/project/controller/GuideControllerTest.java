package studioyoga.project.controller;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import studioyoga.project.constants.RedirConstants;
import studioyoga.project.model.GuideSection;
import studioyoga.project.service.GuideSectionService;

import java.util.Arrays;

class GuideControllerTest {

    @Mock
    private GuideSectionService guideSectionService;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private GuideController guideController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listGuideSections_shouldAddSectionsAndReturnView() {
        when(guideSectionService.getAllSections()).thenReturn(Arrays.asList(new GuideSection(), new GuideSection()));
        String view = guideController.listGuideSections(model);
        verify(model).addAttribute(eq("sections"), any());
        assertThat(view).isEqualTo("admin/manage-guide");
    }

    @Test
    void showCreateForm_shouldAddEmptySectionAndReturnView() {
        String view = guideController.showCreateForm(model);
        verify(model).addAttribute(eq("guideSection"), any(GuideSection.class));
        assertThat(view).isEqualTo("admin/form-guide");
    }

    @Test
    void createSection_shouldSaveAndRedirect() {
        GuideSection section = new GuideSection();
        String view = guideController.createSection(section);
        verify(guideSectionService).saveSection(section);
        assertThat(view).isEqualTo(RedirConstants.REDIRECT_ADMIN_GUIDE);
    }

    @Test
    void showEditForm_shouldAddSectionAndReturnView() {
        GuideSection section = new GuideSection();
        when(guideSectionService.getSectionById(1L)).thenReturn(section);
        String view = guideController.showEditForm(1L, model);
        verify(model).addAttribute("guideSection", section);
        assertThat(view).isEqualTo("admin/form-guide");
    }

    @Test
    void updateSection_shouldSetIdSaveAndRedirect() {
        GuideSection section = new GuideSection();
        String view = guideController.updateSection(2L, section);
        assertThat(section.getId()).isEqualTo(2L);
        verify(guideSectionService).saveSection(section);
        assertThat(view).isEqualTo(RedirConstants.REDIRECT_ADMIN_GUIDE);
    }

    @Test
    void confirmDelete_shouldAddAttributesAndReturnView() {
        GuideSection section = new GuideSection();
        section.setTitle("Test Title");
        when(guideSectionService.getSectionById(5L)).thenReturn(section);
        String view = guideController.confirmDelete(5L, model);
        verify(model).addAttribute(eq("message"), contains("Test Title"));
        verify(model).addAttribute(eq("action"), eq("/admin/guide/delete/5"));
        verify(model).addAttribute(eq("cancelUrl"), eq("/admin/guide/manage-guide"));
        assertThat(view).isEqualTo("admin/confirm-delete");
    }

    @Test
    void deleteSection_shouldDeleteAndRedirectWithSuccess() {
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
        String view = guideController.deleteSection(7L, redirectAttributes);
        verify(guideSectionService).deleteSectionById(7L);
        verify(redirectAttributes).addFlashAttribute(eq("success"), anyString());
        assertThat(view).isEqualTo(RedirConstants.REDIRECT_ADMIN_GUIDE);
    }
}
