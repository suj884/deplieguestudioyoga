package studioyoga.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import studioyoga.project.model.Ticket;

/**
 * Repositorio para la entidad {@link Ticket}.
 * Proporciona métodos para acceder, consultar y eliminar tickets en la base de
 * datos.
 */
public interface TicketRepository extends JpaRepository<Ticket, Long> {

     /**
      * Elimina todos los tickets asociados a un usuario por su ID.
      *
      * @param usuarioId ID del usuario cuyos tickets serán eliminados.
      */
     void deleteByUsuario_Id(Integer usuarioId);

}