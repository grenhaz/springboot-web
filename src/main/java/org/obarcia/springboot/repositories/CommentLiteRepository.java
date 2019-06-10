package org.obarcia.springboot.repositories;

import org.obarcia.springboot.models.article.CommentLite;
import org.springframework.data.repository.CrudRepository;

/**
 * Repositorio de comentarios.
 * 
 * @author Heck
 */
public interface CommentLiteRepository extends CrudRepository<CommentLite, Integer>
{    
}