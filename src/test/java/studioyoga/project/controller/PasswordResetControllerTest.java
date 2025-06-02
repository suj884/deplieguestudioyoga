package studioyoga.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import studioyoga.project.model.User;
import studioyoga.project.repository.UserRepository;
import studioyoga.project.service.PasswordResetService;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PasswordResetControllerTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordResetService passwordResetService;
    @Mock
    private Model model;
    @Mock
    private RedirectAttributes redirectAttributes;
    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private PasswordResetController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void showForgotPasswordForm_returnsView() {
        String view = controller.showForgotPasswordForm();
        assertThat(view).isEqualTo("user/forgot-password");
    }

    @Test
    void processForgotPassword_whenUserExists_sendsEmailAndRedirects() {
        User user = new User();
        when(userRepository.findByEmail("test@mail.com")).thenReturn(Optional.of(user));
        when(request.getScheme()).thenReturn("http");
        when(request.getServerName()).thenReturn("localhost");
        when(request.getServerPort()).thenReturn(8080);

        String view = controller.processForgotPassword("test@mail.com", request, redirectAttributes);

        verify(passwordResetService).createPasswordResetTokenForUser(eq(user), anyString());
        verify(passwordResetService).sendResetEmail(eq(user), anyString(), contains("http://localhost:8080"));
        verify(redirectAttributes).addFlashAttribute(eq("success"), anyString());
        assertThat(view).isEqualTo("redirect:/forgot-password");
    }

    @Test
    void processForgotPassword_whenUserNotExists_onlyRedirects() {
        when(userRepository.findByEmail("notfound@mail.com")).thenReturn(Optional.empty());

        String view = controller.processForgotPassword("notfound@mail.com", request, redirectAttributes);

        verify(passwordResetService, never()).createPasswordResetTokenForUser(any(), anyString());
        verify(passwordResetService, never()).sendResetEmail(any(), anyString(), anyString());
        verify(redirectAttributes).addFlashAttribute(eq("success"), anyString());
        assertThat(view).isEqualTo("redirect:/forgot-password");
    }

    @Test
    void showResetPasswordForm_withInvalidToken_showsError() {
        when(passwordResetService.validatePasswordResetToken("badtoken")).thenReturn(false);

        String view = controller.showResetPasswordForm("badtoken", model);

        verify(model).addAttribute(eq("error"), anyString());
        assertThat(view).isEqualTo("user/reset-password");
    }

    @Test
    void showResetPasswordForm_withValidToken_showsForm() {
        when(passwordResetService.validatePasswordResetToken("goodtoken")).thenReturn(true);

        String view = controller.showResetPasswordForm("goodtoken", model);

        verify(model).addAttribute("token", "goodtoken");
        assertThat(view).isEqualTo("user/reset-password");
    }

    @Test
    void processResetPassword_whenPasswordsDoNotMatch_showsError() {
        String view = controller.processResetPassword("token", "abc", "xyz", model, redirectAttributes);

        verify(model).addAttribute(eq("error"), anyString());
        verify(model).addAttribute("token", "token");
        assertThat(view).isEqualTo("user/reset-password");
    }

    @Test
    void processResetPassword_whenPasswordsMatch_updatesAndRedirects() {
        String view = controller.processResetPassword("token", "abc", "abc", model, redirectAttributes);

        verify(passwordResetService).updatePassword("token", "abc");
        verify(redirectAttributes).addFlashAttribute(eq("success"), anyString());
        assertThat(view).isEqualTo("redirect:/login");
    }
}
