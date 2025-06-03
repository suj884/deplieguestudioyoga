package studioyoga.project.model;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

class ClassesTest {

    @Test
    void testNoArgsConstructorAndSettersAndGetters() {
        Classes yogaClass = new Classes();
        yogaClass.setId(1);
        yogaClass.setTitle("Yoga Básico");
        yogaClass.setDescription("Clase para principiantes");
        yogaClass.setInstructor("Ana");
        yogaClass.setImageUrl("img.jpg");
        yogaClass.setEventDate(LocalDate.of(2024, 6, 1));
        yogaClass.setTimeInit(LocalTime.of(10, 0));
        yogaClass.setTimeEnd(LocalTime.of(11, 0));
        yogaClass.setLocation("Sala 1");
        yogaClass.setActive(false);
        yogaClass.setCapacity(20);

        assertThat(yogaClass.getId()).isEqualTo(1);
        assertThat(yogaClass.getTitle()).isEqualTo("Yoga Básico");
        assertThat(yogaClass.getDescription()).isEqualTo("Clase para principiantes");
        assertThat(yogaClass.getInstructor()).isEqualTo("Ana");
        assertThat(yogaClass.getImageUrl()).isEqualTo("img.jpg");
        assertThat(yogaClass.getEventDate()).isEqualTo(LocalDate.of(2024, 6, 1));
        assertThat(yogaClass.getTimeInit()).isEqualTo(LocalTime.of(10, 0));
        assertThat(yogaClass.getTimeEnd()).isEqualTo(LocalTime.of(11, 0));
        assertThat(yogaClass.getLocation()).isEqualTo("Sala 1");
        assertThat(yogaClass.isActive()).isFalse();
        assertThat(yogaClass.getCapacity()).isEqualTo(20);
    }

    @Test
    void testAllArgsConstructor() {
        LocalDate date = LocalDate.of(2024, 6, 2);
        LocalTime start = LocalTime.of(9, 30);
        LocalTime end = LocalTime.of(10, 30);
        Classes yogaClass = new Classes(2, "Avanzado", "Clase avanzada", "Luis", "img2.jpg", date, start, end, "Sala 2", true, 15);

        assertThat(yogaClass.getId()).isEqualTo(2);
        assertThat(yogaClass.getTitle()).isEqualTo("Avanzado");
        assertThat(yogaClass.getDescription()).isEqualTo("Clase avanzada");
        assertThat(yogaClass.getInstructor()).isEqualTo("Luis");
        assertThat(yogaClass.getImageUrl()).isEqualTo("img2.jpg");
        assertThat(yogaClass.getEventDate()).isEqualTo(date);
        assertThat(yogaClass.getTimeInit()).isEqualTo(start);
        assertThat(yogaClass.getTimeEnd()).isEqualTo(end);
        assertThat(yogaClass.getLocation()).isEqualTo("Sala 2");
        assertThat(yogaClass.isActive()).isTrue();
        assertThat(yogaClass.getCapacity()).isEqualTo(15);
    }

    @Test
    void testEqualsAndHashCode() {
        LocalDate date = LocalDate.of(2024, 6, 2);
        LocalTime start = LocalTime.of(9, 30);
        LocalTime end = LocalTime.of(10, 30);
        Classes c1 = new Classes(3, "A", "B", "C", "D", date, start, end, "E", true, 10);
        Classes c2 = new Classes(3, "A", "B", "C", "D", date, start, end, "E", true, 10);

        assertThat(c1).isEqualTo(c2);
        assertThat(c1.hashCode()).isEqualTo(c2.hashCode());
    }

    @Test
    void testToString() {
        Classes yogaClass = new Classes();
        yogaClass.setId(7);
        yogaClass.setTitle("Test");
        String str = yogaClass.toString();
        assertThat(str).contains("7").contains("Test");
    }
}