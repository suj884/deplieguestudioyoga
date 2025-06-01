package studioyoga.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa una reseña de un usuario sobre Studio Yoga.
 * Contiene el texto de la reseña y el nombre del autor.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    /**
     * Texto de la reseña.
     */
    private String text;

    /**
     * Nombre del autor de la reseña.
     */
    private String author;
}