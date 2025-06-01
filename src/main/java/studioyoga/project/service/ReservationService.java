package studioyoga.project.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import studioyoga.project.exception.AlreadyReservedException;
import studioyoga.project.exception.NoSpotsAvailableException;
import studioyoga.project.model.Classes;
import studioyoga.project.model.Reservation;
import studioyoga.project.model.User;
import studioyoga.project.repository.ClassRepository;
import studioyoga.project.repository.ReservationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Servicio para la gestión de reservas de clases.
 * Proporciona métodos para listar, buscar, crear, editar, eliminar, cancelar y
 * activar/desactivar reservas.
 */
@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ClassRepository classRepository;

    /**
     * Busca reservas filtrando por nombre de usuario y/o nombre de clase.
     *
     * @param usuario Nombre del usuario (opcional).
     * @param clase   Nombre de la clase (opcional).
     * @return Lista de reservas que cumplen los criterios.
     */
    public List<Reservation> findByUserOrClass(String usuario, String clase) {
        if (usuario != null && !usuario.isEmpty() && clase != null && !clase.isEmpty()) {
            return reservationRepository.findByUserNameContainingIgnoreCaseAndClassesTitleContainingIgnoreCase(usuario,
                    clase);
        } else if (usuario != null && !usuario.isEmpty()) {
            return reservationRepository.findByUserNameContainingIgnoreCase(usuario);
        } else if (clase != null && !clase.isEmpty()) {
            return reservationRepository.findByClassesTitleContainingIgnoreCase(clase);
        } else {
            return reservationRepository.findAll();
        }
    }

    /**
     * Busca una reserva por su ID.
     *
     * @param id ID de la reserva.
     * @return Un {@link Optional} con la reserva si existe, o vacío si no se
     *         encuentra.
     */
    public Optional<Reservation> findById(Integer id) {
        return reservationRepository.findById(id);
    }

    /**
     * Guarda o actualiza una reserva.
     *
     * @param reservation Reserva a guardar o actualizar.
     * @return La reserva guardada.
     */
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    /**
     * Elimina una reserva por su ID.
     *
     * @param id ID de la reserva a eliminar.
     */
    public void deleteById(Integer id) {
        reservationRepository.deleteById(id);
    }

    /**
     * Busca todas las reservas asociadas a una clase.
     *
     * @param classId ID de la clase.
     * @return Lista de reservas para la clase indicada.
     */
    public List<Reservation> findByClassId(Integer classId) {
        return reservationRepository.findByClassesId(classId);
    }

    /**
     * Busca todas las reservas realizadas por un usuario.
     *
     * @param user Usuario.
     * @return Lista de reservas del usuario.
     */
    public List<Reservation> findByUser(User user) {
        return reservationRepository.findByUser(user);
    }

    /**
     * Cuenta cuántas reservas existen para una clase.
     *
     * @param classId ID de la clase.
     * @return Número de reservas para la clase.
     */
    public int countByClassId(Integer classId) {
        return reservationRepository.findByClassesId(classId).size();
    }

    /**
     * Activa o desactiva una reserva (cambia su estado activo).
     *
     * @param id ID de la reserva a modificar.
     */
    public void toggleActive(Integer id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservation.setActive(!reservation.isActive());
        reservationRepository.save(reservation);
    }

    /**
     * Actualiza una reserva cambiando la clase reservada.
     * Solo permite modificar reservas propias.
     *
     * @param user          Usuario que modifica.
     * @param reservationId ID de la reserva.
     * @param newClassId    Nuevo ID de la clase.
     */
    public void updateReservation(User user, Integer reservationId, Integer newClassId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        if (!reservation.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You can't modify another user's reservation");
        }
        Classes newClass = classRepository.findById(newClassId)
                .orElseThrow(() -> new RuntimeException("Class not found"));
        reservation.setClasses(newClass);
        reservationRepository.save(reservation);
    }

    /**
     * Busca una reserva por su ID y usuario.
     *
     * @param reservationId ID de la reserva.
     * @param user          Usuario propietario de la reserva.
     * @return Reserva encontrada o excepción si no existe.
     */
    public Reservation findByIdAndUser(Integer reservationId, User user) {
        return reservationRepository.findByIdAndUser(reservationId, user)
                .orElseThrow(() -> new RuntimeException("Reservation not found for this user"));
    }

    /**
     * Obtiene la lista de todas las reservas.
     *
     * @return Lista de reservas.
     */
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    /**
     * Elimina todas las reservas asociadas a un usuario por su ID (solo admin).
     *
     * @param userId ID del usuario cuyas reservas se eliminarán.
     */
    @Transactional
    public void deleteByUserId(Integer userId) {
        reservationRepository.deleteByUserId(userId);
    }

    /**
     * Busca una reserva por usuario y por ID.
     *
     * @param user          Usuario propietario de la reserva.
     * @param reservationId ID de la reserva.
     * @return Reserva encontrada o null si no existe.
     */
    public Reservation findByUserAndId(User user, Integer reservationId) {
        return reservationRepository.findByIdAndUser(reservationId, user).orElse(null);
    }

    /**
     * Permite a un usuario reservar una clase si hay plazas disponibles y no tiene
     * una reserva previa.
     *
     * @param user    Usuario que realiza la reserva.
     * @param classId ID de la clase a reservar.
     * @return La clase reservada.
     * @throws NoSpotsAvailableException Si no hay plazas disponibles.
     * @throws AlreadyReservedException  Si el usuario ya tiene una reserva para esa
     *                                   clase.
     */
    public Classes reserveClass(User user, Integer classId) throws NoSpotsAvailableException, AlreadyReservedException {
        Classes yogaClass = classRepository.findById(classId)
                .orElseThrow(() -> new RuntimeException("Class not found"));

        // Check available spots
        int reservedCount = reservationRepository.findByClassesId(yogaClass.getId()).size();
        if (reservedCount >= yogaClass.getCapacity()) {
            throw new NoSpotsAvailableException();
        }

        // Check if already reserved
        boolean alreadyReserved = reservationRepository.findByUser(user).stream()
                .anyMatch(r -> r.getClasses().getId().equals(yogaClass.getId()));
        if (alreadyReserved) {
            throw new AlreadyReservedException();
        }

        // Create and save reservation
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setClasses(yogaClass);
        reservation.setDateReservation(LocalDateTime.now());
        reservationRepository.save(reservation);

        // Devuelve la clase reservada
        return yogaClass;
    }

    /**
     * Permite a un usuario cancelar una de sus reservas.
     *
     * @param user          Usuario que cancela la reserva.
     * @param reservationId ID de la reserva a cancelar.
     * @return La clase cuya reserva fue cancelada.
     * @throws RuntimeException Si la reserva no existe o no pertenece al usuario.
     */
    public Classes cancelReservation(User user, Integer reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        if (!reservation.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You can't cancel another user's reservation");
        }
        Classes claseCancelada = reservation.getClasses();
        reservationRepository.delete(reservation);
        // Devuelve la clase cancelada
        return claseCancelada;
    }

    /**
     * Obtiene una página de reservas según la paginación especificada.
     *
     * @param pageable Objeto que define la información de paginación y orden.
     * @return Página de reservas correspondiente a los parámetros de paginación.
     */
    public Page<Reservation> findAll(Pageable pageable) {
        return reservationRepository.findAll(pageable);
    }

    /**
     * Busca reservas filtrando por nombre de usuario y/o nombre de clase, con
     * paginación.
     *
     * @param user      Nombre del usuario (opcional).
     * @param className Nombre de la clase (opcional).
     * @param pageable  Objeto que define la información de paginación y orden.
     * @return Página de reservas que cumplen los criterios.
     */
    public Page<Reservation> findByUserOrClass(String user, String className, Pageable pageable) {
        if (user != null && !user.isEmpty() && className != null && !className.isEmpty()) {
            return reservationRepository.findByUserNameContainingIgnoreCaseAndClassesTitleContainingIgnoreCase(user,
                    className, pageable);
        } else if (user != null && !user.isEmpty()) {
            return reservationRepository.findByUserNameContainingIgnoreCase(user, pageable);
        } else if (className != null && !className.isEmpty()) {
            return reservationRepository.findByClassesTitleContainingIgnoreCase(className, pageable);
        } else {
            return reservationRepository.findAll(pageable);
        }
    }
}