package studioyoga.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import studioyoga.project.model.Rol;
import studioyoga.project.repository.RolRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class RolServiceTest {

    private RolRepository rolRepository;
    private RolService rolService;

    @BeforeEach
    void setUp() {
        rolRepository = mock(RolRepository.class);
        rolService = new RolService(rolRepository);
    }

    @Test
    void findAll_returnsAllRoles() {
        List<Rol> roles = List.of(new Rol(1, "ADMIN"), new Rol(2, "USER"));
        when(rolRepository.findAll()).thenReturn(roles);

        List<Rol> result = rolService.findAll();

        assertThat(result).isEqualTo(roles);
        verify(rolRepository, times(1)).findAll();
    }

    @Test
    void findByName_returnsRoleIfExists() {
        Rol rol = new Rol(1, "ADMIN");
        when(rolRepository.findByName("ADMIN")).thenReturn(Optional.of(rol));

        Optional<Rol> result = rolService.findByName("ADMIN");

        assertThat(result).contains(rol);
        verify(rolRepository, times(1)).findByName("ADMIN");
    }

    @Test
    void findByName_returnsEmptyIfNotExists() {
        when(rolRepository.findByName("NOEXISTE")).thenReturn(Optional.empty());

        Optional<Rol> result = rolService.findByName("NOEXISTE");

        assertThat(result).isEmpty();
        verify(rolRepository, times(1)).findByName("NOEXISTE");
    }
}