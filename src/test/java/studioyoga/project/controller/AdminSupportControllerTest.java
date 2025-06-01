package studioyoga.project.controller;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.ui.Model;

import studioyoga.project.service.TicketService;
import studioyoga.project.model.Ticket;

class AdminSupportControllerTest {

    @Mock
    private TicketService ticketService;

    @Mock
    private Model model;

    private AdminSupportController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new AdminSupportController(ticketService);
    }

@Test
void testListTickets() {
    Ticket ticket1 = mock(Ticket.class);
    Ticket ticket2 = mock(Ticket.class);
    List<Ticket> tickets = List.of(ticket1, ticket2);
    when(ticketService.findAll()).thenReturn(tickets);

    String view = controller.listTickets(model);

    verify(ticketService).findAll();
    verify(model).addAttribute("tickets", tickets);
    assertThat(view).isEqualTo("admin/support");
}

    @Test
    void testChangeStatus() {
        Long id = 42L;
        String estado = "CERRADO";

        String view = controller.changeStatus(id, estado);

        verify(ticketService).changeStatus(id, estado);
        assertThat(view).isEqualTo("redirect:/admin/support");
    }
}
