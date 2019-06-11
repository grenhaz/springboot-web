package org.obarcia.springboot.repositories;

import java.util.List;
import org.obarcia.springboot.models.article.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Repositorio de comentarios.
 * 
 * @author Heck
 */
public interface CommentRepository extends CrudRepository<Comment, Integer>
{
    @Query("SELECT count(c) FROM Comment c")
    Long countAll();
    List<Comment> findAll(Pageable pageable);
    /**
     * Devuelve los comentarios por artículo.
     * @param idArticle Identificador del artículo.
     * @param pageable Instancia de la paginación.
     * @return Listado de comentarios.
     */
    @Query("SELECT c FROM Comment c WHERE c.article.id = :idArticle")
    List<Comment> findByArticle(Integer idArticle, Pageable pageable);
    /**
     * Devuelve los comentarios por usuario.
     * @param idUser Identificador del usuario.
     * @param pageable Instancia de la paginación.
     * @return Listado de comentarios.
     */
    @Query("SELECT c FROM Comment c WHERE c.user.id = :idUser")
    List<Comment> findByUser(Integer idUser, Pageable pageable);
}