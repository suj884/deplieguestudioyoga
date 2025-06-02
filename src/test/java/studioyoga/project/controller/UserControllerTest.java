package studioyoga.project.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import studioyoga.project.model.Rol;
import studioyoga.project.model.User;
import studioyoga.project.repository.RolRepository;
import studioyoga.project.repository.UserRepository;
import studioyoga.project.security.TestUserDetails;
import studioyoga.project.service.UserService;
import studioyoga.project.service.RolService;

import java.util.Collections;
import java.util.Optional;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private RolService rolService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RolRepository rolRepository;

    TestUserDetails testUserDetails = new TestUserDetails("admin", "/images/default-avatar.png");

    private User testUser;
    private Rol testRol;

    @BeforeEach
    void setUp() {
        testRol = new Rol(1, "ADMIN");
        testUser = new User();
        testUser.setId(1);
        testUser.setEmail("test@example.com");
        testUser.setRol(testRol);
    }

    // ...imports y setup igual...

    @Test
    void listUsers_NoFilters_ReturnsPaginatedResults() throws Exception {
        Page<User> page = new PageImpl<>(Collections.singletonList(testUser));
        when(userService.findUsersByFilters(null, null, PageRequest.of(0, 10))).thenReturn(page);

        mockMvc.perform(get("/admin/users/manage-user")
        .with(user(testUserDetails))
                        .with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/manage-user"))
                .andExpect(model().attributeExists("usersPage"));
    }

    @Test
    void listUsers_WithNameFilter_ReturnsFilteredResults() throws Exception {
        when(userService.findUsersBySurnameAndNameAndRole("john", null))
                .thenReturn(Collections.singletonList(testUser));

        mockMvc.perform(get("/admin/users/manage-user")
                .param("name", "john")
                .with(csrf())
                .with(user(testUserDetails))
                .with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("users"));
    }

    @Test
    void saveUser_NewUserWithValidData_RedirectsWithSuccess() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "profilePictureFile",
                "test.jpg",
                "image/jpeg",
                "test image".getBytes());

        mockMvc.perform(multipart("/admin/users/save")
                .file(file)
                .flashAttr("user", testUser)
                .with(csrf())
                .with(user(testUserDetails))
                .with(user("admin").roles("ADMIN")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/users/manage-user"))
                .andExpect(flash().attributeExists("success"));
    }

    @Test
    void saveUser_DuplicateEmail_ReturnsError() throws Exception {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(testUser));

        mockMvc.perform(multipart("/admin/users/save")
                .file(new MockMultipartFile("profilePictureFile", new byte[0]))
                .flashAttr("user", testUser)
                .with(csrf())
                .with(user(testUserDetails))
                .with(user("admin").roles("ADMIN")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/users/new"))
                .andExpect(flash().attributeExists("error"));
    }

    @Test
    void deleteUser_ValidId_RedirectsWithSuccess() throws Exception {
        doNothing().when(userService).deleteUserAndReservations(1);

        mockMvc.perform(post("/admin/users/delete/1")
        .with(user(testUserDetails))
        .with(csrf())
                .with(user("admin").roles("ADMIN")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/users/manage-user"))
                .andExpect(flash().attributeExists("success"));
    }


}