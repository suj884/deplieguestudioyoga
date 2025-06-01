package studioyoga.project.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import studioyoga.project.model.PasswordResetToken;
import studioyoga.project.model.User;
import studioyoga.project.repository.PasswordResetTokenRepository;
import studioyoga.project.repository.UserRepository;

/**
 * Servicio para la gestión del proceso de restablecimiento de contraseña.
 * <p>
 * Permite crear tokens de restablecimiento, enviar correos electrónicos con el
 * enlace,
 * validar tokens y actualizar la contraseña del usuario.
 */
@Service
public class PasswordResetService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordResetTokenRepository tokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JavaMailSender mailSender;

    /**
     * Crea o actualiza un token de restablecimiento de contraseña para un usuario.
     *
     * @param user  Usuario para el que se crea el token.
     * @param token Token generado.
     */
    public void createPasswordResetTokenForUser(User user, String token) {
        Optional<PasswordResetToken> existingTokenOpt = tokenRepository.findByUser(user);
        PasswordResetToken myToken;
        if (existingTokenOpt.isPresent()) {
            // Actualiza el token y la fecha de expiración
            myToken = existingTokenOpt.get();
            myToken.setToken(token);
            myToken.setExpiryDate(LocalDateTime.now().plusHours(1));
        } else {
            // Crea uno nuevo
            myToken = new PasswordResetToken();
            myToken.setToken(token);
            myToken.setUser(user);
            myToken.setExpiryDate(LocalDateTime.now().plusHours(1));
        }
        tokenRepository.save(myToken);
    }

    /**
     * Envía un correo electrónico con el enlace para restablecer la contraseña.
     *
     * @param user   Usuario que solicita el restablecimiento.
     * @param token  Token de restablecimiento.
     * @param appUrl URL base de la aplicación para construir el enlace.
     */
    public void sendResetEmail(User user, String token, String appUrl) {
        String url = appUrl + "/reset-password?token=" + token;
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("Restablece tu contraseña");
        email.setText("Haz clic en el siguiente enlace para restablecer tu contraseña: " + url);
        mailSender.send(email);
    }

    /**
     * Valida si un token de restablecimiento es válido y no ha expirado.
     *
     * @param token Token a validar.
     * @return true si el token es válido y no ha expirado, false en caso contrario.
     */
    public boolean validatePasswordResetToken(String token) {
        Optional<PasswordResetToken> resetToken = tokenRepository.findByToken(token);
        return resetToken.isPresent() && resetToken.get().getExpiryDate().isAfter(LocalDateTime.now());
    }

    /**
     * Actualiza la contraseña del usuario asociado a un token válido y elimina el
     * token tras su uso.
     *
     * @param token       Token de restablecimiento.
     * @param newPassword Nueva contraseña a establecer (será codificada).
     * @throws RuntimeException si el token es inválido o ha expirado.
     */
    public void updatePassword(String token, String newPassword) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("El token ha expirado");
        }

        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword)); // ¡Aquí se codifica!
        userRepository.save(user);

        tokenRepository.delete(resetToken); // Elimina el token tras usarlo
    }

}