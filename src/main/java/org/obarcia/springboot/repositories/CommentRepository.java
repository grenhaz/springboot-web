package org.obarcia.springboot.repositories;

import org.obarcia.springboot.models.article.Comment;
import org.springframework.data.repository.CrudRepository;

/**
 * Repositorio de comentarios.
 * 
 * @author Heck
 */
public interface CommentRepository extends CrudRepository<Comment, Integer>
{    
}