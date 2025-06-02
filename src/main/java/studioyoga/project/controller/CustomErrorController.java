package studioyoga.project.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Controlador para manejar errores globales de la aplicación.
 * <p>
 * Permite mostrar una vista personalizada cuando ocurre un error en la aplicación.
 * Todas las rutas de error son redirigidas a "/error".
 */
@Controller
public class CustomErrorController implements ErrorController {

    /**
     * Maneja las solicitudes a la ruta "/error" y muestra una página de error personalizada.
     *
     * @return Nombre de la vista personalizada de error.
     */
    @RequestMapping("/error")
    public String handleError() {
        // Puedes devolver una vista personalizada, por ejemplo:
        return "/error";
    }
}
