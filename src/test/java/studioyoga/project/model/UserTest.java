package studioyoga.project.model;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void testNoArgsConstructorAndSettersAndGetters() {
        User user = new User();
        user.setId(1);
        user.setName("Juan");
        user.setFirstLastName("Pérez");
        user.setSecondLastName("García");
        user.setEmail("juan@email.com");
        user.setPassword("pass");
        user.setRegistrationDate(LocalDate.of(2024, 6, 1));
        user.setProfilePicture("img.jpg");
        user.setPhoneNumber("123456789");
        user.setEnabled(true);
        user.setVerificationCode("code123");
        Rol rol = new Rol();
        rol.setName("ADMIN");
        user.setRol(rol);
        user.setNotes("Notas de prueba");

        assertThat(user.getId()).isEqualTo(1);
        assertThat(user.getName()).isEqualTo("Juan");
        assertThat(user.getFirstLastName()).isEqualTo("Pérez");
        assertThat(user.getSecondLastName()).isEqualTo("García");
        assertThat(user.getEmail()).isEqualTo("juan@email.com");
        assertThat(user.getPassword()).isEqualTo("pass");
        assertThat(user.getRegistrationDate()).isEqualTo(LocalDate.of(2024, 6, 1));
        assertThat(user.getProfilePicture()).isEqualTo("img.jpg");
        assertThat(user.getPhoneNumber()).isEqualTo("123456789");
        assertThat(user.isEnabled()).isTrue();
        assertThat(user.getVerificationCode()).isEqualTo("code123");
        assertThat(user.getRol()).isEqualTo(rol);
        assertThat(user.getNotes()).isEqualTo("Notas de prueba");
    }

    @Test
    void testAllArgsConstructor() {
        Rol rol = new Rol();
        rol.setName("USER");
        User user = new User(2, "Ana", "López", "Martínez", "ana@email.com", "pass2",
                LocalDate.of(2024, 6, 2), "img2.jpg", "987654321", false, "verif456", rol, "Notas Ana");

        assertThat(user.getId()).isEqualTo(2);
        assertThat(user.getName()).isEqualTo("Ana");
        assertThat(user.getFirstLastName()).isEqualTo("López");
        assertThat(user.getSecondLastName()).isEqualTo("Martínez");
        assertThat(user.getEmail()).isEqualTo("ana@email.com");
        assertThat(user.getPassword()).isEqualTo("pass2");
        assertThat(user.getRegistrationDate()).isEqualTo(LocalDate.of(2024, 6, 2));
        assertThat(user.getProfilePicture()).isEqualTo("img2.jpg");
        assertThat(user.getPhoneNumber()).isEqualTo("987654321");
        assertThat(user.isEnabled()).isFalse();
        assertThat(user.getVerificationCode()).isEqualTo("verif456");
        assertThat(user.getRol()).isEqualTo(rol);
        assertThat(user.getNotes()).isEqualTo("Notas Ana");
    }

    @Test
    void testEqualsAndHashCode() {
        Rol rol = new Rol();
        rol.setName("ADMIN");
        User u1 = new User(3, "A", "B", "C", "a@b.com", "p", LocalDate.now(), "img", "111", true, "v", rol, "n");
        User u2 = new User(3, "A", "B", "C", "a@b.com", "p", LocalDate.now(), "img", "111", true, "v", rol, "n");

        assertThat(u1).isEqualTo(u2);
        assertThat(u1.hashCode()).isEqualTo(u2.hashCode());
    }

    @Test
    void testToString() {
        User user = new User();
        user.setId(7);
        user.setName("Test");
        String str = user.toString();
        assertThat(str).contains("7").contains("Test");
    }

    @Test
    void testUserDetailsMethods() {
        Rol rol = new Rol();
        rol.setName("USER");
        User user = new User();
        user.setEmail("correo@correo.com");
        user.setPassword("clave");
        user.setEnabled(true);
        user.setRol(rol);


    // getAuthorities debe contener el rol como GrantedAuthority con nombre "ROLE_USER"
    Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
    assertThat(authorities)
        .extracting(GrantedAuthority::getAuthority)
        .contains("ROLE_USER");

        // getUsername debe devolver el email
        assertThat(user.getUsername()).isEqualTo("correo@correo.com");
    }
}