package studioyoga.project.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import studioyoga.project.constants.RedirConstants;
import studioyoga.project.model.Rol;
import studioyoga.project.model.User;
import studioyoga.project.repository.RolRepository;
import studioyoga.project.repository.UserRepository;
import studioyoga.project.service.RolService;
import studioyoga.project.service.UserService;

/**
 * Controlador para la gestión de usuarios en el panel de administración.
 * <p>
 * Permite listar, buscar, crear, editar, eliminar usuarios, así como cambiar
 * contraseñas y gestionar imágenes de perfil.
 * Todas las rutas de este controlador están bajo el prefijo "/admin/users".
 */
@Controller
@RequestMapping("/admin/users")
public class UserController {

    private final UserService userService;
    private final RolService rolService;
    private final UserRepository userRepository;
    private final RolRepository rolRepository;

    /**
     * Constructor que inyecta los servicios y repositorios necesarios.
     *
     * @param userService    Servicio de usuarios.
     * @param rolService     Servicio de roles.
     * @param userRepository Repositorio de usuarios.
     * @param rolRepository  Repositorio de roles.
     */
    public UserController(UserService userService,
            RolService rolService,
            UserRepository userRepository,
            RolRepository rolRepository) {
        this.userService = userService;
        this.rolService = rolService;
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
    }

    // ============ MÉTODOS PRINCIPALES ============

