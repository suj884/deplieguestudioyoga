package studioyoga.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import studioyoga.project.model.PasswordResetToken;
import studioyoga.project.model.User;

/**
 * Repositorio para la entidad {@link PasswordResetToken}.
 * Proporciona métodos para acceder y gestionar tokens de restablecimiento de
 * contraseña en la base de datos.
 */
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    /**
     * Busca un token de restablecimiento por su valor.
     *
     * @param token Valor del token.
     * @return Un {@link Optional} con el token si existe, o vacío si no se
     *         encuentra.
     */
    Optional<PasswordResetToken> findByToken(String token);

    /**
     * Elimina el token de restablecimiento asociado a un usuario.
     *
     * @param user Usuario cuyo token se eliminará.
     */
    void deleteByUser(User user);

    /**
     * Busca un token de restablecimiento asociado a un usuario.
     *
     * @param user Usuario asociado al token.
     * @return Un {@link Optional} con el token si existe, o vacío si no se
     *         encuentra.
     */
    Optional<PasswordResetToken> findByUser(User user);

}
