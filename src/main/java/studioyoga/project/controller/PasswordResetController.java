
package studioyoga.project.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import studioyoga.project.model.User;
import studioyoga.project.repository.UserRepository;
import studioyoga.project.service.PasswordResetService;

/**
 * Controlador para la gestión del proceso de restablecimiento de contraseña.
 * <p>
 * Permite solicitar el restablecimiento, enviar el email con el enlace y
 * actualizar la contraseña.
 * Gestiona las rutas "/forgot-password" y "/reset-password".
 */
@Controller
public class PasswordResetController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordResetService passwordResetService;

    /**
     * Muestra el formulario para solicitar el restablecimiento de contraseña.
     *
     * @return Vista del formulario de solicitud de restablecimiento.
     */
    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "user/forgot-password";
    }

    /**
     * Procesa la solicitud de restablecimiento de contraseña.
     * Si el email existe, envía un correo con el enlace de restablecimiento.
     *
     * @param email              Email del usuario.
     * @param request            Objeto HttpServletRequest para construir la URL.
     * @param redirectAttributes Atributos para mensajes flash.
     * @return Redirección al formulario de solicitud con mensaje de éxito.
     */
    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam String email, HttpServletRequest request,
            RedirectAttributes redirectAttributes) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            String token = UUID.randomUUID().toString();
            passwordResetService.createPasswordResetTokenForUser(userOpt.get(), token);
            String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            passwordResetService.sendResetEmail(userOpt.get(), token, appUrl);
        }
        redirectAttributes.addFlashAttribute("success",
                "Si el correo está registrado, recibirás instrucciones para restablecer tu contraseña.");
        return "redirect:/forgot-password";

    }

    /**
     * Muestra el formulario para establecer una nueva contraseña usando el token
     * recibido por email.
     *
     * @param token Token de restablecimiento.
     * @param model Modelo para pasar datos a la vista.
     * @return Vista del formulario de nueva contraseña o mensaje de error si el
     *         token no es válido.
     */
    @GetMapping("/reset-password")
    public String showResetPasswordForm(@RequestParam String token, Model model) {
        if (!passwordResetService.validatePasswordResetToken(token)) {
            model.addAttribute("error", "El enlace es inválido o ha expirado.");
            return "user/reset-password";
        }
        model.addAttribute("token", token);
        return "user/reset-password";
    }

    /**
     * Procesa el formulario de nueva contraseña.
     * Valida que las contraseñas coincidan y actualiza la contraseña del usuario.
     *
     * @param token              Token de restablecimiento.
     * @param newPassword        Nueva contraseña.
     * @param confirmPassword    Confirmación de la nueva contraseña.
     * @param model              Modelo para pasar datos a la vista.
     * @param redirectAttributes Atributos para mensajes flash.
     * @return Redirección al login si tiene éxito, o vuelve al formulario si hay
     *         error.
     */
    @PostMapping("/reset-password")
    public String processResetPassword(@RequestParam String token,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Las contraseñas no coinciden.");
            model.addAttribute("token", token);
            return "user/reset-password";
        }
        passwordResetService.updatePassword(token, newPassword);
        redirectAttributes.addFlashAttribute("success", "Contraseña actualizada correctamente.");
        return "redirect:/login";
    }
}
