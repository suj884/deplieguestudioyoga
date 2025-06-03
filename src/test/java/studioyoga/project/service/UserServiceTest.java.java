package studioyoga.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import studioyoga.project.model.User;
import studioyoga.project.repository.TicketRepository;
import studioyoga.project.repository.UserRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private ReservationService reservationService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
   

    }

    @Test
    void testFindByEmail_UserExists() {
        User user = new User();
        user.setEmail("test@mail.com");
        when(userRepository.findByEmail("test@mail.com")).thenReturn(Optional.of(user));
        User found = userService.findByEmail("test@mail.com");
        assertEquals("test@mail.com", found.getEmail());
    }

    @Test
    void testFindByEmail_UserNotFound() {
        when(userRepository.findByEmail("notfound@mail.com")).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> userService.findByEmail("notfound@mail.com"));
    }

    @Test
    void testFindAll() {
        User u1 = new User(); u1.setId(1); u1.setEmail("a@a.com");
        User u2 = new User(); u2.setId(2); u2.setEmail("b@b.com");
        when(userRepository.findAll()).thenReturn(List.of(u1, u2, u1));
        List<User> result = userService.findAll();
        assertEquals(2, result.size());
    }

    @Test
    void testFindById_UserExists() {
        User user = new User(); user.setId(1);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        assertEquals(user, userService.findById(1));
    }

    @Test
    void testFindById_UserNotFound() {
        when(userRepository.findById(99)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> userService.findById(99));
    }

    @Test
    void testDeleteById() {
        userService.deleteById(1);
        verify(userRepository, times(1)).deleteById(1);
    }

    @Test
    void testSave_NewUser_EncryptsPassword() {
        User user = new User();
        user.setPassword("plainpassword");
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));
        userService.save(user);
        assertTrue(user.getPassword().startsWith("$2a$") || user.getPassword().startsWith("$2b$"));
    }

    @Test
    void testSave_ExistingUser_KeepsOldPassword() {
        User user = new User();
        user.setId(1);
        user.setPassword("");
        User existing = new User();
        existing.setId(1);
        existing.setPassword("$2a$encrypted");
        when(userRepository.findById(1)).thenReturn(Optional.of(existing));
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));
        userService.save(user);
        assertEquals("$2a$encrypted", user.getPassword());
    }

    @Test
    void testUpdatePassword() {
        User user = new User();
        user.setEmail("mail@mail.com");
        user.setPassword("$2a$old");
        when(userRepository.findByEmail("mail@mail.com")).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));
        userService.updatePassword("mail@mail.com", "newpass");
        assertTrue(user.getPassword().startsWith("$2a$") || user.getPassword().startsWith("$2b$"));
    }

    @Test
    void testFindUsersByFilters_NoFilters() {
        Page<User> page = new PageImpl<>(List.of());
        Pageable pageable = PageRequest.of(0, 10);
        when(userRepository.findAll(pageable)).thenReturn(page);
        assertEquals(page, userService.findUsersByFilters(null, null, pageable));
    }

    @Test
    void testFindUsersByFilters_WithFilters() {
        Page<User> page = new PageImpl<>(List.of());
        Pageable pageable = PageRequest.of(0, 10);
        when(userRepository.findByFilters("name", "role", pageable)).thenReturn(page);
        assertEquals(page, userService.findUsersByFilters("name", "role", pageable));
    }


    @Test
    void testDeleteUserAndReservations() {
        doNothing().when(ticketRepository).deleteByUsuario_Id(1);
        doNothing().when(reservationService).deleteByUserId(1);
        doNothing().when(userRepository).deleteById(1);
        userService.deleteUserAndReservations(1);
        verify(ticketRepository).deleteByUsuario_Id(1);
        verify(reservationService).deleteByUserId(1);
        verify(userRepository).deleteById(1);
    }
}
