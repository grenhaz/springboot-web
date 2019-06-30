package org.obarcia.springboot;

import java.util.Optional;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.hamcrest.MockitoHamcrest.argThat;
import org.obarcia.springboot.models.entity.user.User;
import org.obarcia.springboot.repositories.UserRepository;
import org.obarcia.springboot.services.ArticleService;
import org.obarcia.springboot.services.BrowserService;
import org.obarcia.springboot.services.MailService;
import org.obarcia.springboot.services.UserAccessService;
import org.obarcia.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Pruebas del controlador de usuarios.
 * 
 * @author Heck
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserControllerTest
{
    /**
     * Control de los E-mail.
     */
    @MockBean
    public JavaMailSender emailSender;
    /**
     * Repositorio de usuarios.
     */
    @MockBean
    public UserRepository userRepository;
    /**
     * MVC de pruebas.
     */
    @Autowired
    private MockMvc mvc;
    /**
     * Instancia del Contexto del servlet.
     */
    @Autowired
    ServletContext servletContext;
    /**
     * Instancia del servicio de emails
     */
    @Autowired
    private MailService mailService;
    /**
     * Instancia del servicio i18n
     */
    @Autowired
    private MessageSource messageSource;
    /**
     * Instancia del servicio de usuarios.
     */
    @Autowired
    private UserService userService;
    /**
     * Instancia del servicio de artículos.
     */
    @Autowired
    private ArticleService articleService;
    /**
     * Instancia del servicio de login.
     */
    @Autowired
    private UserAccessService userDetailsService;
    /**
     * Instancia del servicio de explorador de archivos.
     */
    @Autowired
    private BrowserService browserService;
    
    /**
     * Setup del test.
     * 
     * @throws Exception 
     */
    @Before
    public void setUp() throws Exception
    {
        // E-MAIL
        Mockito.when(emailSender.createMimeMessage()).thenReturn(new MimeMessage((Session)null));
        
        // Usuario
        User user = new User();
        user.setId(1);
        user.setEmail("admin@test.com");
        user.setNickname("admin");
        user.setActive(Boolean.TRUE);
        user.setUserRole("ROLE_USER");
        user.setUkey("UKEYTEST");
        user.setPassword("PASSWORD");
        
        // Repositorios
        // Usuarios
        Mockito.when(userRepository.findByNickname(Mockito.eq(user.getNickname()))).thenReturn(user);
        Mockito.when(userRepository.findByNickname(Mockito.eq("testing"))).thenReturn(null);
        Mockito.when(userRepository.findByEmail(Mockito.eq(user.getEmail()))).thenReturn(user);
        Mockito.when(userRepository.findByUkey(Mockito.eq(user.getUkey()))).thenReturn(user);
        Mockito.when(userRepository.findByUkey(Mockito.eq("UKEYNONE"))).thenReturn(null);
        Mockito.when(userRepository.findById(Mockito.eq(1))).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save((User) argThat(hasProperty("id", is(1))))).thenReturn(user);
    }
    
    /**
     * Contexto.
     * @throws Exception 
     */
    @Test
    public void contextLoads() throws Exception
    {
        assertThat(servletContext).isNotNull();
        assertThat(mailService).isNotNull();
        assertThat(messageSource).isNotNull();
        assertThat(userService).isNotNull();
        assertThat(articleService).isNotNull();
        assertThat(userDetailsService).isNotNull();
        assertThat(browserService).isNotNull();
    }
    /**
     * Prueba de login sin usuario
     * @throws Throwable 
     */
    @Test
    public void accessLoginNoUser() throws Throwable
    {
        mvc
            .perform(get("/user/login"))
            .andExpect(status().isOk())
            .andExpect(view().name("user/login"));
    }
    /**
     * Prueba de login con usuario
     * @throws Throwable 
     */
    @Test
    @WithMockUser(username = "admin@test.com", roles = {"USER"})
    public void accessLoginUser() throws Throwable
    {
        mvc
            .perform(get("/user/login"))
            .andExpect(status().isUnauthorized());
    }
    /**
     * Prueba de logout sin usuario
     * @throws Throwable 
     */
    @Test
    public void accessLogoutNoUser() throws Throwable
    {
        mvc
            .perform(get("/user/logout"))
            .andExpect(status().isUnauthorized());;
    }
    /**
     * Prueba de logout con usuario
     * @throws Throwable 
     */
    @Test
    @WithMockUser(username = "admin@test.com", roles = {"USER"})
    public void accessLogoutUser() throws Throwable
    {
        mvc
            .perform(get("/user/logout"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"));
    }
    /**
     * Prueba de registro sin usuario
     * @throws Throwable 
     */
    @Test
    public void accessRegisterNoUser() throws Throwable
    {
        mvc
            .perform(get("/user/register"))
            .andExpect(status().isOk())
            .andExpect(view().name("user/register"));
    }
    /**
     * Prueba de registro con usuario
     * @throws Throwable 
     */
    @Test
    @WithMockUser(username = "admin@test.com", roles = {"USER"})
    public void accessRegisterUser() throws Throwable
    {
        mvc
            .perform(get("/user/register"))
            .andExpect(status().isUnauthorized());
    }
    /**
     * Registro sin usuario.
     * @throws Throwable 
     */
    @Test
    public void accessRegisterPostOk() throws Throwable
    {
        mvc
            .perform(post("/user/register")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .with(csrf())
                .param("email", "test2@test.com")
                .param("emailRepeat", "test2@test.com")
                .param("nickname", "testing")
            )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"))
            .andExpect(model().hasNoErrors());
    }
    /**
     * Registro con usuario.
     * @throws Throwable 
     */
    @Test
    @WithMockUser(username = "admin@test.com", roles = {"USER"})
    public void accessRegisterPostUser() throws Throwable
    {
        mvc
            .perform(post("/user/register")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .with(csrf())
            )
            .andExpect(status().isUnauthorized());
    }
    /**
     * Registro sin csrf.
     * @throws Throwable 
     */
    @Test
    public void accessRegisterPostNoCsrf() throws Throwable
    {
        mvc
            .perform(post("/user/register")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            )
            .andExpect(status().isForbidden());
    }
    /**
     * Prueba de activar la cuenta sin usuario
     * @throws Throwable 
     */
    @Test
    public void accessAccountActivateNoUser() throws Throwable
    {
        mvc
            .perform(get("/user/?k=UKEYTEST"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"));
    }
    /**
     * Prueba de activar la cuenta con usuario
     * @throws Throwable 
     */
    @Test
    @WithMockUser(username = "admin@test.com", roles = {"USER"})
    public void accessAccountActivateUser() throws Throwable
    {
        mvc
            .perform(get("/user/?k=UKEYTEST"))
            .andExpect(status().isUnauthorized());
    }
    /**
     * Prueba de recuperar sin usuario
     * @throws Throwable 
     */
    @Test
    public void accessRecoverNoUser() throws Throwable
    {
        mvc
            .perform(get("/user/recover?k=UKEYTEST"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/user/profile/password"));
    }
    /**
     * Prueba de recuperar sin usuario pero UKEY incorrecta
     * @throws Throwable 
     */
    @Test
    public void accessRecoverNoUserNone() throws Throwable
    {
        mvc
            .perform(get("/user/recover?k=UKEYNONE"))
            .andExpect(status().isNotFound());
    }
    /**
     * Prueba de recuperar con usuario
     * @throws Throwable 
     */
    @Test
    @WithMockUser(username = "admin@test.com", roles = {"USER"})
    public void accessRecoverUser() throws Throwable
    {
        mvc
            .perform(get("/user/recover?k=UKEYNONE"))
            .andExpect(status().isUnauthorized());
    }
    /**
     * Prueba de Contraseña olvidada sin usuario
     * @throws Throwable 
     */
    @Test
    public void accessForgotNoUser() throws Throwable
    {
        mvc
            .perform(get("/user/forgot"))
            .andExpect(status().isOk())
            .andExpect(view().name("user/forgot"));
    }
    /**
     * Prueba de Contraseña olvidada con usuario
     * @throws Throwable 
     */
    @Test
    @WithMockUser(username = "admin@test.com", roles = {"USER"})
    public void accessForgotUser() throws Throwable
    {
        mvc
            .perform(get("/user/forgot"))
            .andExpect(status().isUnauthorized());
    }
    /**
     * Recuperar contraseña sin usuario.
     * @throws Throwable 
     */
    @Test
    public void accessForgotPostOk() throws Throwable
    {
        mvc
            .perform(post("/user/forgot")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .with(csrf())
                .param("email", "admin@test.com")
            )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"))
            .andExpect(model().hasNoErrors());
    }
    /**
     * Recuperar contraseña con usuario.
     * @throws Throwable 
     */
    @Test
    @WithMockUser(username = "admin@test.com", roles = {"USER"})
    public void accessForgotPostUser() throws Throwable
    {
        mvc
            .perform(post("/user/forgot")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .with(csrf())
            )
            .andExpect(status().isUnauthorized());
    }
    /**
     * Recuperar contraseña sin csrf.
     * @throws Throwable 
     */
    @Test
    public void accessForgotPostNoCsrf() throws Throwable
    {
        mvc
            .perform(post("/user/forgot")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            )
            .andExpect(status().isForbidden());
    }
    /**
     * Prueba de Acceso al perfil sin usuario
     * @throws Throwable 
     */
    @Test
    public void accessProfileNoUser() throws Throwable
    {
        mvc
            .perform(get("/user/profile"))
            .andExpect(status().isUnauthorized());
    }
    /**
     * Prueba de Acceso al perfil con usuario
     * @throws Throwable 
     */
    @Test
    @WithMockUser(username = "admin@test.com", roles = {"USER"})
    public void accessProfileUser() throws Throwable
    {
        mvc
            .perform(get("/user/profile"))
            .andExpect(status().isOk())
            .andExpect(view().name("user/profile"));
    }
    /**
     * Perfil sin usuario.
     * @throws Throwable 
     */
    @Test
    public void accessProfilePostNoUser() throws Throwable
    {
        mvc
            .perform(post("/user/profile")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .with(csrf())
            )
            .andExpect(status().isUnauthorized());
    }
    /**
     * Perfil con usuario.
     * @throws Throwable 
     */
    @Test
    @WithMockUser(username = "admin@test.com", roles = {"USER"})
    public void accessProfilePostOk() throws Throwable
    {
        mvc
            .perform(post("/user/profile")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .with(csrf())
                .param("id", "1")
                .param("nickname", "testing")
                .param("avatar", "test")
            )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/user/profile"))
            .andExpect(model().hasNoErrors());
    }
    /**
     * Perfil sin csrf.
     * @throws Throwable 
     */
    @Test
        public void accessProfilePostNoCsrf() throws Throwable
    {
        mvc
            .perform(post("/user/profile")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            )
            .andExpect(status().isForbidden());
    }
    /**
     * Prueba de Acceso al perfil - contraseña sin usuario
     * @throws Throwable 
     */
    @Test
    public void accessProfilePasswordNoUser() throws Throwable
    {
        mvc
            .perform(get("/user/profile/password"))
            .andExpect(status().isUnauthorized());
    }
    /**
     * Prueba de Acceso al perfil - contraseña con usuario
     * @throws Throwable 
     */
    @Test
    @WithMockUser(username = "admin@test.com", roles = {"USER"})
    public void accessProfilePasswordUser() throws Throwable
    {
        mvc
            .perform(get("/user/profile/password"))
            .andExpect(status().isOk())
            .andExpect(view().name("user/password"));
    }
    /**
     * Recuperar contraseña sin usuario.
     * @throws Throwable 
     */
    @Test
    public void accessProfilePasswordPostNoUser() throws Throwable
    {
        mvc
            .perform(post("/user/profile/password")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .with(csrf())
            )
            .andExpect(status().isUnauthorized());
    }
    /**
     * Recuperar contraseña con usuario.
     * @throws Throwable 
     */
    @Test
    @WithMockUser(username = "admin@test.com", roles = {"USER"})
    public void accessProfilePasswordPostOk() throws Throwable
    {
        mvc
            .perform(post("/user/profile/password")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .with(csrf())
                .param("id", "1")
                .param("password", "PASSWORD")
                .param("passwordRepeat", "PASSWORD")
            )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/user/profile"))
            .andExpect(model().hasNoErrors());
    }
    /**
     * Perfil sin csrf.
     * @throws Throwable 
     */
    @Test
        public void accessProfilePasswordPostNoCsrf() throws Throwable
    {
        mvc
            .perform(post("/user/profile/password")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            )
            .andExpect(status().isForbidden());
    }
    /**
     * Prueba de Acceso a los avatares - contraseña sin usuario
     * @throws Throwable 
     */
    @Test
    public void accessAvatarsNoUser() throws Throwable
    {
        mvc
            .perform(get("/user/avatars?field=test"))
            .andExpect(status().isUnauthorized());
    }
    /**
     * Prueba de Acceso a los avatares - contraseña con usuario
     * @throws Throwable 
     */
    @Test
    @WithMockUser(username = "admin@test.com", roles = {"USER"})
    public void accessAvatarsUser() throws Throwable
    {
        mvc
            .perform(get("/user/avatars?field=test"))
            .andExpect(status().isOk())
            .andExpect(view().name("user/avatars"));
    }
}