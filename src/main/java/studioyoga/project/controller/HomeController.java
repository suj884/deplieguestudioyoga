package studioyoga.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import studioyoga.project.model.BlogPost;
import studioyoga.project.model.Event;
import studioyoga.project.model.Review;
import studioyoga.project.service.BlogService;
import studioyoga.project.service.EventService;
import studioyoga.project.service.GuideSectionService;

/**
 * Controlador para la gestión de las vistas públicas y principales del sitio
 * web.
 * <p>
 * Permite mostrar la página de inicio, login, registro, horarios, precios,
 * reglas,
 * eventos, blog, guía de yoga, preguntas frecuentes, ubicación, información
 * sobre nosotros y contacto.
 * Gestiona rutas bajo "/", "/login", "/formRegister", "/schedule", "/prices",
 * "/rules", "/events", "/event-detail/{id}", "/blog", "/blog/{id}", "/guide",
 * "/faq", "/location", "/about-us", "/form-contact".
 */
@Controller
public class HomeController {

    @Autowired
    EventService eventService;

    private final GuideSectionService guideSectionService;

    @Autowired
    BlogService blogService;

    /**
     * Constructor que inyecta el servicio de secciones de la guía.
     *
     * @param guideSectionService Servicio de secciones de la guía.
     * @param eventService        Servicio de eventos.
     * @param blogService         Servicio de blog.
     */
    public HomeController(EventService eventService, GuideSectionService guideSectionService, BlogService blogService) {
        this.eventService = eventService;
        this.guideSectionService = guideSectionService;
        this.blogService = blogService;
    }

    /**
     * Muestra la página principal con los eventos activos y las reseñas.
     *
     * @param model Modelo para pasar datos a la vista.
     * @return Vista de la página de inicio.
     */
    @GetMapping("/")
    public String home(Model model) {
        List<Review> reviews = List.of(
                new Review(
                        "Gracias a Studio Yoga he mejorado mi flexibilidad y mi bienestar general. ¡Las clases son muy completas!",
                        "Ana P."),
                new Review(
                        "El ambiente es muy acogedor y los profesores siempre están atentos a las necesidades de cada alumno.",
                        "Javier S."),
                new Review(
                        "Me encanta la variedad de clases y la profesionalidad del equipo. Recomiendo Studio Yoga a todos mis amigos.",
                        "Lucía M."),
                new Review(
                        "Las sesiones de meditación me han ayudado a reducir el estrés y a dormir mejor. ¡Muy agradecida!",
                        "Marta R."),
                new Review("Un espacio donde realmente puedo desconectar y dedicarme tiempo. ¡Siempre salgo renovada!",
                        "Sergio T."),
                new Review(
                        "Desde que asisto a Studio Yoga, he notado una gran mejoría en mi postura y en mi energía diaria.",
                        "Marcos D."),
                new Review(
                        "La atención personalizada y el trato cercano hacen que cada clase sea especial. ¡Gracias por tanto!",
                        "Paula G."),
                new Review(
                        "Studio Yoga es el lugar perfecto para empezar en el yoga o profundizar en la práctica. ¡Muy recomendable!",
                        "Diego L."),
                new Review(
                        "He encontrado un grupo maravilloso y mucha motivación para seguir cuidando de mi cuerpo y mente.",
                        "Elena F."),
                new Review(
                        "Las instalaciones son impecables y el ambiente invita a la calma desde que entras por la puerta.",
                        "Tomás V."),
                new Review(
                        "Las clases online son igual de buenas que las presenciales. ¡Muy recomendable para quienes tienen poco tiempo!",
                        "Laura H."),
                new Review(
                        "El equipo de Studio Yoga me ha ayudado a mantenerme motivado y constante en mi práctica. ¡Gracias por el apoyo!",
                        "Pedro V."),
                new Review(
                        "Me gusta mucho la variedad de horarios y la facilidad para reservar clases. Todo muy profesional.",
                        "Sonia C."),
                new Review(
                        "Desde que asisto a Studio Yoga, he notado una gran mejoría en mi postura y en mi energía diaria.",
                        "Marcos D."),
                new Review("El ambiente es relajante y siempre salgo de las clases con una sonrisa. ¡Muy agradecida!",
                        "Isabel R."));
        int slidesPerView = 3; // por ejemplo, 3*3 si slidesPerView máximo es 3
        int required = slidesPerView * 3;
        List<Review> reviewsForSwiper = new ArrayList<>();
        while (reviewsForSwiper.size() < 30) {
            reviewsForSwiper.addAll(reviews);
        }
    reviewsForSwiper = reviewsForSwiper.subList(0, required);
        model.addAttribute("reviews", reviewsForSwiper);
        model.addAttribute("events", eventService.findAllActive());
        return "user/index"; // o "index", según tu plantilla
    }

