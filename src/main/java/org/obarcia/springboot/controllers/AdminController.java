package org.obarcia.springboot.controllers;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.obarcia.springboot.components.Utilities;
import org.obarcia.springboot.components.datatables.DataTablesResponse;
import org.obarcia.springboot.components.datatables.DataTablesRequest;
import org.obarcia.springboot.exceptions.ArticleNotFoundException;
import org.obarcia.springboot.exceptions.SaveException;
import org.obarcia.springboot.exceptions.UserNotFoundException;
import org.obarcia.springboot.models.ActionResponse;
import org.obarcia.springboot.models.entity.article.Article;
import org.obarcia.springboot.models.forms.article.ArticleForm;
import org.obarcia.springboot.models.entity.article.Comment;
import org.obarcia.springboot.models.entity.user.User;
import org.obarcia.springboot.models.entity.user.UserForm;
import org.obarcia.springboot.services.ArticleService;
import org.obarcia.springboot.services.BrowserService;
import org.obarcia.springboot.services.MailService;
import org.obarcia.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// XXX: Administración: Subir ficheros.
// XXX: Administración: Usuario: Reenviar email de activación (Colocar el botón).
// XXX: Administración: Usuario: Enviar email de recuperación de cuenta (Coloar el botón).
// XXX: Administración: Estadísticas: Artículos, Comentarios, Mas comentado
// XXX: Administración: Tablas de listados FILTERS
/**
 * Controlador para la Administración.
 * 
 * @author obarcia
 */
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController
{
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
     * Instancia del servicio de explorador de archivos.
     */
    @Autowired
    private BrowserService browserService;
    
    /**
     * Página principal.
     * @return Vista resultante.
     */
    @GetMapping("/index")
    public ModelAndView actionIndex()
    {
        return new ModelAndView("admin/index")
            .addObject("lastComments",  articleService.getLastComments(null, 10));
    }
    /**
     * Listado de usuarios.
     * @return Vista resultante.
     */
    @GetMapping("/users")
    public String actionUsers()
    {
        return "admin/users";
    }
    /**
     * Listado de usuarios para el DataTables.
     * @param request Instancia de la petición.
     * @return JSON resultante.
     */
    @GetMapping("/ajax/users")
    public @ResponseBody DataTablesResponse<User> actionUsersAjax(HttpServletRequest request)
    {
        return userService.getUsersLite(new DataTablesRequest(request));
    }
    /**
     * Listado de artículos.
     * @return Vista resultante.
     */
    @GetMapping("/articles")
    public String actionArticles()
    {
        return "admin/articles";
    }
    @GetMapping("/ajax/browser")
    public ModelAndView actionBrowserAjax(
        @RequestParam(value = "field", required = true) String field,
        @RequestParam(value = "path", required = true) String path,
        @RequestParam(value = "type", required = true) String type)
    {
        return new ModelAndView("admin/filebrowser.ajax")
            .addObject("field", field)
            .addObject("path", path)
            .addObject("type", type)
            .addObject("files", browserService.getFiles(path, type));
    }
    /**
     * Listado de artículos para el DataTables.
     * @param request Instancia de la petición.
     * @return JSON resultante.
     */
    @GetMapping("/ajax/articles")
    public @ResponseBody DataTablesResponse<Article> actionArticlesAjax(HttpServletRequest request)
    {
        return articleService.getArticlesLite(new DataTablesRequest(request));
    }
    /**
     * Listado de artículos para el DataTables.
     * @param id Identificador del artículo.
     * @param request Instancia de la petición.
     * @return JSON resultante.
     */
    @GetMapping("/ajax/comments/{id}")
    public @ResponseBody DataTablesResponse<Comment> actionArticlesAjax(
            @PathVariable("id") int id,
            HttpServletRequest request)
    {
        return articleService.getCommentsLite(id, new DataTablesRequest(request));
    }
    /**
     * Formulario de un usuario.
     * @param id Identificador del usuario.
     * @return Vista resultante.
     * @throws UserNotFoundException 
     */
    @GetMapping("/user/{id}")
    public ModelAndView actionUser(
            @PathVariable("id") int id) throws UserNotFoundException
    {
        // Buscar el usuario por id
        User user = userService.getUserById(id);
        if (user != null) {
            // Rellanar los datos del formulario con los del usuario
            UserForm form = new UserForm();
            form.setId(id);
            form.setEmail(user.getEmail());
            form.setNickname(user.getNickname());
            
            // Vista del formulario
            return new ModelAndView("admin/user")
                    .addObject("id", id)
                    .addObject("form", form);
        } else {
            // No se encontró el usuario
            throw new UserNotFoundException();
        }
    }
    /**
     * Formulario de un usuario.
     * @param id Identificador del usuario.
     * @param form Instancia del formulario.
     * @param result Resultado de la validación.
     * @param locale Localización para el i18n.
     * @param flash Flash variables.
     * @return Vista resultante.
     * @throws SaveException 
     * @throws UserNotFoundException
     */
    @PostMapping("/user/{id}")
    public ModelAndView actionUser(
            @PathVariable("id") int id,
            @Valid @ModelAttribute("form") UserForm form,
            BindingResult result,
            Locale locale,
            RedirectAttributes flash) throws SaveException, UserNotFoundException
    {
        // Obtener el usuario por id
        User user = userService.getUserById(id);
        if (user != null) {
            // Si no hay errores
            if (!result.hasErrors()) {
                // Asignar los datos del formulario
                user.setEmail(form.getEmail());
                user.setNickname(form.getNickname());
                
                // Guardar el registro
                userService.save(user);
                
                // Añadir mensaje flash (I18N)
                flash.addFlashAttribute("flash", messageSource.getMessage("message.save.ok", null, locale));

                // Redirect
                return new ModelAndView("redirect:/admin/user/" + user.getId());
            }

            // Como hay errores se muestra la vista del formulario
            return new ModelAndView("admin/user")
                    .addObject("id", id)
                    .addObject("form", form);
        } else {
            // No se encontró el usuario
            throw new UserNotFoundException();
        }
    }
    /**
     * Realizar una operación sobre el usuario.
     * @param id Identificador.
     * @param action Nombre de la acción.
     * @param request Instancia de la petición.
     * @param flash Flash variables.
     * @return Respuesta.
     * @throws UserNotFoundException
     * @throws SaveException
     */
    @PostMapping("/user/{id}/{action}")
    public @ResponseBody ActionResponse actionUserAction(
            @PathVariable("id") int id,
            @PathVariable("action") String action,
            RedirectAttributes flash,
            HttpServletRequest request) throws UserNotFoundException, SaveException
    {
        User user = userService.getUserById(id);
        if (user != null) {
            switch (action) {
                case "active":
                {
                    // Activar / Desactivar el usuario
                    Boolean value = Boolean.valueOf(request.getParameter("value"));
                    user.setActive(value);
                    
                    try {
                        // Guardar el usuario
                        userService.save(user);

                        return new ActionResponse(true);
                    } catch(SaveException e) {
                        return new ActionResponse(false, 500, "");
                    }
                }
                case "recovery":
                {
                    // Reenviar el mensaje de recuperación
                    user.setUkey(Utilities.getRandomHexString(64));

                    try {
                        // Guardar el usuario
                        userService.save(user);

                        // Enviar el mail de recuperación
                        mailService.sendmailRecovery(request, user);

                        // Redireccionar con mensaje
                        return new ActionResponse(true);
                    } catch(SaveException e) {
                        return new ActionResponse(false, 500, "");
                    }
                }
                case "activate":
                {
                    // Reenviar el mensaje de activación
                    if (user.getActive().equals(Boolean.FALSE)) {
                        user.setUkey(Utilities.getRandomHexString(64));
                        
                        try {
                            // Guardar el usuario
                            userService.save(user);

                            // Enviar el mail de recuperación
                            mailService.sendmailActivation(request, user);

                            // Redireccionar con mensaje
                            return new ActionResponse(true);
                        } catch(SaveException e) {
                            return new ActionResponse(false, 500, "");
                        }
                    }
                }
                break;
            }
            
            return new ActionResponse(false, 500, "");
        } else {
            // No se encontró el usuario
            throw new UserNotFoundException();
        }
    }
    /**
     * Formulario de un artículo.
     * @param id Identificador de lartículo.
     * @return Vista resultante.
     * @throws ArticleNotFoundException 
     */
    @GetMapping("/article/{id}")
    public ModelAndView actionArticle(
            @PathVariable("id") int id) throws ArticleNotFoundException
    {
        // Obtener el artículo por id
        ArticleForm form = new ArticleForm();
        Article article;
        if (id == 0) {
            article = new Article();
        } else {
            article = articleService.getArticle(id);
        }
        if (article != null) {
            // Rellenar el formulario
            form.setTitle(article.getTitle());
            form.setDescription(article.getDescription());
            form.setImage(article.getImage());
            form.setContent(article.getContent());
            form.setImportant(article.getImportant());
            form.setActive(article.getActive());
            form.setTags(article.getTags());
            form.setPublish(article.getPublish());
            form.setScore(article.getScore());
        } else {
            // No se encontró el artículo
            throw new ArticleNotFoundException();
        }

        // Vista con el formulario
        return new ModelAndView("admin/article")
                .addObject("id", id)
                .addObject("form", form);
    }
    /**
     * Formulario de un artículo.
     * @param id Identificador del artículo.
     * @param form Instancia del formulario.
     * @param result Resultado de la validación.
     * @param locale Localización para el i18n.
     * @param flash Flash variables.
     * @return Vista resultante.
     * @throws SaveException
     * @throws ArticleNotFoundException
     */
    @PostMapping("/article/{id}")
    public ModelAndView actionArticle(
            @PathVariable("id") int id,
            @Valid @ModelAttribute("form") ArticleForm form,
            BindingResult result,
            Locale locale,
            RedirectAttributes flash) throws SaveException, ArticleNotFoundException
    {
        // Si no hay errores
        if (!result.hasErrors()) {
            // Obtener el artículo por id
            Article article;
            if (id == 0) {
                article = new Article();
            } else {
                article = articleService.getArticle(id);
            }
            if (article != null) {
                // Rellenar el articulo
                article.setTitle(form.getTitle());
                article.setDescription(form.getDescription());
                article.setImage(form.getImage());
                article.setContent(form.getContent());
                article.setImportant(form.getImportant());
                article.setActive(form.getActive());
                article.setTags(form.getTags());
                article.setPublish(form.getPublish());
                article.setScore(form.getScore());
            } else {
                // No se encontró el artículo
                throw new ArticleNotFoundException();
            }
            
            // Guardar el artículo
            articleService.save(article);
            
            // Añadir mensaje flash (I18N)
            flash.addFlashAttribute("flash", messageSource.getMessage("message.save.ok", null, locale));

            // Redirect
            return new ModelAndView("redirect:/admin/article/" + article.getId());
        }

        // Si hubo errores se muestra el formulario
        return new ModelAndView("admin/article")
                .addObject("id", id)
                .addObject("form", form);
    }
    /**
     * Realizar una operación sobre el artículo.
     * @param id Identificador.
     * @param action Nombre de la acción.
     * @param request Instancia de la petición.
     * @return Respuesta.
     * @throws ArticleNotFoundException 
     */
    @PostMapping("/article/{id}/{action}")
    public @ResponseBody ActionResponse actionArticleAction(
            @PathVariable("id") int id,
            @PathVariable("action") String action,
            HttpServletRequest request) throws ArticleNotFoundException
    {
        Article article = articleService.getArticle(id);
        if (article != null) {
            switch (action) {
                case "active":
                {
                    // Activar / Desactivar el usuario
                    Boolean value = Boolean.valueOf(request.getParameter("value"));
                    article.setActive(value);
                    
                    try {
                        // Guardar el artículo
                        articleService.save(article);

                        return new ActionResponse(true);
                    } catch(SaveException e) {
                        return new ActionResponse(false, 500, "");
                    }
                }
                case "erase":
                {
                    // Borrar o mostrar un comentario
                    Integer commentId = Integer.parseInt(request.getParameter("comment"));
                    Comment comment = articleService.getComment(commentId);
                    if (comment != null) {
                        Boolean value = Boolean.valueOf(request.getParameter("value"));
                        comment.setErased(value);
                        try {
                            articleService.save(comment);
                            
                            return new ActionResponse(true);
                        } catch(SaveException e) {
                            return new ActionResponse(false, 500, "");
                        }
                    }
                }
            }
            return null;
        } else {
            // No se encontró el artículo
            throw new ArticleNotFoundException();
        }
    }
}