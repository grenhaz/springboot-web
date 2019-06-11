package org.obarcia.springboot.config;

import java.util.Date;
import javax.annotation.PostConstruct;
import org.obarcia.springboot.exceptions.SaveException;
import org.obarcia.springboot.models.article.Article;
import org.obarcia.springboot.models.article.Comment;
import org.obarcia.springboot.models.user.User;
import org.obarcia.springboot.services.ArticleService;
import org.obarcia.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Configuración para desarrollo.
 * 
 * @author obarcia
 */
@Configuration
@Profile("dev")
public class DevConfig
{
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
     * Inicialización para pruebas.
     */
    @PostConstruct
    public void init()
    {
        try {
            String content = "<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu</p><p>In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus.</p><p>Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nunc,</p>";
            // Usuarios
            createUser("administrador", null, "admin@test.com", "password", "ROLE_ADMIN");
            createUser("obarcia", "avatar1.jpg", "user@test.com", "password", "ROLE_USER");
            createUser("Heck", "avatar2.jpg", "yo@test.com", "password", "ROLE_USER");
            createUser("Tester", "avatar3.jpg", "yo2@test.com", "password", "ROLE_USER");
            createUser("Tester2", "avatar3.jpg", "yo3@test.com", "password", "ROLE_USER");
            createUser("Tester3", "avatar3.jpg", "yo4@test.com", "password", "ROLE_USER");
            // Artículos
            createArticle("new", "game.jpg", "Mañana es el cierre de servidores de Demon's Souls", "El clásico de PlayStation 3 cortará a partir de mañana sus posibilidades on-line definitivamente.", content, "[PC]", null, true, 3);
            createArticle("new", "game1.jpg", "El rodaje de la serie televisiva de Halo comenzaría a finales de 2018", "El afamado director Steven Spielberg continuaría vinculado al proyecto.", content, "[PC][PS4]", null, false, 5);
            createArticle("guide", "game2.jpg", "Guía de Mario Kart 8 Deluxe", "", content, "[SWITCH]", null, true, 1);
            createArticle("video", "game3.jpg", "Turok Remastered", "Turok y Turok 2 tendrán remasterización en Xbox One el 2 de marzo", content, "[PS4]", null, false, 0);
            createArticle("review", "game4.jpg", "Análisis de Into the Breach", "Estrategia implacable de los creadores de FTL", content, "[PC][PS4]", 8.5, false, 3);
            createArticle("new", "game5.jpg", "¿Filtrado Plants vs. Zombies: Garden Warfare 3? Un cómic lo desvela", "El tebeo narrará los acontecimientos entre la segunda y la tercera parte del juego.", content, "[PC][PS4]", null, false, 3);
            createArticle("video", "game6.jpg", "Devil May Cry HD Collection llega a la nueva generación. Tráiler", "Capcom estrena el 13 de marzo Devil May Cry HD Collection en PS4 y Xbox One. Supone traer de vuelta a los tiempos actuales", content, "[PS4]", null, false, 6);
            createArticle("new", "game7.jpg", "GT Sport se actualiza y suma nuevos coches y dos nuevos circuitos", "La actualización incluye doce coches y tres eventos de la GT League.", content, "[PS4]", null, false, 12);
            createArticle("new", "game8.jpg", "Las ventas de PS4 en Japón mejoran todos los años desde su estreno", "Cerró su cuarto año en el mercado nipón con 2.083.974 unidades vendidas.", content, "[PS4]", null, true, 32);
            createArticle("new", "game9.jpg", "Habrá cambios en la cúpula directiva de Sony Interactive Entertainment", "Se efectuarán a partir del próximo 1 de abril y están implicados distintos empresarios.", content, "[PS4]", null, false, 9);
            createArticle("special", "game10.jpg", "Reportaje: Juegos olvidados", "", content, "[PC]", null, false, 4);
            //createArticle(12, "new", "game.jpg", "qwe", "qwe", content, "[PC]", null, true, 3);
        } catch (SaveException ex) {}
    }
    /**
     * Crear / Actualizar un artículo.
     * @param type Tipo de artículo.
     * @param image Imagen de portada.
     * @param title Título.
     * @param description Descripción
     * @param content Contenido.
     * @param tags Etiquetas.
     * @param puntuation Puntuación.
     * @param important Si es o no imporatante / destacado.
     * @param comments Número de comentarios aleatorios.
     * @return Instancia del artículo.
     * @throws SaveException
     */
    private Article createArticle(
        String type, String image, String title, 
        String description, String content,
        String tags, Double puntuation,
        boolean important, int comments) throws SaveException
    {
        Article article = articleService.getArticleByTitle(title);
        boolean isNewRecord = false;
        if (article == null) {
            article = new Article();
            isNewRecord = true;
        }
        article.setType(type);
        article.setImage(image);
        article.setTitle(title);
        article.setDescription(description);
        article.setContent(content);
        article.setTags(tags);
        article.setScore(puntuation);
        article.setImportant(important);
        article.setPublish(new Date());
        article.setActive(true);
        
        // Guardar el artículo
        articleService.save(article);
        
        if (isNewRecord) {
            String[] messages = {
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.",
                "Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu.",
                "In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a,"
            };

            User[] users = {
                userService.getUserByEmail("user@test.com"),
                userService.getUserByEmail("yo@test.com"),
                userService.getUserByEmail("yo2@test.com"),
                userService.getUserByEmail("yo3@test.com"),
                userService.getUserByEmail("yo4@test.com"),
            };
            for (int i = 0; i < comments; i ++) {
                Comment c = new Comment();
                c.setArticle(article);
                c.setUser(users[(int)(Math.random() * 5)]);
                c.setContent(messages[(int)(Math.random() * 3)]);
                c.setPublish(new Date());
                c.setErased(false);
                articleService.save(c);
            }
        }
        
        return article;
    }
    /**
     * Crear / Actualizar un usuario.
     * @param nickname Nickname.
     * @param avatar Fichero del avatar.
     * @param email Email.
     * @param password Contraseña.
     * @param userRole Rol.
     * @return Instancia del usuario.
     * @throws SaveException
     */
    private User createUser(String nickname, String avatar, String email, String password, String userRole) throws SaveException
    {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            user = new User();
        }
        user.setNickname(nickname);
        user.setAvatar(avatar);
        user.setEmail(email);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setUserRole(userRole);
        user.setActive(Boolean.TRUE);
        
        // Guardar el usuario
        userService.save(user);
        
        return user;
    }
}