package studioyoga.project.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class TicketTest {

    @Test
    void testNoArgsConstructorAndSettersAndGetters() {
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setAsunto("Problema acceso");
        ticket.setDescripcion("No puedo entrar al sistema");
        LocalDateTime fecha = LocalDateTime.of(2024, 6, 3, 10, 0);
        ticket.setFechaCreacion(fecha);
        ticket.setEstado("Abierto");
        User usuario = new User();
        usuario.setId(5);
        ticket.setUsuario(usuario);

        assertThat(ticket.getId()).isEqualTo(1L);
        assertThat(ticket.getAsunto()).isEqualTo("Problema acceso");
        assertThat(ticket.getDescripcion()).isEqualTo("No puedo entrar al sistema");
        assertThat(ticket.getFechaCreacion()).isEqualTo(fecha);
        assertThat(ticket.getEstado()).isEqualTo("Abierto");
        assertThat(ticket.getUsuario()).isEqualTo(usuario);
    }

    @Test
    void testAllArgsConstructor() {
        User usuario = new User();
        usuario.setId(2);
        LocalDateTime fecha = LocalDateTime.of(2024, 6, 4, 12, 0);
        Ticket ticket = new Ticket(2L, "Error", "Detalle", fecha, "Cerrado", usuario);

        assertThat(ticket.getId()).isEqualTo(2L);
        assertThat(ticket.getAsunto()).isEqualTo("Error");
        assertThat(ticket.getDescripcion()).isEqualTo("Detalle");
        assertThat(ticket.getFechaCreacion()).isEqualTo(fecha);
        assertThat(ticket.getEstado()).isEqualTo("Cerrado");
        assertThat(ticket.getUsuario()).isEqualTo(usuario);
    }

    @Test
    void testEqualsAndHashCode() {
        User usuario = new User();
        usuario.setId(3);
        LocalDateTime fecha = LocalDateTime.of(2024, 6, 5, 14, 0);
        Ticket t1 = new Ticket(3L, "A", "B", fecha, "Abierto", usuario);
        Ticket t2 = new Ticket(3L, "A", "B", fecha, "Abierto", usuario);

        assertThat(t1).isEqualTo(t2);
        assertThat(t1.hashCode()).isEqualTo(t2.hashCode());
    }

    @Test
    void testToString() {
        Ticket ticket = new Ticket();
        ticket.setId(7L);
        ticket.setAsunto("Test");
        String str = ticket.toString();
        assertThat(str).contains("7").contains("Test");
    }
}
