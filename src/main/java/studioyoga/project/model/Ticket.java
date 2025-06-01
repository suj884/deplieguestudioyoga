package studioyoga.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entidad que representa un ticket o incidencia reportada por un usuario.
 * Contiene información sobre el asunto, descripción, fecha de creación, estado y el usuario que reporta la incidencia.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tickets") // Nombre de la tabla en la base de datos
public class Ticket {
        /**
     * Identificador único del ticket.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Asunto del ticket.
     */
    private String asunto;

    /**
     * Descripción del ticket.
     */
    private String descripcion;

    /**
     * Fecha y hora de creación del ticket.
     */
    private LocalDateTime fechaCreacion;

    /**
     * Estado del ticket (por ejemplo: "Abierto", "En progreso", "Cerrado").
     */
    private String estado;

    @ManyToOne
    private User usuario; // Usuario que reporta la incidencia

   
}
