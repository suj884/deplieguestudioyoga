package studioyoga.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import studioyoga.project.model.GuideSection;
import studioyoga.project.repository.GuideSectionRepository;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GuideSectionServiceTest {

    private GuideSectionRepository repository;
    private GuideSectionService service;

    @BeforeEach
    void setUp() {
        repository = mock(GuideSectionRepository.class);
        service = new GuideSectionService(repository);
    }


    @Test
    void saveSection_callsRepositorySave() {
        GuideSection section = new GuideSection();
        service.saveSection(section);
        verify(repository, times(1)).save(section);
    }

    @Test
    void getAllSections_returnsOrderedList() {
        List<GuideSection> list = List.of(new GuideSection());
        when(repository.findAllByOrderBySectionOrderAsc()).thenReturn(list);
        List<GuideSection> result = service.getAllSections();
        assertThat(result).isEqualTo(list);
    }

    @Test
    void deleteSectionById_callsRepositoryDeleteById() {
        service.deleteSectionById(5L);
        verify(repository, times(1)).deleteById(5L);
    }
}