package studioyoga.project.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.ui.Model;

import studioyoga.project.model.BlogPost;
import studioyoga.project.model.Event;
import studioyoga.project.service.BlogService;
import studioyoga.project.service.EventService;
import studioyoga.project.service.GuideSectionService;

class HomeControllerTest {

    @Mock
    private EventService eventService;

    @Mock
    private GuideSectionService guideSectionService;

    @Mock
    private BlogService blogService;

    @Mock
    private Model model;

    @InjectMocks
    private HomeController controller;
    
@BeforeEach
void setUp() {
    MockitoAnnotations.openMocks(this);
    controller = new HomeController(eventService, guideSectionService, blogService);
}

    @Test
    void testHome() {
        when(eventService.findAllActive()).thenReturn(List.of(new Event()));
        String view = controller.home(model);
        verify(model).addAttribute(eq("reviews"), anyList());
        verify(model).addAttribute(eq("events"), anyList());
        assertThat(view).isEqualTo("user/index");
    }

    @Test
    void testLogin() {
        assertThat(controller.login()).isEqualTo("user/login");
    }

    @Test
    void testRegister() {
        assertThat(controller.register()).isEqualTo("user/formRegister");
    }

    @Test
    void testSchedule() {
        assertThat(controller.schedule()).isEqualTo("user/schedule");
    }

    @Test
    void testPrices() {
        assertThat(controller.prices()).isEqualTo("user/prices");
    }

    @Test
    void testRules() {
        assertThat(controller.rules()).isEqualTo("user/rules");
    }

    @Test
    void testEvents() {
        when(eventService.findAllActive()).thenReturn(List.of(new Event()));
        String view = controller.events(model);
        verify(model).addAttribute(eq("events"), anyList());
        assertThat(view).isEqualTo("user/events");
    }

    @Test
    void testEventDetailFound() {
        Event event = new Event();
        when(eventService.findById(1)).thenReturn(Optional.of(event));
        String view = controller.eventDetail(1, model);
        verify(model).addAttribute("event", event);
        assertThat(view).isEqualTo("user/events-detail");
    }

    @Test
    void testEventDetailNotFound() {
        when(eventService.findById(2)).thenReturn(Optional.empty());
        String view = controller.eventDetail(2, model);
        assertThat(view).isEqualTo("redirect:/eventos?error=notfound");
    }

    @Test
    void testShowBlog() {
        when(blogService.findAllPublishedOrdered()).thenReturn(List.of(new BlogPost()));
        String view = controller.showBlog(model);
        verify(model).addAttribute(eq("blogPosts"), anyList());
        assertThat(view).isEqualTo("user/blog");
    }

    @Test
    void testShowBlogPostDetailFound() {
        BlogPost post = new BlogPost();
        when(blogService.findById(1)).thenReturn(Optional.of(post));
        String view = controller.showBlogPostDetail(1, model);
        verify(model).addAttribute("post", post);
        assertThat(view).isEqualTo("user/blog-post-detail");
    }

    @Test
    void testShowBlogPostDetailNotFound() {
        when(blogService.findById(5)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> controller.showBlogPostDetail(5, model))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining("Post no encontrado");
    }

    @Test
    void testShowYogaGuide() {
        when(guideSectionService.getAllSections()).thenReturn(List.of());
        String view = controller.showYogaGuide(model);
        verify(model).addAttribute(eq("sections"), anyList());
        assertThat(view).isEqualTo("user/guide");
    }

    @Test
    void testFaq() {
        assertThat(controller.faq()).isEqualTo("user/faq");
    }

    @Test
    void testLocation() {
        assertThat(controller.location()).isEqualTo("user/location");
    }

    @Test
    void testAboutUs() {
        assertThat(controller.aboutUs()).isEqualTo("user/about-us");
    }

    @Test
    void testContact() {
        assertThat(controller.contact()).isEqualTo("user/form-contact");
    }
}
