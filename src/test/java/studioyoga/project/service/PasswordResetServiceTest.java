package studioyoga.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import studioyoga.project.model.PasswordResetToken;
import studioyoga.project.model.User;
import studioyoga.project.repository.PasswordResetTokenRepository;
import studioyoga.project.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class PasswordResetServiceTest {

    private UserRepository userRepository;
    private PasswordResetTokenRepository tokenRepository;
    private PasswordEncoder passwordEncoder;
    private JavaMailSender mailSender;
    private PasswordResetService service;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        tokenRepository = mock(PasswordResetTokenRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        mailSender = mock(JavaMailSender.class);

        service = new PasswordResetService();
        // Inyectar los mocks usando reflexión
        for (var field : PasswordResetService.class.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.getType().equals(UserRepository.class)) {
                    field.set(service, userRepository);
                } else if (field.getType().equals(PasswordResetTokenRepository.class)) {
                    field.set(service, tokenRepository);
                } else if (field.getType().equals(PasswordEncoder.class)) {
                    field.set(service, passwordEncoder);
                } else if (field.getType().equals(JavaMailSender.class)) {
                    field.set(service, mailSender);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    void createPasswordResetTokenForUser_createsNewToken() {
        User user = new User();
        when(tokenRepository.findByUser(user)).thenReturn(Optional.empty());

        service.createPasswordResetTokenForUser(user, "token123");

        ArgumentCaptor<PasswordResetToken> captor = ArgumentCaptor.forClass(PasswordResetToken.class);
        verify(tokenRepository).save(captor.capture());
        PasswordResetToken saved = captor.getValue();
        assertThat(saved.getToken()).isEqualTo("token123");
        assertThat(saved.getUser()).isEqualTo(user);
        assertThat(saved.getExpiryDate()).isAfter(LocalDateTime.now());
    }

    @Test
    void createPasswordResetTokenForUser_updatesExistingToken() {
        User user = new User();
        PasswordResetToken existing = new PasswordResetToken();
        existing.setUser(user);
        existing.setToken("old");
        existing.setExpiryDate(LocalDateTime.now().minusHours(1));
        when(tokenRepository.findByUser(user)).thenReturn(Optional.of(existing));

        service.createPasswordResetTokenForUser(user, "newtoken");

        assertThat(existing.getToken()).isEqualTo("newtoken");
        assertThat(existing.getExpiryDate()).isAfter(LocalDateTime.now());
        verify(tokenRepository).save(existing);
    }

    @Test
    void sendResetEmail_sendsMail() {
        User user = new User();
        user.setEmail("test@mail.com");
        String token = "tok";
        String appUrl = "http://localhost";
        service.sendResetEmail(user, token, appUrl);

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(captor.capture());
        SimpleMailMessage msg = captor.getValue();
        assertThat(msg.getTo()).contains("test@mail.com");
        assertThat(msg.getText()).contains("reset-password?token=tok");
    }

    @Test
    void validatePasswordResetToken_validToken_returnsTrue() {
        PasswordResetToken token = new PasswordResetToken();
        token.setExpiryDate(LocalDateTime.now().plusMinutes(10));
        when(tokenRepository.findByToken("tok")).thenReturn(Optional.of(token));
        assertThat(service.validatePasswordResetToken("tok")).isTrue();
    }

    @Test
    void validatePasswordResetToken_expiredToken_returnsFalse() {
        PasswordResetToken token = new PasswordResetToken();
        token.setExpiryDate(LocalDateTime.now().minusMinutes(10));
        when(tokenRepository.findByToken("tok")).thenReturn(Optional.of(token));
        assertThat(service.validatePasswordResetToken("tok")).isFalse();
    }

    @Test
    void validatePasswordResetToken_tokenNotFound_returnsFalse() {
        when(tokenRepository.findByToken("tok")).thenReturn(Optional.empty());
        assertThat(service.validatePasswordResetToken("tok")).isFalse();
    }

    @Test
    void updatePassword_validToken_updatesPasswordAndDeletesToken() {
        User user = new User();
        PasswordResetToken token = new PasswordResetToken();
        token.setUser(user);
        token.setExpiryDate(LocalDateTime.now().plusMinutes(10));
        when(tokenRepository.findByToken("tok")).thenReturn(Optional.of(token));
        when(passwordEncoder.encode("newpass")).thenReturn("encodedpass");

        service.updatePassword("tok", "newpass");

        assertThat(user.getPassword()).isEqualTo("encodedpass");
        verify(userRepository).save(user);
        verify(tokenRepository).delete(token);
    }

    @Test
    void updatePassword_expiredToken_throwsException() {
        PasswordResetToken token = new PasswordResetToken();
        token.setExpiryDate(LocalDateTime.now().minusMinutes(10));
        when(tokenRepository.findByToken("tok")).thenReturn(Optional.of(token));

        assertThatThrownBy(() -> service.updatePassword("tok", "newpass"))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining("ha expirado");
    }

    @Test
    void updatePassword_invalidToken_throwsException() {
        when(tokenRepository.findByToken("tok")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.updatePassword("tok", "newpass"))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining("Token inválido");
    }
}