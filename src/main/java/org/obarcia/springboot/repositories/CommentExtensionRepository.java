package org.obarcia.springboot.repositories;

import java.util.List;
import java.util.Map;
import org.obarcia.springboot.models.entity.article.Comment;
import org.springframework.data.domain.Pageable;

/**
 * Interface del respositorio personalizado.
 * 
 * @author Heck
 */
public interface CommentExtensionRepository
{
    List<Comment> findByFilter(Map<String, Object> filters, Pageable pageable);
    
    Long countByFilter(Map<String, Object> filters);
}