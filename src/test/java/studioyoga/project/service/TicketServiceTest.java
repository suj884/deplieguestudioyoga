package studioyoga.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import studioyoga.project.model.Ticket;
import studioyoga.project.repository.TicketRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class TicketServiceTest {

    private TicketRepository ticketRepository;
    private TicketService ticketService;

    @BeforeEach
    void setUp() {
        ticketRepository = mock(TicketRepository.class);
        ticketService = new TicketService(ticketRepository);
    }

    @Test
    void findAll_returnsAllTickets() {
        List<Ticket> tickets = List.of(new Ticket(), new Ticket());
        when(ticketRepository.findAll()).thenReturn(tickets);

        List<Ticket> result = ticketService.findAll();

        assertThat(result).isEqualTo(tickets);
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    void save_callsRepositorySave() {
        Ticket ticket = new Ticket();
        when(ticketRepository.save(ticket)).thenReturn(ticket);

        Ticket result = ticketService.save(ticket);

        assertThat(result).isEqualTo(ticket);
        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    void changeStatus_updatesStatusAndSaves() {
        Ticket ticket = new Ticket();
        ticket.setEstado("ABIERTO");
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(ticket)).thenReturn(ticket);

        ticketService.changeStatus(1L, "CERRADO");

        assertThat(ticket.getEstado()).isEqualTo("CERRADO");
        verify(ticketRepository).save(ticket);
    }

    @Test
    void countAllTickets_returnsCount() {
        when(ticketRepository.count()).thenReturn(5L);

        long count = ticketService.countAllTickets();

        assertThat(count).isEqualTo(5L);
        verify(ticketRepository, times(1)).count();
    }
}