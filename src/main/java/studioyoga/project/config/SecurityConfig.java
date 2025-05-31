package studioyoga.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import studioyoga.project.service.CustomUserDetailsService;

/**
 * Configuración de seguridad para la aplicación Studio Yoga.
 * Define las reglas de acceso a rutas, la gestión de sesiones, el login, logout,
 * el manejo de contraseñas y la redirección según el rol del usuario.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

private final CustomUserDetailsService customUserDetailsService;

    // Inyecta el servicio correctamente
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    // Añade este método para configurar el AuthenticationProvider
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Configura la cadena de filtros de seguridad de Spring Security.
     * Define las rutas públicas, las rutas protegidas por rol, la gestión de sesiones,
     * la configuración de login y logout, y el manejo de excepciones.
     *
     * @param http Objeto HttpSecurity para configurar la seguridad.
     * @return SecurityFilterChain configurada.
     * @throws Exception Si ocurre un error en la configuración.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/css/**", "/js/**", "/img/**", "/font/**",
                        "/", "/classes", "/landing", "/schedule", "/prices", "/rules", "/events", "/blog", "/guide",
                        "/login", "/faq", "/location", "/forgot-password", "/reset-password")
                .permitAll()
                // Solo pides login para reservar o ver reservas
                .requestMatchers("/classes/reserve/**", "/classes/my-reservations", "/classes/cancel-reservation/**")
                .authenticated()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/user/classes/**").hasRole("USER")
                .anyRequest().permitAll()
        )
        // Gestión de sesiones: máximo 1 sesión por usuario, redirección si expira
        .sessionManagement(session -> session
            .maximumSessions(1)
            .expiredUrl("/login?expired")
        )
        // Configuración de login
        .formLogin(form -> form
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler(authenticationSuccessHandler())
                .permitAll())
        // Configuración de logout
        .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll())
        // Manejo de excepciones
        .exceptionHandling(exception -> exception
                .accessDeniedPage("/403"));

        return http.build();
    }

    /**
     * Define el manejador de éxito de autenticación.
     * Redirige a los usuarios según su rol después de iniciar sesión.
     *
     * @return AuthenticationSuccessHandler personalizado.
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            if (authentication.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
                response.sendRedirect("/admin/dash-board");
            } else if (authentication.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"))) {
                response.sendRedirect("/classes");
            }
        };
    }

    /**
     * Define el codificador de contraseñas a utilizar (BCrypt).
     *
     * @return PasswordEncoder basado en BCrypt.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Proporciona el AuthenticationManager para la autenticación personalizada.
     *
     * @param authConfig Configuración de autenticación.
     * @return AuthenticationManager configurado.
     * @throws Exception Si ocurre un error al obtener el AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}