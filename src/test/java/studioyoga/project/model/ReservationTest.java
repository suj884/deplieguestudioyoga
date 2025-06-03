package studioyoga.project.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ReservationTest {

    @Test
    void testNoArgsConstructorAndSettersAndGetters() {
        Reservation reservation = new Reservation();
        reservation.setId(1);
        User user = new User();
        user.setId(10);
        reservation.setUser(user);
        Classes yogaClass = new Classes();
        yogaClass.setId(20);
        reservation.setClasses(yogaClass);
        LocalDateTime now = LocalDateTime.of(2024, 6, 1, 10, 0);
        reservation.setDateReservation(now);
        reservation.setActive(false);

        assertThat(reservation.getId()).isEqualTo(1);
        assertThat(reservation.getUser()).isEqualTo(user);
        assertThat(reservation.getClasses()).isEqualTo(yogaClass);
        assertThat(reservation.getDateReservation()).isEqualTo(now);
        assertThat(reservation.isActive()).isFalse();
    }

    @Test
    void testAllArgsConstructor() {
        User user = new User();
        user.setId(11);
        Classes yogaClass = new Classes();
        yogaClass.setId(21);
        LocalDateTime date = LocalDateTime.of(2024, 6, 2, 12, 0);
        Reservation reservation = new Reservation(2, user, yogaClass, date, true);

        assertThat(reservation.getId()).isEqualTo(2);
        assertThat(reservation.getUser()).isEqualTo(user);
        assertThat(reservation.getClasses()).isEqualTo(yogaClass);
        assertThat(reservation.getDateReservation()).isEqualTo(date);
        assertThat(reservation.isActive()).isTrue();
    }

    @Test
    void testEqualsAndHashCode() {
        User user = new User();
        user.setId(1);
        Classes yogaClass = new Classes();
        yogaClass.setId(2);
        LocalDateTime date = LocalDateTime.of(2024, 6, 3, 8, 0);
        Reservation r1 = new Reservation(3, user, yogaClass, date, true);
        Reservation r2 = new Reservation(3, user, yogaClass, date, true);

        assertThat(r1).isEqualTo(r2);
        assertThat(r1.hashCode()).isEqualTo(r2.hashCode());
    }

    @Test
    void testToString() {
        Reservation reservation = new Reservation();
        reservation.setId(7);
        reservation.setActive(true);
        String str = reservation.toString();
        assertThat(str).contains("7").contains("true");
    }
}