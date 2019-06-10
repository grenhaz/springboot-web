package org.obarcia.springboot.repositories;

import org.obarcia.springboot.models.article.Article;
import org.springframework.data.repository.CrudRepository;

/**
 * Repositorio de artículos.
 * 
 * @author Heck
 */
public interface ArticleRepository extends CrudRepository<Article, Integer>
{    
}