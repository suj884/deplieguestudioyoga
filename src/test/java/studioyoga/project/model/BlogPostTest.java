package studioyoga.project.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class BlogPostTest {

    @Test
    void testNoArgsConstructorAndSettersAndGetters() {
        BlogPost post = new BlogPost();
        post.setId(1);
        post.setTitle("Título");
        post.setContent("Contenido largo");
        post.setSummary("Resumen");
        post.setImageUrl("http://img.com/1.jpg");
        post.setPublishedDate(LocalDate.of(2024, 6, 1));

        assertThat(post.getId()).isEqualTo(1);
        assertThat(post.getTitle()).isEqualTo("Título");
        assertThat(post.getContent()).isEqualTo("Contenido largo");
        assertThat(post.getSummary()).isEqualTo("Resumen");
        assertThat(post.getImageUrl()).isEqualTo("http://img.com/1.jpg");
        assertThat(post.getPublishedDate()).isEqualTo(LocalDate.of(2024, 6, 1));
    }

    @Test
    void testAllArgsConstructor() {
        LocalDate date = LocalDate.of(2024, 6, 1);
        BlogPost post = new BlogPost(2, "Otro título", "Texto", "Resumen2", "img2.jpg", date);

        assertThat(post.getId()).isEqualTo(2);
        assertThat(post.getTitle()).isEqualTo("Otro título");
        assertThat(post.getContent()).isEqualTo("Texto");
        assertThat(post.getSummary()).isEqualTo("Resumen2");
        assertThat(post.getImageUrl()).isEqualTo("img2.jpg");
        assertThat(post.getPublishedDate()).isEqualTo(date);
    }

    @Test
    void testEqualsAndHashCode() {
        LocalDate date = LocalDate.of(2024, 6, 1);
        BlogPost post1 = new BlogPost(1, "A", "B", "C", "D", date);
        BlogPost post2 = new BlogPost(1, "A", "B", "C", "D", date);

        assertThat(post1).isEqualTo(post2);
        assertThat(post1.hashCode()).isEqualTo(post2.hashCode());
    }

    @Test
    void testToString() {
        BlogPost post = new BlogPost();
        post.setId(5);
        post.setTitle("Test");
        String str = post.toString();
        assertThat(str).contains("5").contains("Test");
    }
}