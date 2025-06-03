package studioyoga.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import studioyoga.project.model.BlogPost;
import studioyoga.project.repository.BlogPostRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BlogServiceTest {

    private BlogPostRepository blogPostRepository;
    private BlogService blogService;

    @BeforeEach
    void setUp() {
        blogPostRepository = mock(BlogPostRepository.class);
        blogService = new BlogService();
        // Inyectar el mock usando reflexión porque el campo es private y @Autowired
        var field = BlogService.class.getDeclaredFields()[0];
        field.setAccessible(true);
        try {
            field.set(blogService, blogPostRepository);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void findAll_returnsAllPosts() {
        List<BlogPost> posts = List.of(new BlogPost(), new BlogPost());
        when(blogPostRepository.findAll()).thenReturn(posts);

        List<BlogPost> result = blogService.findAll();

        assertThat(result).isEqualTo(posts);
        verify(blogPostRepository, times(1)).findAll();
    }

    @Test
    void findAllPublishedOrdered_returnsOrderedPosts() {
        List<BlogPost> posts = List.of(new BlogPost());
        when(blogPostRepository.findAllByOrderByPublishedDateDesc()).thenReturn(posts);

        List<BlogPost> result = blogService.findAllPublishedOrdered();

        assertThat(result).isEqualTo(posts);
        verify(blogPostRepository, times(1)).findAllByOrderByPublishedDateDesc();
    }

    @Test
    void findById_returnsOptional() {
        BlogPost post = new BlogPost();
        when(blogPostRepository.findById(1)).thenReturn(Optional.of(post));

        Optional<BlogPost> result = blogService.findById(1);

        assertThat(result).contains(post);
        verify(blogPostRepository, times(1)).findById(1);
    }

    @Test
    void save_callsRepositorySave() {
        BlogPost post = new BlogPost();
        when(blogPostRepository.save(post)).thenReturn(post);

        BlogPost result = blogService.save(post);

        assertThat(result).isEqualTo(post);
        verify(blogPostRepository, times(1)).save(post);
    }

    @Test
    void deleteById_callsRepositoryDeleteById() {
        blogService.deleteById(5);
        verify(blogPostRepository, times(1)).deleteById(5);
    }
}