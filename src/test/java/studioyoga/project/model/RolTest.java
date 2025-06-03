package studioyoga.project.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class RolTest {

    @Test
    void testNoArgsConstructorAndSettersAndGetters() {
        Rol rol = new Rol();
        rol.setId(1);
        rol.setName("ADMIN");

        assertThat(rol.getId()).isEqualTo(1);
        assertThat(rol.getName()).isEqualTo("ADMIN");
    }

    @Test
    void testAllArgsConstructor() {
        Rol rol = new Rol(2, "USER");
        assertThat(rol.getId()).isEqualTo(2);
        assertThat(rol.getName()).isEqualTo("USER");
    }

    @Test
    void testGetAuthority() {
        Rol rol = new Rol(3, "MANAGER");
        assertThat(rol.getAuthority()).isEqualTo("ROLE_MANAGER");
    }

    @Test
    void testEqualsAndHashCode() {
        Rol r1 = new Rol(4, "TEST");
        Rol r2 = new Rol(4, "TEST");
        assertThat(r1).isEqualTo(r2);
        assertThat(r1.hashCode()).isEqualTo(r2.hashCode());
    }

    @Test
    void testToString() {
        Rol rol = new Rol(5, "VIEWER");
        String str = rol.toString();
        assertThat(str).contains("5").contains("VIEWER");
    }
}