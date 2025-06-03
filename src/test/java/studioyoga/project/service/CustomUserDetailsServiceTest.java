package studioyoga.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import studioyoga.project.model.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CustomUserDetailsServiceTest {

    private UserService userService;
    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        customUserDetailsService = new CustomUserDetailsService(userService);
    }

    @Test
    void loadUserByUsername_returnsUserDetails() {
        User user = mock(User.class); // User implementa UserDetails
        when(userService.findByEmail("test@email.com")).thenReturn(user);

        UserDetails result = customUserDetailsService.loadUserByUsername("test@email.com");

        assertThat(result).isEqualTo(user);
        verify(userService, times(1)).findByEmail("test@email.com");
    }

    @Test
    void loadUserByUsername_throwsExceptionIfNotFound() {
        when(userService.findByEmail("notfound@email.com")).thenThrow(new UsernameNotFoundException("Not found"));

        assertThrows(UsernameNotFoundException.class,
                () -> customUserDetailsService.loadUserByUsername("notfound@email.com"));
        verify(userService, times(1)).findByEmail("notfound@email.com");
    }
}