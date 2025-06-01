package studioyoga.project.constants;

/**
 * Clase de utilidades que contiene constantes para redirecciones utilizadas en
 * los controladores de la aplicación.
 * <p>
 * Cada constante representa una URL de redirección típica para las secciones de
 * administración
 * (usuarios, blog, eventos y guía).
 * <p>
 * Esta clase no puede ser instanciada.
 */
public class RedirConstants {

	/** Redirección a la gestión de usuarios en administración. */
	public static final String REDIRECT_ADMIN_USERS = "redirect:/admin/users/manage-user";
	/** Redirección a la gestión de publicaciones del blog en administración. */
	public static final String REDIRECT_ADMIN_BLOG = "redirect:/admin/blog/manage-blog";
	/** Redirección a la gestión de eventos en administración. */
	public static final String REDIRECT_ADMIN_EVENTS = "redirect:/admin/events/manage-events";
	/** Redirección a la gestión de la guía en administración. */
	public static final String REDIRECT_ADMIN_GUIDE = "redirect:/admin/guide/manage-guide";

	/**
	 * Constructor privado para evitar la instanciación de la clase de utilidades.
	 */
	private RedirConstants() {

		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}
}
