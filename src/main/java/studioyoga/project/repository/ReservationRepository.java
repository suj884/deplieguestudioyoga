package studioyoga.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import studioyoga.project.model.Reservation;
import studioyoga.project.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Repositorio para la entidad {@link Reservation}.
 * Proporciona métodos para acceder, consultar y eliminar reservas en la base de
 * datos.
 */
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    /**
     * Busca todas las reservas realizadas por un usuario específico.
     *
     * @param user Usuario para el cual buscar las reservas.
     * @return Lista de reservas del usuario.
     */
    List<Reservation> findByUser(User user);

    /**
     * Busca todas las reservas realizadas por el ID de un usuario.
     *
     * @param userId ID del usuario.
     * @return Lista de reservas del usuario.
     */
    List<Reservation> findByUserId(Integer userId);

    /**
     * Busca todas las reservas asociadas a una clase específica por su ID.
     *
     * @param classId ID de la clase.
     * @return Lista de reservas para la clase.
     */
    List<Reservation> findByClassesId(Integer classId);

    /**
     * Busca una reserva por su ID y usuario.
     *
     * @param id   ID de la reserva.
     * @param user Usuario asociado a la reserva.
     * @return Reserva encontrada, si existe.
     */
    Optional<Reservation> findByIdAndUser(Integer id, User user);

    /**
     * Busca reservas cuyo nombre de usuario contenga el texto proporcionado, sin
     * distinguir mayúsculas o minúsculas.
     *
     * @param userName Texto a buscar en el nombre de usuario.
     * @return Lista de reservas que coinciden con el criterio.
     */
    List<Reservation> findByUserNameContainingIgnoreCase(String userName);

    /**
     * Busca reservas cuyo título de clase contenga el texto proporcionado, sin
     * distinguir mayúsculas o minúsculas.
     *
     * @param title Texto a buscar en el título de la clase.
     * @return Lista de reservas que coinciden con el criterio.
     */
    List<Reservation> findByClassesTitleContainingIgnoreCase(String title);

    /**
     * Busca reservas cuyo nombre de usuario y título de clase contengan los textos
     * proporcionados, sin distinguir mayúsculas o minúsculas.
     *
     * @param userName Texto a buscar en el nombre de usuario.
     * @param title    Texto a buscar en el título de la clase.
     * @return Lista de reservas que coinciden con ambos criterios.
     */
    List<Reservation> findByUserNameContainingIgnoreCaseAndClassesTitleContainingIgnoreCase(String userName,
            String title);

    /**
     * Elimina todas las reservas asociadas a una clase específica por su ID.
     *
     * @param classId ID de la clase.
     */
    @Transactional
    void deleteByClassesId(Integer classId);

    /**
     * Elimina todas las reservas asociadas a un usuario específico por su ID.
     *
     * @param userId ID del usuario cuyas reservas se eliminarán.
     */
    void deleteByUserId(Integer userId);

    /**
     * Cuenta el número de reservas asociadas a una clase específica por su ID.
     *
     * @param classesId ID de la clase.
     * @return Número de reservas para la clase.
     */
    int countByClassesId(Integer classesId);

    /**
     * Busca reservas cuyo nombre de usuario contenga el texto proporcionado, con
     * paginación.
     *
     * @param user     Texto a buscar en el nombre de usuario.
     * @param pageable Información de paginación.
     * @return Página de reservas que coinciden con el criterio.
     */
    Page<Reservation> findByUserNameContainingIgnoreCase(String user, Pageable pageable);

    /**
     * Busca reservas cuyo título de clase contenga el texto proporcionado, con
     * paginación.
     *
     * @param className Texto a buscar en el título de la clase.
     * @param pageable  Información de paginación.
     * @return Página de reservas que coinciden con el criterio.
     */
    Page<Reservation> findByClassesTitleContainingIgnoreCase(String className, Pageable pageable);

    /**
     * Busca reservas cuyo nombre de usuario y título de clase contengan los textos
     * proporcionados, con paginación.
     *
     * @param user      Texto a buscar en el nombre de usuario.
     * @param className Texto a buscar en el título de la clase.
     * @param pageable  Información de paginación.
     * @return Página de reservas que coinciden con ambos criterios.
     */
    Page<Reservation> findByUserNameContainingIgnoreCaseAndClassesTitleContainingIgnoreCase(String user,
            String className, Pageable pageable);
}