    /**
     * Muestra la página de login.
     *
     * @return Vista de login.
     */
    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    /**
     * Muestra el formulario de registro de usuario.
     *
     * @return Vista del formulario de registro.
     */
    @GetMapping("/formRegister")
    public String register() {
        return "user/formRegister";
    }

    /**
     * Muestra la página de horarios.
     *
     * @return Vista de horarios.
     */
    @GetMapping("/schedule")
    public String schedule() {
        return "user/schedule";
    }

    /**
     * Muestra la página de precios.
     *
     * @return Vista de precios.
     */
    @GetMapping("/prices")
    public String prices() {
        return "user/prices";
    }

    /**
     * Muestra la página de reglas.
     *
     * @return Vista de reglas.
     */
    @GetMapping("/rules")
    public String rules() {
        return "user/rules";
    }

    /**
     * Muestra la lista de eventos activos.
     *
     * @param model Modelo para pasar datos a la vista.
     * @return Vista de eventos.
     */
    @GetMapping("/events")
    public String events(Model model) {
        List<Event> events = eventService.findAllActive();
        model.addAttribute("events", events);
        return "user/events";
    }

    /**
     * Muestra el detalle de una publicación del blog.
     *
     * @param id    ID de la publicación.
     * @param model Modelo para pasar datos a la vista.
     * @return Vista de detalle de la publicación del blog.
     * @throws RuntimeException si no se encuentra el post.
     */
    @GetMapping("/event-detail/{id}")
    public String eventDetail(@PathVariable Integer id, Model model) {
        Optional<Event> eventOpt = eventService.findById(id); // O como accedas a tus eventos
        if (eventOpt.isPresent()) {
            model.addAttribute("event", eventOpt.get());
            return "user/events-detail";
        } else {
            return "redirect:/eventos?error=notfound";
        }
    }

    /**
     * Muestra la lista de publicaciones del blog para los usuarios.
     *
     * @param model Modelo para pasar datos a la vista.
     * @return Vista del blog.
     */
    @GetMapping("/blog")
    public String showBlog(Model model) {
        List<BlogPost> blogPosts = blogService.findAllPublishedOrdered();
        model.addAttribute("blogPosts", blogPosts);
        return "user/blog";
    }

    /**
     * Muestra el detalle de una publicación del blog.
     *
     * @param id    ID de la publicación.
     * @param model Modelo para pasar datos a la vista.
     * @return Vista de detalle de la publicación del blog.
     */
    @GetMapping("/blog/{id}")
    public String showBlogPostDetail(@PathVariable Integer id, Model model) {
        BlogPost post = blogService.findById(id)
                .orElseThrow(() -> new RuntimeException("Post no encontrado"));
        model.addAttribute("post", post);
        return "user/blog-post-detail";
    }

    /**
     * Muestra la guía de yoga.
     *
     * @param model Modelo para pasar datos a la vista.
     * @return Vista de la guía de yoga.
     */
    @GetMapping("/guide")
    public String showYogaGuide(Model model) {
        model.addAttribute("sections", guideSectionService.getAllSections());
        return "user/guide";
    }

    /**
     * Muestra la página de preguntas frecuentes.
     *
     * @return Vista de preguntas frecuentes.
     */
    @GetMapping("/faq")
    public String faq() {
        return "user/faq";
    }

    /**
     * Muestra la página de ubicación.
     *
     * @return Vista de ubicación.
     */
    @GetMapping("/location")
    public String location() {
        return "user/location";
    }

    /**
     * Muestra la página de información sobre nosotros.
     *
     * @return Vista sobre nosotros.
     */
    @GetMapping("/about-us")
    public String aboutUs() {
        return "user/about-us";
    }

    /**
     * Muestra el formulario de contacto.
     *
     * @return Vista del formulario de contacto.
     */
    @GetMapping("/form-contact")
    public String contact() {
        return "user/form-contact";
    }
/**
 * Muestra la página de Política de Cookies.
 *
 * @return Nombre de la vista correspondiente a la política de cookies.
 */
    @GetMapping("/user/cookie-policy")
    public String cookiePolicy() {
        return "user/cookie-policy";
    }

/**
 * Muestra la página de Política de Privacidad.
 *
 * @return Nombre de la vista correspondiente a la política de privacidad.
 */
@GetMapping("/user/privacy-policy")
public String showPrivacyPolicy() {
    return "user/privacy-policy";
}


}