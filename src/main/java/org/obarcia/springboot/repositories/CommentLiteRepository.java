package org.obarcia.springboot.repositories;

import java.util.List;
import org.obarcia.springboot.models.article.CommentLite;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Repositorio de comentarios.
 * 
 * @author Heck
 */
public interface CommentLiteRepository extends CrudRepository<CommentLite, Integer>
{
    @Query("SELECT c FROM CommentLite c")
    List<CommentLite> getComments();
}