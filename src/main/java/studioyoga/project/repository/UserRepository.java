package studioyoga.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import studioyoga.project.model.User;

/**
 * Repositorio para la entidad {@link User}.
 * Proporciona métodos para acceder y consultar usuarios en la base de datos.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

        /**
         * Busca un usuario por su correo electrónico.
         *
         * @param email Correo electrónico del usuario a buscar.
         * @return Un {@link Optional} que contiene el usuario si existe, o vacío si no
         *         se encuentra.
         */
        Optional<User> findByEmail(String email);

        /**
         * Busca usuarios cuyo primer apellido, segundo apellido o nombre contenga el
         * texto proporcionado,
         * sin distinguir mayúsculas o minúsculas.
         *
         * @param firstLastName  Texto a buscar en el primer apellido.
         * @param secondLastName Texto a buscar en el segundo apellido.
         * @param name           Texto a buscar en el nombre.
         * @return Lista de usuarios que coinciden con alguno de los criterios.
         */
        List<User> findByFirstLastNameContainingIgnoreCaseOrSecondLastNameContainingIgnoreCaseOrNameContainingIgnoreCase(
                        String firstLastName, String secondLastName, String name);

        /**
         * Busca usuarios por el nombre de su rol.
         *
         * @param roleName Nombre del rol.
         * @return Lista de usuarios que tienen el rol especificado.
         */
        List<User> findByRol_Name(String roleName);

        @Query("SELECT u FROM User u WHERE " +
                        "(:name IS NULL OR LOWER(CONCAT(u.firstLastName, ' ', u.secondLastName, ' ', u.name)) LIKE LOWER(CONCAT('%', :name, '%'))) AND "
                        +
                        "(:role IS NULL OR u.rol.name = :role)")

        /**
         * Busca usuarios filtrando por nombre/apellidos y/o rol, con paginación.
         *
         * @param name     Nombre o apellidos para filtrar (opcional).
         * @param role     Rol para filtrar (opcional).
         * @param pageable Información de paginación.
         * @return Página de usuarios que cumplen los criterios.
         */
        Page<User> findByFilters(@Param("name") String name, @Param("role") String role, Pageable pageable);

        // Método para contar resultados
        @Query("SELECT COUNT(u) FROM User u WHERE " +
                        "(LOWER(u.firstLastName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
                        "LOWER(u.secondLastName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
                        "LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
                        "(:role IS NULL OR u.rol.name = :role)")
        long countByFilters(
                        @Param("name") String name,
                        @Param("role") String role);
}