    /**
     * Muestra la lista de usuarios, permite filtrar por nombre/apellido y rol.
     *
     * @param name  Nombre o apellido para filtrar (opcional).
     * @param role  Rol para filtrar (opcional).
     * @param page  Número de página.
     * @param size  Tamaño de página.
     * @param all   Si es true, muestra todos los usuarios.
     * @param model Modelo para pasar datos a la vista.
     * @return Vista de administración de usuarios.
     */
    @GetMapping("/manage-user")
    public String listUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String role,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Boolean all,
            Model model) {
        if (Boolean.TRUE.equals(all)) {
            name = null;
            role = null;
            page = 0;
        }
        // Normaliza parámetros vacíos a null
        if (name != null && name.trim().isEmpty())
            name = null;
        if (role != null && role.trim().isEmpty())
            role = null;

        List<User> users = userService.findUsersBySurnameAndNameAndRole(name, role);
        List<Rol> roles = rolService.findAll();
        Pageable pageable = PageRequest.of(page, size);
        Page<User> usersPage = userService.findUsersByFilters(name, role, pageable);

        model.addAttribute("usersPage", usersPage);
        model.addAttribute("users", users);
        model.addAttribute("name", name);
        model.addAttribute("role", role);
        model.addAttribute("roles", roles);

        // Mensaje si no hay resultados
        boolean searchPerformed = (name != null && !name.isEmpty()) || (role != null && !role.isEmpty());
        if (searchPerformed && users.isEmpty()) {
            model.addAttribute("info", "No existen usuarios con ese criterio");
        }

        return "admin/manage-user";
    }

    // ============ CREACIÓN/EDICIÓN ============

    /**
     * Muestra el formulario para crear un nuevo usuario.
     *
     * @param model Modelo para pasar datos a la vista.
     * @return Vista del formulario de creación/edición de usuario.
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", rolRepository.findAll());
        return "admin/form-create-edit-users";
    }

    /**
     * Guarda un nuevo usuario o actualiza uno existente, gestionando la imagen de
     * perfil.
     *
     * @param user               Objeto User a guardar.
     * @param file               Archivo de imagen de perfil.
     * @param redirectAttributes Atributos para mensajes flash en la redirección.
     * @return Redirección a la vista de administración de usuarios.
     * @throws IOException Si ocurre un error al guardar la imagen.
     */
    @PostMapping("/save")
    public String saveUser(
            @ModelAttribute User user,
            @RequestParam("profilePictureFile") MultipartFile file,
            RedirectAttributes redirectAttributes) throws IOException {
        // Lógica de subida de imagen
        if (!file.isEmpty()) {
            String uploadDir = "src/main/resources/static/images/profile-pictures/";
            String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Files.copy(file.getInputStream(),
                    Paths.get(uploadDir + fileName),
                    StandardCopyOption.REPLACE_EXISTING);
            user.setProfilePicture("/images/profile-pictures/" + fileName);
        }
        // Comprobación de email duplicado solo en creación (no en edición)
        if (user.getId() == null && userRepository.findByEmail(user.getEmail()).isPresent()) {
            redirectAttributes.addFlashAttribute("error", "El correo ya está registrado.");
            return "redirect:/admin/users/new";
        }
        // Mantener contraseña si es edición
        if (user.getId() != null && user.getPassword().isEmpty()) {
            User existingUser = userRepository.findById(user.getId()).orElseThrow();
            user.setPassword(existingUser.getPassword());
        }
        try {
            userService.save(user);
            redirectAttributes.addFlashAttribute("success",
                    user.getId() == null ? "Usuario creado correctamente" : "Usuario actualizado correctamente");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "El correo ya está registrado.");
            return user.getId() == null ? "redirect:/admin/users/new" : "redirect:/admin/users/edit/" + user.getId();
        }
        return RedirConstants.REDIRECT_ADMIN_USERS;
    }

    /**
     * Muestra el formulario de edición para un usuario existente.
     *
     * @param id    ID del usuario a editar.
     * @param model Modelo para pasar datos a la vista.
     * @return Vista del formulario de edición de usuario o redirección si no se
     *         encuentra.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            model.addAttribute("error", "Usuario no encontrado");
            return RedirConstants.REDIRECT_ADMIN_USERS;
        }

        model.addAttribute("user", user);
        model.addAttribute("roles", rolRepository.findAll());
        return "admin/form-create-edit-users";
    }

    // ============ BORRADO ============

    /**
     * Muestra una página de confirmación antes de eliminar un usuario.
     *
     * @param id    ID del usuario a eliminar.
     * @param model Modelo para pasar datos a la vista.
     * @return Vista de confirmación de eliminación o redirección si no se encuentra
     *         el usuario.
     */
    @GetMapping("/confirm-delete/{id}")
    public String confirmDelete(@PathVariable Integer id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            model.addAttribute("error", "Usuario no encontrado");
            return RedirConstants.REDIRECT_ADMIN_USERS;
        }

        model.addAttribute("message",
                "¿Seguro que quieres eliminar al usuario: " +
                        user.getFirstLastName() + " " + user.getSecondLastName() + "?");
        model.addAttribute("action", "/admin/users/delete/" + id);
        model.addAttribute("cancelUrl", "/admin/users/manage-user");

        return "admin/confirm-delete";
    }

    /**
     * Elimina un usuario y todas sus reservas asociadas.
     *
     * @param id                 ID del usuario a eliminar.
     * @param redirectAttributes Atributos para mensajes flash en la redirección.
     * @return Redirección a la vista de administración de usuarios.
     */
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUserAndReservations(id);
            redirectAttributes.addFlashAttribute("success", "Usuario eliminado correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el usuario: " + e.getMessage());
        }
        return RedirConstants.REDIRECT_ADMIN_USERS;
    }

    // ============ CAMBIO DE CONTRASEÑA ============

    /**
     * Cambia la contraseña del usuario autenticado.
     *
     * @param newPassword        Nueva contraseña.
     * @param principal          Usuario autenticado.
     * @param redirectAttributes Atributos para mensajes flash en la redirección.
     * @return Redirección al perfil del usuario.
     */
    @PostMapping("/change-password")
    public String changePassword(@RequestParam String newPassword,
            Principal principal,
            RedirectAttributes redirectAttributes) {
        userService.updatePassword(principal.getName(), newPassword);
        redirectAttributes.addFlashAttribute("success", "Contraseña actualizada correctamente");
        return "redirect:/profile";
    }
}