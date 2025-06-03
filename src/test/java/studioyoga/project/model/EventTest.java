package studioyoga.project.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

class EventTest {

    @Test
    void testNoArgsConstructorAndSettersAndGetters() {
        Event event = new Event();
        event.setId(1);
        event.setTitle("Yoga Festival");
        event.setDescription("Un gran evento de yoga");
        event.setEventDate(LocalDate.of(2024, 7, 1));
        event.setEventTime(LocalTime.of(18, 0));
        event.setImageUrl("img.jpg");
        event.setLocation("Parque Central");
        event.setActive(false);

        assertThat(event.getId()).isEqualTo(1);
        assertThat(event.getTitle()).isEqualTo("Yoga Festival");
        assertThat(event.getDescription()).isEqualTo("Un gran evento de yoga");
        assertThat(event.getEventDate()).isEqualTo(LocalDate.of(2024, 7, 1));
        assertThat(event.getEventTime()).isEqualTo(LocalTime.of(18, 0));
        assertThat(event.getImageUrl()).isEqualTo("img.jpg");
        assertThat(event.getLocation()).isEqualTo("Parque Central");
        assertThat(event.isActive()).isFalse();
    }

    @Test
    void testAllArgsConstructor() {
        LocalDate date = LocalDate.of(2024, 8, 15);
        LocalTime time = LocalTime.of(20, 30);
        Event event = new Event(2, "Clase especial", "Descripción", date, time, "img2.jpg", "Sala 2", true);

        assertThat(event.getId()).isEqualTo(2);
        assertThat(event.getTitle()).isEqualTo("Clase especial");
        assertThat(event.getDescription()).isEqualTo("Descripción");
        assertThat(event.getEventDate()).isEqualTo(date);
        assertThat(event.getEventTime()).isEqualTo(time);
        assertThat(event.getImageUrl()).isEqualTo("img2.jpg");
        assertThat(event.getLocation()).isEqualTo("Sala 2");
        assertThat(event.isActive()).isTrue();
    }

    @Test
    void testEqualsAndHashCode() {
        LocalDate date = LocalDate.of(2024, 8, 15);
        LocalTime time = LocalTime.of(20, 30);
        Event e1 = new Event(3, "A", "B", date, time, "img", "loc", true);
        Event e2 = new Event(3, "A", "B", date, time, "img", "loc", true);

        assertThat(e1).isEqualTo(e2);
        assertThat(e1.hashCode()).isEqualTo(e2.hashCode());
    }

    @Test
    void testToString() {
        Event event = new Event();
        event.setId(7);
        event.setTitle("Test");
        String str = event.toString();
        assertThat(str).contains("7").contains("Test");
    }
}