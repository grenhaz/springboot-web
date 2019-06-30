
package org.obarcia.springboot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.obarcia.springboot.models.entity.article.Article;
import org.obarcia.springboot.models.entity.article.Comment;
import org.obarcia.springboot.models.entity.user.User;
import org.obarcia.springboot.repositories.ArticleRepository;
import org.obarcia.springboot.repositories.CommentRepository;
import org.obarcia.springboot.repositories.UserRepository;
import org.obarcia.springboot.services.ArticleService;
import org.obarcia.springboot.services.UserService;
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

import static org.assertj.core.api.Assertions.*;
import org.obarcia.springboot.controllers.WebController;
import org.obarcia.springboot.models.forms.article.CommentForm;
import org.springframework.http.MediaType;
import static org.hamcrest.Matchers.*;
import static org.mockito.hamcrest.MockitoHamcrest.argThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
     * Repositorio de comentarios.
     */
    @MockBean
    public CommentRepository commentRepository;
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
     * Controlador.
     */
    @Autowired
    private WebController webController;
    
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
        List<Article> emptyList = new ArrayList<>();
        List<Article> list = new ArrayList<>();
        list.add(article);
        List<Comment> emptyListComments = new ArrayList<>();
        List<Comment> listComments = new ArrayList<>();
        listComments.add(comment);
        
        // Repositorios
        // Artículos
        Mockito.when(articleRepository.count()).thenReturn(1L);
        Mockito.when(articleRepository.findAll(Mockito.any(Pageable.class))).thenReturn(list);
        Mockito.when(articleRepository.findByFilter((Map<String, Object>) argThat(allOf(hasEntry("tag", (Object)"pc"), not(hasEntry("type", (Object)"test")))), (Pageable) argThat(hasProperty("offset", is(0L))))).thenReturn(list);
        Mockito.when(articleRepository.findByFilter((Map<String, Object>) argThat(allOf(hasEntry("tag", (Object)"pc"), not(hasEntry("type", (Object)"test")))), (Pageable) argThat(hasProperty("offset", is(1L))))).thenReturn(emptyList);
        Mockito.when(articleRepository.countByFilter((Map<String, Object>) argThat(allOf(hasEntry("tag", (Object)"pc"), not(hasEntry("type", (Object)"test")))))).thenReturn(1L);
        Mockito.when(articleRepository.findByFilter((Map<String, Object>) argThat(hasEntry("tag", (Object)"test")), Mockito.any(Pageable.class))).thenReturn(emptyList);
        Mockito.when(articleRepository.countByFilter((Map<String, Object>) argThat(hasEntry("tag", (Object)"test")))).thenReturn(0L);
        Mockito.when(articleRepository.findByFilter((Map<String, Object>) argThat(hasEntry("type", (Object)"test")), Mockito.any(Pageable.class))).thenReturn(emptyList);
        Mockito.when(articleRepository.countByFilter((Map<String, Object>) argThat(hasEntry("type", (Object)"test")))).thenReturn(0L);
        Mockito.when(articleRepository.findById(Mockito.eq(1))).thenReturn(Optional.of(article));
        Mockito.when(articleRepository.findById(Mockito.eq(2))).thenReturn(Optional.empty());
        // Comentarios
        Mockito.when(commentRepository.findByArticle(Mockito.eq(1), (Pageable) argThat(hasProperty("offset", is(0L))))).thenReturn(listComments);
        Mockito.when(commentRepository.findByArticle(Mockito.eq(1), (Pageable) argThat(hasProperty("offset", is(1L))))).thenReturn(emptyListComments);
        Mockito.when(commentRepository.countByArticle(Mockito.eq(1))).thenReturn(1L);
        Mockito.when(commentRepository.findByArticle(Mockito.eq(2), Mockito.any(Pageable.class))).thenReturn(emptyListComments);
        Mockito.when(commentRepository.countByArticle(Mockito.eq(2))).thenReturn(0L);
        // Usuarios
        Mockito.when(userRepository.findByNickname(Mockito.eq("admin"))).thenReturn(user);
        Mockito.when(userRepository.findByEmail(Mockito.eq("admin@test.com"))).thenReturn(user);
    }
    /**
     * Contexto.
     * @throws Exception 
     */
    @Test
    public void contextLoads() throws Exception
    {
        assertThat(userService).isNotNull();
        assertThat(articleService).isNotNull();
        assertThat(webController).isNotNull();
    }
    /**
     * Prueba de acceso simple sin usuario (Ok)
     * @throws Throwable 
     */
    @Test
    public void accessIndexAnonymous() throws Throwable
    {
        mvc
            .perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("articles/index"));
    }
    /**
     * Prueba de acceso simple como usuario (Ok)
     * @throws Throwable 
     */
    @Test
    @WithMockUser(username = "admin@test.com", roles = {"USER"})
    public void accessIndexUser() throws Throwable
    {
        mvc
            .perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("articles/index"));
    }
    /**
     * Prueba de acceso por etiqueta (Ok)
     * @throws Throwable 
     */
    @Test
    public void accessIndexTag() throws Throwable
    {
        mvc
            .perform(get("/web/pc"))
            .andExpect(status().isOk())
            .andExpect(view().name("articles/index"));
    }
    /**
     * Prueba de acceso por etiqueta y tipo (Ok)
     * @throws Throwable 
     */
    @Test
    public void accessIndexTagType() throws Throwable
    {
        mvc
            .perform(get("/web/pc/new"))
            .andExpect(status().isOk())
            .andExpect(view().name("articles/articles"));
    }
    /**
     * Buscar artículos por etiqueta (Ok)
     * @throws Throwable 
     */
    @Test
    public void accessSearchTagOk() throws Throwable
    {
        mvc
            .perform(get("/web/search/pc?t=Test"))
            .andExpect(status().isOk())
            .andExpect(view().name("articles/search"));
    }
    /**
     * Prueba de acceso a artículo (Ok)
     * @throws Throwable 
     */
    @Test
    public void accessArticleOk() throws Throwable
    {
        mvc
            .perform(get("/article/1"))
            .andExpect(status().isOk())
            .andExpect(view().name("articles/article"));
    }
    /**
     * Prueba de acceso a artículo (404)
     * @throws Throwable 
     */
    @Test
    public void accessArticleNotFound() throws Throwable
    {
        mvc
            .perform(get("/article/2"))
            .andExpect(status().isNotFound());
    }
    /**
     * Prueba de escribir un comentario (Ok)
     * @throws Throwable 
     */
    @Test
    @WithMockUser(username = "admin@test.com", roles = { "USER" })
    public void accessArticleAddCommentOk() throws Throwable
    {
        CommentForm form = new CommentForm();
        form.setContent("Text Comment");
        
        mvc
            .perform(post("/article/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .with(csrf())
                .param("content", "Text Comment")
            )
            .andExpect(status().isOk())
            .andExpect(model().hasNoErrors());
    }
    /**
     * Prueba de escribir un comentario (Ok)
     * @throws Throwable 
     */
    @Test
    public void accessArticleAddCommentErrorCsrf() throws Throwable
    {
        mvc
            .perform(post("/article/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
            .andExpect(status().isForbidden());
    }
    /**
     * Prueba de escribir un comentario (Ok)
     * @throws Throwable 
     */
    @Test
    public void accessArticleAddCommentErrorUser() throws Throwable
    {
        mvc
            .perform(post("/article/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .with(csrf()))
            .andExpect(status().isUnauthorized());
    }
    /**
     * Prueba de escribir un comentario (Ok)
     * @throws Throwable 
     */
    @Test
    @WithMockUser(username = "admin@test.com", roles = { "USER" })
    public void accessArticleAddCommentErrorNotFound() throws Throwable
    {
        mvc
            .perform(post("/article/2")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .with(csrf()))
            .andExpect(status().isNotFound());
    }
    /**
     * Listado de comentarios (Ok).
     * @throws Throwable 
     */
    @Test
    public void accessArticleCommentsOk() throws Throwable
    {
        mvc
            .perform(get("/ajax/comments/1"))
            .andExpect(status().isOk())
            .andExpect(view().name("articles/comments.ajax"))
            .andExpect(model()
                .attribute("comments", 
                    allOf(
                        hasProperty("records", hasSize(1)),
                        hasProperty("records", 
                            hasItem(
                                allOf(
                                   hasProperty("id", is(1))
                                )
                            )
                        ),
                        hasProperty("total", is(1L))
                    )
                )
            );
    }
    /**
     * Listado de comentarios de un artículo que no existe (Sin resultados).
     * @throws Throwable 
     */
    @Test
    public void accessArticleCommentsNotFound() throws Throwable
    {
        mvc
            .perform(get("/ajax/comments/2"))
            .andExpect(status().isOk())
            .andExpect(view().name("articles/comments.ajax"))
            .andExpect(model()
                .attribute("comments", 
                    allOf(
                        hasProperty("records", hasSize(0)),
                        hasProperty("total", is(0L))
                    )
                )
            );
    }
    /**
     * Listado de comentarios de un artículo pero en segunda página (Sin resultados).
     * @throws Throwable 
     */
    @Test
    public void accessArticleCommentsPageEmpty() throws Throwable
    {
        mvc
            .perform(get("/ajax/comments/1/2"))
            .andExpect(status().isOk())
            .andExpect(view().name("articles/comments.ajax"))
            .andExpect(model()
                .attribute("comments", 
                    allOf(
                        hasProperty("records", hasSize(0)),
                        hasProperty("total", is(1L))
                    )
                )
            );
    }
    /**
     * Búsqueda de artículos (Ok).
     * @throws Throwable 
     */
    @Test
    public void accessSearchArticles() throws Throwable
    {
        mvc
            .perform(get("/ajax/search/pc?t=Test"))
            .andExpect(status().isOk())
            .andExpect(view().name("articles/articles.search.ajax"))
            .andExpect(model()
                .attribute("articles", 
                    allOf(
                        hasProperty("records", hasSize(1)),
                        hasProperty("records", 
                            hasItem(
                                allOf(
                                   hasProperty("id", is(1))
                                )
                            )
                        ),
                        hasProperty("total", is(1L))
                    )
                )
            );
    }
    /**
     * Búsqueda de artículos (Sin resultados).
     * @throws Throwable 
     */
    @Test
    public void accessSearchArticlesEmpty() throws Throwable
    {
        mvc
            .perform(get("/ajax/search/test?t=Test"))
            .andExpect(status().isOk())
            .andExpect(view().name("articles/articles.search.ajax"))
            .andExpect(model()
                .attribute("articles", 
                    allOf(
                        hasProperty("records", hasSize(0)),
                        hasProperty("total", is(0L))
                    )
                )
            );;
    }
    /**
     * Búsqueda de artículos (Ok).
     * @throws Throwable 
     */
    @Test
    public void accessSearchArticlesPage() throws Throwable
    {
        mvc
            .perform(get("/ajax/search/pc/1?t=Test"))
            .andExpect(status().isOk())
            .andExpect(view().name("articles/articles.search.ajax"))
            .andExpect(model()
                .attribute("articles", 
                    allOf(
                        hasProperty("records", hasSize(1)),
                        hasProperty("records", 
                            hasItem(
                                allOf(
                                   hasProperty("id", is(1))
                                )
                            )
                        ),
                        hasProperty("total", is(1L))
                    )
                )
            );
    }
    /**
     * Búsqueda de artículos (Sin resultados).
     * @throws Throwable 
     */
    @Test
    public void accessSearchArticlesPageEmpty() throws Throwable
    {
        mvc
            .perform(get("/ajax/search/pc/2?t=Test"))
            .andExpect(status().isOk())
            .andExpect(view().name("articles/articles.search.ajax"))
            .andExpect(model()
                .attribute("articles", 
                    allOf(
                        hasProperty("records", hasSize(0)),
                        hasProperty("total", is(1L))
                    )
                )
            );
    }
    /**
     * Búsqueda de artículos (Ok).
     * @throws Throwable 
     */
    @Test
    public void accessArticlesTagType() throws Throwable
    {
        mvc
            .perform(get("/ajax/pc/new"))
            .andExpect(status().isOk())
            .andExpect(view().name("articles/articles.ajax"))
            .andExpect(model()
                .attribute("articles", 
                    allOf(
                        hasProperty("records", hasSize(1)),
                        hasProperty("records", 
                            hasItem(
                                allOf(
                                   hasProperty("id", is(1))
                                )
                            )
                        ),
                        hasProperty("total", is(1L))
                    )
                )
            );
    }
    /**
     * Búsqueda de artículos (Sin resultados).
     * @throws Throwable 
     */
    @Test
    public void accessArticlesTagTypeEmpty() throws Throwable
    {
        mvc
            .perform(get("/ajax/pc/test"))
            .andExpect(status().isOk())
            .andExpect(view().name("articles/articles.ajax"))
            .andExpect(model()
                .attribute("articles", 
                    allOf(
                        hasProperty("records", hasSize(0)),
                        hasProperty("total", is(0L))
                    )
                )
            );
    }
    /**
     * Búsqueda de artículos (Ok).
     * @throws Throwable 
     */
    @Test
    public void accessArticleTagTypesPage() throws Throwable
    {
        mvc
            .perform(get("/ajax/pc/new/1"))
            .andExpect(status().isOk())
            .andExpect(view().name("articles/articles.ajax"))
            .andExpect(model()
                .attribute("articles",
                    allOf(
                        hasProperty("records", hasSize(1)),
                        hasProperty("records", 
                            hasItem(
                                allOf(
                                   hasProperty("id", is(1))
                                )
                            )
                        ),
                        hasProperty("total", is(1L))
                    )
                )
            );
    }
    /**
     * Búsqueda de artículos (Sin resultados).
     * @throws Throwable 
     */
    @Test
    public void accessArticlesTagTypePageEmpty() throws Throwable
    {
        mvc
            .perform(get("/ajax/pc/new/2"))
            .andExpect(status().isOk())
            .andExpect(view().name("articles/articles.ajax"))
            .andExpect(model()
                .attribute("articles", 
                    allOf(
                        hasProperty("records", hasSize(0)),
                        hasProperty("total", is(1L))
                    )
                )
            );
    }
}