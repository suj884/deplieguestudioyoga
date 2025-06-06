package studioyoga.project.service;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studioyoga.project.dto.ClassesDTO;
import studioyoga.project.model.AllowedClass;
import studioyoga.project.model.Classes;
import studioyoga.project.repository.ClassRepository;
import studioyoga.project.repository.ReservationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Servicio para la gestión de clases de yoga.
 * Proporciona métodos para listar, buscar, crear, editar, eliminar clases,
 * validar horarios permitidos y comprobar disponibilidad de plazas.
 */
@Service
public class ClassesService {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepository reservationRepository;

    /**
     * Obtiene la lista de todas las clases registradas.
     *
     * @return Lista de clases.
     */
    public List<Classes> findAll() {
        return classRepository.findAll()
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Obtiene una página de clases según la paginación especificada.
     *
     * @param pageable Objeto que define la información de paginación y orden.
     * @return Página de clases correspondiente a los parámetros de paginación.
     */
    public Page<Classes> findAll(Pageable pageable) {
        return classRepository.findAll(pageable);
    }

    /**
     * Busca una clase por su ID.
     *
     * @param id ID de la clase.
     * @return Un {@link Optional} con la clase si existe, o vacío si no se
     *         encuentra.
     */
    public Optional<Classes> findById(Integer id) {
        return classRepository.findById(id);
    }

    /**
     * Guarda o actualiza una clase.
     *
     * @param classes Objeto Classes a guardar.
     * @return La clase guardada o actualizada.
     */
    public Classes save(Classes classes) {
        return classRepository.save(classes);
    }

    /**
     * Elimina una clase por su ID.
     *
     * @param id ID de la clase a eliminar.
     */
    public void deleteById(Integer id) {
        classRepository.deleteById(id);
    }

    /**
     * Elimina una clase y todas las reservas asociadas a ella.
     *
     * @param classId ID de la clase a eliminar.
     */
    public void deleteClassAndReservations(Integer classId) {
        reservationRepository.deleteByClassesId(classId); // Borra reservas asociadas
        classRepository.deleteById(classId); // Borra la clase
    }

    /**
     * Obtiene la lista de clases próximas (con fecha futura).
     *
     * @return Lista de clases próximas.
     */
    public List<Classes> findUpcomingClasses() {
        return classRepository.findByEventDateAfterOrderByEventDateAsc(java.time.LocalDate.now());
    }

    /**
     * Obtiene la lista de clases activas próximas junto con el número de plazas
     * disponibles.
     *
     * @return Lista de DTOs con la clase y plazas restantes.
     */
    public List<ClassesDTO> findUpcomingClassesWithSpots() {
        List<Classes> classesList = classRepository.findByActiveTrueOrderByEventDateAsc();
        List<ClassesDTO> result = new ArrayList<>();
        for (Classes c : classesList) {
            int reserved = reservationService.countByClassId(c.getId());
            int spotsLeft = c.getCapacity() - reserved;
            result.add(new ClassesDTO(c, spotsLeft));
        }
        return result;
    }

    /**
     * Devuelve una lista de todas las clases ordenadas por fecha de evento de forma
     * ascendente
     * y, en caso de coincidencia de fecha, por hora de inicio de forma ascendente.
     *
     * @return Lista de objetos {@link Classes} ordenados por fecha y hora de
     *         inicio.
     */
    public List<Classes> findAllOrderedByDateTime() {
        return classRepository.findAllByOrderByEventDateAscTimeInitAsc();
    }

    /**
     * Normaliza una cadena de texto eliminando los acentos y signos diacríticos.
     * Útil para comparar cadenas ignorando diferencias de acentuación.
     *
     * @param input Cadena de texto a normalizar.
     * @return Cadena normalizada sin acentos ni signos diacríticos, o null si la
     *         entrada es null.
     */
    public static String normalizeString(String input) {
        if (input == null)
            return null;
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    /**
     * Lista de combinaciones válidas de día, hora y nombre de clase.
     * Se utiliza para validar si una clase puede ser registrada en un horario
     * específico.
     */
    private static final List<AllowedClass> scheduleValid = List.of(
            new AllowedClass("LUNES", "10:00", "Yoga Suave"),
            new AllowedClass("LUNES", "18:00", "Yoga Terapeutico"),
            new AllowedClass("LUNES", "20:00", "Hatha Yoga"),
            new AllowedClass("MARTES", "09:00", "Hatha Yoga"),
            new AllowedClass("MARTES", "17:00", "Yoga Kundalini"),
            new AllowedClass("MARTES", "18:00", "Hatha Yoga"),
            new AllowedClass("MARTES", "20:00", "Vinyasa Yoga"),
            new AllowedClass("MIERCOLES", "10:00", "Yoga Suave"),
            new AllowedClass("MIERCOLES", "18:00", "Yoga Terapeutico"),
            new AllowedClass("MIERCOLES", "20:00", "Hatha Yoga"),
            new AllowedClass("JUEVES", "09:00", "Hatha Yoga"),
            new AllowedClass("JUEVES", "17:00", "Yoga Kundalini"),
            new AllowedClass("JUEVES", "18:00", "Hatha Yoga"),
            new AllowedClass("JUEVES", "20:00", "Vinyasa Yoga"),
            new AllowedClass("VIERNES", "10:00", "Hatha y Meditacion"));

    /**
     * Verifica si una combinación de día, hora y nombre de clase es válida según el
     * horario permitido.
     * Normaliza los valores recibidos para comparar sin tener en cuenta acentos ni
     * diferencias de mayúsculas/minúsculas.
     * Comprueba si existe una coincidencia exacta en la lista de combinaciones
     * válidas predefinidas.
     *
     * @param day       Día de la semana (por ejemplo, "LUNES").
     * @param time      Hora de inicio en formato "HH:mm" (por ejemplo, "10:00").
     * @param className Nombre de la clase (por ejemplo, "Yoga Suave").
     * @return true si la combinación de día, hora y clase es válida; false en caso
     *         contrario.
     */
    public boolean isAllowedSchedule(String day, String time, String className) {
        String normalizedDay = normalizeString(day.trim().toUpperCase());
        String normalizedTime = time.trim();
        String normalizedClassName = className.trim();

        return scheduleValid.stream()
                .anyMatch(ac -> normalizeString(ac.day()).equals(normalizedDay) &&
                        ac.time().equals(normalizedTime) &&
                        normalizeString(ac.className()).equalsIgnoreCase(normalizedClassName));
    }

    /**
     * Verifica si ya existe una clase registrada en una fecha y hora específicas.
     *
     * @param eventDate Fecha de la clase.
     * @param timeInit  Hora de inicio de la clase.
     * @return true si existe una clase en esa fecha y hora, false en caso
     *         contrario.
     */
    public boolean existsByDateTime(LocalDate eventDate, LocalTime timeInit) {
        return classRepository.countByEventDateAndTimeInit(eventDate, timeInit) > 0;
    }

    /**
     * Verifica si ya existe una clase registrada en una fecha y hora específicas,
     * excluyendo una clase por su ID (útil para edición).
     *
     * @param eventDate Fecha de la clase.
     * @param timeInit  Hora de inicio de la clase.
     * @param id        ID de la clase a excluir.
     * @return true si existe otra clase en esa fecha y hora, false en caso
     *         contrario.
     */
    public boolean existsByDateTimeExcludingId(LocalDate eventDate, LocalTime timeInit, Integer id) {
        return classRepository.countByEventDateAndTimeInitAndIdNot(eventDate, timeInit, id) > 0;
    }

    /**
     * Cuenta el número de reservas asociadas a una clase específica.
     *
     * @param classId ID de la clase.
     * @return Número de reservas realizadas para la clase indicada.
     */
    public int countReservationsForClass(Integer classId) {
        return reservationRepository.countByClassesId(classId);
    }

    /**
     * Verifica si se puede crear una clase en una fecha y hora determinadas con el
     * nombre especificado.
     * Comprueba que la combinación de día, hora y nombre de clase sea válida según
     * el horario permitido
     * y que no exista ya una clase registrada en esa fecha y hora.
     *
     * @param eventDate Fecha de la clase.
     * @param timeInit  Hora de inicio de la clase.
     * @param className Nombre de la clase.
     * @return true si se puede crear la clase; false en caso contrario.
     */
    public boolean canCreateClass(LocalDate eventDate, LocalTime timeInit, String className) {
        // Día de la semana en español y mayúsculas, ej: "LUNES"
        String dayOfWeek = eventDate.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("es")).toUpperCase();
        String time = timeInit.format(DateTimeFormatter.ofPattern("HH:mm"));

        return isAllowedSchedule(dayOfWeek, time, className)
                && !existsByDateTime(eventDate, timeInit);
    }

    /**
     * Crea clases semanales a partir de una fecha inicial durante un número
     * determinado de semanas.
     * Solo crea la clase si la combinación de fecha, hora y nombre es válida y no
     * existe ya una clase en ese horario.
     * Las fechas en las que no se pudo crear la clase se almacenan en una lista
     * interna.
     *
     * @param startDate     Fecha inicial para crear la primera clase.
     * @param weeksToCreate Número de semanas para crear clases.
     * @param classTime     Hora de inicio de la clase.
     * @param className     Nombre de la clase.
     */
    public void createWeeklyClasses(LocalDate startDate, int weeksToCreate, LocalTime classTime, String className) {
        List<LocalDate> notCreatedDates = new ArrayList<>();
        for (int i = 0; i < weeksToCreate; i++) {
            LocalDate classDate = startDate.plusWeeks(i);
            if (canCreateClass(classDate, classTime, className)) {
                Classes newClass = new Classes();
                newClass.setEventDate(classDate);
                newClass.setTimeInit(classTime);
                newClass.setTitle(className);
                // Completa los demás campos necesarios, por ejemplo capacidad, instructor, etc.
                save(newClass);
            } else {
                notCreatedDates.add(classDate);
            }
        }
    }
}