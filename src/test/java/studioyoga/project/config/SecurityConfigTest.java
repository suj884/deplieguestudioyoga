package studioyoga.project.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import studioyoga.project.service.CustomUserDetailsService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class SecurityConfigTest {

    private CustomUserDetailsService customUserDetailsService;
    private SecurityConfig securityConfig;

    @BeforeEach
    void setUp() {
        customUserDetailsService = mock(CustomUserDetailsService.class);
        securityConfig = new SecurityConfig(customUserDetailsService);
    }

@Test
void authenticationProvider_returnsConfiguredProvider() {
    DaoAuthenticationProvider provider = securityConfig.authenticationProvider();
    assertThat(provider).isInstanceOf(DaoAuthenticationProvider.class);
}

    @Test
    void passwordEncoder_returnsBCryptPasswordEncoder() {
        PasswordEncoder encoder = securityConfig.passwordEncoder();
        assertThat(encoder).isInstanceOf(BCryptPasswordEncoder.class);
    }

    @Test
    void authenticationSuccessHandler_isNotNull() {
        AuthenticationSuccessHandler handler = securityConfig.authenticationSuccessHandler();
        assertThat(handler).isNotNull();
    }

    @Test
    void authManager_returnsAuthenticationManager() throws Exception {
        AuthenticationManager mockManager = mock(AuthenticationManager.class);
        AuthenticationConfiguration config = mock(AuthenticationConfiguration.class);
        when(config.getAuthenticationManager()).thenReturn(mockManager);

        AuthenticationManager result = securityConfig.authManager(config);
        assertThat(result).isEqualTo(mockManager);
    }

    @Test
    void filterChain_buildsSecurityFilterChain() throws Exception {
        HttpSecurity http = mock(HttpSecurity.class, RETURNS_DEEP_STUBS);
        // No lanzará excepción, solo verifica que el método retorna algo no nulo
        SecurityFilterChain chain = securityConfig.filterChain(http);
        assertThat(chain).isNotNull();
    }
}