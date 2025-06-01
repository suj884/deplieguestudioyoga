package studioyoga.project.controller;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import studioyoga.project.constants.RedirConstants;
import studioyoga.project.model.BlogPost;
import studioyoga.project.service.BlogService;

class BlogPostControllerTest {

    @InjectMocks
    private BlogPostController controller;

    @Mock
    private BlogService blogService;

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
    void testManageBlog() {
        List<BlogPost> posts = Arrays.asList(new BlogPost(), new BlogPost());
        when(blogService.findAll()).thenReturn(posts);

        String view = controller.manageBlog(model);

        verify(model).addAttribute(eq("blogPosts"), eq(posts));
        assertThat(view).isEqualTo("admin/manage-blog");
    }

    @Test
    void testNewPostForm() {
        String view = controller.newPostForm(model);

        // Verifica que se añade el atributo "post" al modelo
        verify(model).addAttribute(eq("post"), any(BlogPost.class));
        assertThat(view).isEqualTo("admin/form-blog");
    }

    @Test
    void testEditPostFormFound() {
        BlogPost post = new BlogPost();
        when(blogService.findById(1)).thenReturn(Optional.of(post));

        String view = controller.editPostForm(1, model, redirectAttributes);

        verify(model).addAttribute("post", post);
        assertThat(view).isEqualTo("admin/form-blog");
    }

    @Test
    void testEditPostFormNotFound() {
        when(blogService.findById(2)).thenReturn(Optional.empty());

        String view = controller.editPostForm(2, model, redirectAttributes);

        verify(redirectAttributes).addFlashAttribute(eq("error"), anyString());
        assertThat(view).isEqualTo(RedirConstants.REDIRECT_ADMIN_BLOG);
    }

    @Test
    void testSavePost() {
        BlogPost post = new BlogPost();

        String view = controller.savePost(post, redirectAttributes);

        verify(blogService).save(post);
        verify(redirectAttributes).addFlashAttribute(eq("success"), anyString());
        assertThat(view).isEqualTo(RedirConstants.REDIRECT_ADMIN_BLOG);
    }

    @Test
    void testConfirmDeleteFound() {
        BlogPost post = new BlogPost();
        post.setId(3);
        post.setTitle("Test Post");
        when(blogService.findById(3)).thenReturn(Optional.of(post));

        String view = controller.confirmDelete(3, model);

        verify(model).addAttribute(eq("message"), contains("Test Post"));
        verify(model).addAttribute(eq("action"), eq("/admin/blog/delete/3"));
        verify(model).addAttribute(eq("cancelUrl"), eq("/admin/blog/manage-blog"));
        assertThat(view).isEqualTo("admin/confirm-delete");
    }

    @Test
    void testConfirmDeleteNotFound() {
        when(blogService.findById(4)).thenReturn(Optional.empty());

        String view = controller.confirmDelete(4, model);

        verify(model).addAttribute(eq("error"), anyString());
        assertThat(view).isEqualTo("redirect:/admin/blog/manage-blog");
    }

    @Test
    void testDeletePost() {
        String view = controller.deletePost(5, redirectAttributes);

        verify(blogService).deleteById(5);
        verify(redirectAttributes).addFlashAttribute(eq("success"), anyString());
        assertThat(view).isEqualTo("redirect:/admin/blog/manage-blog");
    }
}
