package org.obarcia.springboot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.obarcia.springboot.models.article.Article;
import org.obarcia.springboot.models.article.Comment;
import org.obarcia.springboot.models.user.User;
import org.obarcia.springboot.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

// TODO: Pruebas unitarias y funcionales
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class WebControllerTest
{
    /**
     * Control de los E-mail.
     */
    @MockBean
    public JavaMailSender emailSender;
    /**
     * Repositorio de artículos.
     */
    @MockBean
    public ArticleRepository articleRepository;
    /**
     * MVC de pruebas.
     */
    @Autowired
    private MockMvc mvc;
    
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
        user.setNickname("admin");
        
        // Artículos
        Article article = new Article();
        article.setId(1);
        article.setTags("[PC]");
        article.setPublish(new Date());
        article.setActive(Boolean.TRUE);
        article.setImportant(Boolean.TRUE);
        article.setType("new");
        
        // Comentario
        Comment comment = new Comment();
        comment.setId(1);
        comment.setUser(user);
        comment.setArticle(article);
        article.getComments().add(comment);
        
        // Listados
        List<Article> list = new ArrayList<>();
        list.add(article);
        
        // Repositorios
        Mockito.when(articleRepository.countAll()).thenReturn((long)1);
        Mockito.when(articleRepository.findAll(Mockito.any(Pageable.class))).thenReturn(list);
    }
    @Test
    public void accessIndexAnonymous()
    {
        // TODO: Prueba de acceso simple sin usuario (Ok)
    }
    @Test
    @WithMockUser(username = "admin", roles = { "USER" })
    public void accessIndexUser()
    {
        // TODO: Prueba de acceso simple como usuario (Ok)
    }
    // TODO: Acceso a artículos por etiqueta (Ok)
    // TODO: Acceso a artículos por etiqueta (Vacío)
    // TODO: Acceso a un artículo (Ok)
    // TODO: Escribir un comentario sin usuario (Error)
    // TODO: Escribir un comentario con usuario (Ok)
    // TODO: Listado de artículos por Ajax
}