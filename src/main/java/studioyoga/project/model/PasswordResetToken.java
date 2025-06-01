package studioyoga.project.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * Entidad que representa un token de restablecimiento de contraseña.
 * <p>
 * Se utiliza para gestionar el proceso de recuperación de contraseña de un usuario,
 * asociando un token único y una fecha de expiración a un usuario concreto.
 */
@Entity
@Getter
@Setter
public class PasswordResetToken {
     /**
     * Identificador único del token.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Token único utilizado para el restablecimiento de contraseña.
     */
    private String token;

    /**
     * Fecha y hora de expiración del token.
     */
    private LocalDateTime expiryDate;

    /**
     * Usuario asociado al token de restablecimiento.
     */

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

  
}
