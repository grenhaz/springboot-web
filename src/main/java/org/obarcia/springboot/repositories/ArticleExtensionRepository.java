package org.obarcia.springboot.repositories;

import java.util.List;
import java.util.Map;
import org.obarcia.springboot.models.entity.article.Article;
import org.springframework.data.domain.Pageable;

/**
 * Interface del respositorio personalizado.
 * 
 * @author Heck
 */
public interface ArticleExtensionRepository
{
    List<Article> findByFilter(Map<String, Object> filters, Pageable pageable);
    
    Long countByFilter(Map<String, Object> filters);
}