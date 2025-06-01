package studioyoga.project.service;

import org.springframework.stereotype.Service;

import studioyoga.project.model.Ticket;
import studioyoga.project.repository.TicketRepository;

import java.util.List;

/**
 * Servicio para la gestión de tickets o incidencias reportadas por los
 * usuarios.
 * Proporciona métodos para listar, guardar, actualizar el estado y contar
 * tickets.
 */
@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    /**
     * Constructor que inyecta el repositorio de tickets.
     *
     * @param ticketRepository Repositorio de tickets.
     */
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    /**
     * Obtiene la lista de todos los tickets registrados.
     *
     * @return Lista de tickets.
     */
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    /**
     * Guarda un ticket nuevo o actualiza uno existente.
     *
     * @param ticket Ticket a guardar o actualizar.
     * @return El ticket guardado.
     */
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    /**
     * Cambia el estado de un ticket identificado por su ID.
     *
     * @param id          ID del ticket.
     * @param nuevoEstado Nuevo estado a establecer.
     * @throws java.util.NoSuchElementException si no se encuentra el ticket.
     */
    public void changeStatus(Long id, String nuevoEstado) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow();
        ticket.setEstado(nuevoEstado);
        ticketRepository.save(ticket);
    }

    /**
     * Cuenta el número total de tickets registrados.
     *
     * @return Número total de tickets.
     */
    public long countAllTickets() {
        return ticketRepository.count();
    }
}
