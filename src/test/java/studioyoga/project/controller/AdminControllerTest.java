package studioyoga.project.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import studioyoga.project.repository.UserRepository;
import studioyoga.project.service.RolService;
import studioyoga.project.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(AdminController.class)
@org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc(addFilters = false)
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private studioyoga.project.repository.EventRepository eventRepository;
    @MockBean
    private studioyoga.project.repository.BlogPostRepository blogPostRepository;
    @MockBean
    private studioyoga.project.repository.ClassRepository classRepository;
    @MockBean
    private studioyoga.project.repository.ReservationRepository reservationRepository;
    @MockBean
    private studioyoga.project.repository.GuideSectionRepository guideSectionRepository;
    @MockBean
    private studioyoga.project.repository.TicketRepository ticketRepository;

    // Si tu AdminController depende de servicios, también debes mockearlos
    @MockBean
    private UserService userService;
    @MockBean
    private RolService rolService;

    @Test
    void testAdminDashboard() throws Exception {
        // Mock de los métodos count() para evitar NullPointerException
        org.mockito.Mockito.when(userRepository.count()).thenReturn(10L);
        org.mockito.Mockito.when(eventRepository.count()).thenReturn(5L);
        org.mockito.Mockito.when(blogPostRepository.count()).thenReturn(3L);
        org.mockito.Mockito.when(classRepository.count()).thenReturn(7L);
        org.mockito.Mockito.when(reservationRepository.count()).thenReturn(8L);
        org.mockito.Mockito.when(guideSectionRepository.count()).thenReturn(2L);
        org.mockito.Mockito.when(ticketRepository.count()).thenReturn(1L);

        mockMvc.perform(get("/admin/dash-board"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/dash-board"));
    }


}