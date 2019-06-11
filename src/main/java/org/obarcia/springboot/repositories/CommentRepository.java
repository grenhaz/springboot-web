package org.obarcia.springboot.repositories;

import java.util.List;
import org.obarcia.springboot.models.entity.article.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Repositorio de comentarios.
 * 
 * @author Heck
 */
public interface CommentRepository extends CrudRepository<Comment, Integer>
{
    /**
     * Listado paginado de comentarios.
     * @param pageable Instancia de la pafinación.
     * @return Listado de comentarios.
     */
    List<Comment> findAll(Pageable pageable);
    /**
     * Devuelve los comentarios por artículo.
     * @param idArticle Identificador del artículo.
     * @param pageable Instancia de la paginación.
     * @return Listado de comentarios.
     */
    @Query("SELECT c FROM Comment c WHERE c.article.id = :idArticle")
    List<Comment> findByArticle(@Param("idArticle") Integer idArticle, Pageable pageable);
    /**
     * Devuelve los comentarios por usuario.
     * @param idUser Identificador del usuario.
     * @param pageable Instancia de la paginación.
     * @return Listado de comentarios.
     */
    @Query("SELECT c FROM Comment c WHERE c.user.id = :idUser")
    List<Comment> findByUser(@Param("idUser") Integer idUser, Pageable pageable);
}