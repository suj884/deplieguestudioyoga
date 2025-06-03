package studioyoga.project.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import studioyoga.project.model.Rol;
import studioyoga.project.model.User;
import studioyoga.project.repository.UserRepository;
import studioyoga.project.security.TestUserDetails;
import studioyoga.project.service.RolService;
import studioyoga.project.service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

@MockBean
private UserService userService;

@MockBean
private RolService rolService;

@MockBean
private UserRepository userRepository;

@MockBean
private studioyoga.project.repository.RolRepository rolRepository;

@Test
void testGetUsers() throws Exception {
    User user1 = new User();
    user1.setId(1);
    user1.setName("Juan");
    Rol rol1 = new Rol();
    rol1.setName("ADMIN");
    user1.setRol(rol1);

    User user2 = new User();
    user2.setId(2);
    user2.setName("Ana");
    Rol rol2 = new Rol();
    rol2.setName("USER");
    user2.setRol(rol2);

    List<User> mockUsers = List.of(user1, user2);
    Page<User> mockPage = new PageImpl<>(mockUsers, PageRequest.of(0, 10), mockUsers.size());
    when(userService.findUsersByFilters(any(), any(), any())).thenReturn(mockPage);

    mockMvc.perform(get("/admin/users/manage-user")
        .with(user(new TestUserDetails("admin", "/images/default-avatar.png", "ADMIN"))))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("usersPage"));
}

    @Test
    public void testDeleteUser() throws Exception {
          doNothing().when(userService).deleteUserAndReservations(anyInt());
        mockMvc.perform(post("/admin/users/delete/1")
                .with(user(new TestUserDetails("admin", "/images/default-avatar.png", "ADMIN")))
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection()); // Suponiendo que redirige tras borrar
    }
}
