package studioyoga.project.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GuideSectionTest {

    @Test
    void testNoArgsConstructorAndSettersAndGetters() {
        GuideSection section = new GuideSection();
        section.setId(1L);
        section.setTitle("Introducción");
        section.setContent("<p>Bienvenido a la guía</p>");
        section.setImageUrl("img.jpg");
        section.setSectionOrder(1);

        assertThat(section.getId()).isEqualTo(1L);
        assertThat(section.getTitle()).isEqualTo("Introducción");
        assertThat(section.getContent()).isEqualTo("<p>Bienvenido a la guía</p>");
        assertThat(section.getImageUrl()).isEqualTo("img.jpg");
        assertThat(section.getSectionOrder()).isEqualTo(1);
    }

    @Test
    void testAllArgsConstructor() {
        GuideSection section = new GuideSection(2L, "Posturas", "<p>Contenido</p>", "img2.jpg", 2);

        assertThat(section.getId()).isEqualTo(2L);
        assertThat(section.getTitle()).isEqualTo("Posturas");
        assertThat(section.getContent()).isEqualTo("<p>Contenido</p>");
        assertThat(section.getImageUrl()).isEqualTo("img2.jpg");
        assertThat(section.getSectionOrder()).isEqualTo(2);
    }

    @Test
    void testEqualsAndHashCode() {
        GuideSection s1 = new GuideSection(3L, "A", "B", "C", 3);
        GuideSection s2 = new GuideSection(3L, "A", "B", "C", 3);

        assertThat(s1).isEqualTo(s2);
        assertThat(s1.hashCode()).isEqualTo(s2.hashCode());
    }

    @Test
    void testToString() {
        GuideSection section = new GuideSection();
        section.setId(7L);
        section.setTitle("Test");
        String str = section.toString();
        assertThat(str).contains("7").contains("Test");
    }
}