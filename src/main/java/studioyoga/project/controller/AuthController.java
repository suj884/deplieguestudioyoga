package studioyoga.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * Controlador para la gestión de autenticación y manejo de cookies.
 * <p>
 * Permite establecer y leer cookies en el navegador del usuario mediante las rutas:
 * <ul>
 *   <li><b>/set-cookie</b>: Establece una cookie segura.</li>
 *   <li><b>/read-cookie</b>: Lee el valor de la cookie.</li>
 * </ul>
 */
@Controller
@RequiredArgsConstructor
public class AuthController {

/**
 * Establece una cookie llamada "miCookie" con un valor de ejemplo.
 * La cookie tiene una duración de 1 semana, es accesible solo por HTTP y solo en HTTPS.
 * <p>
 * Este método es solo para demostración y debe adaptarse para producción.
 *
 * @param response Objeto HttpServletResponse para añadir la cookie.
 * @return Redirección a la página principal.
 */
    @PostMapping("/set-cookie")
    public String setCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("miCookie", "valorEncriptado");
        cookie.setMaxAge(7 * 24 * 60 * 60); // 1 semana
        cookie.setPath("/");
        cookie.setHttpOnly(true); // Seguridad: solo accesible vía HTTP
        cookie.setSecure(true); // Solo en HTTPS (actívalo en producción)
        response.addCookie(cookie);
        return "redirect:/";
    }

   /**
 * Lee el valor de la cookie llamada "miCookie" y lo imprime por consola.
 *
 * @param cookieValue Valor de la cookie "miCookie" (vacío si no existe).
 * @return Nombre de la vista a mostrar (de ejemplo).
 */
    @GetMapping("/read-cookie")
    public String readCookie(@CookieValue(name = "miCookie", defaultValue = "") String cookieValue) {
        System.out.println("Valor de la cookie: " + cookieValue);
        return "vista";
    }

}