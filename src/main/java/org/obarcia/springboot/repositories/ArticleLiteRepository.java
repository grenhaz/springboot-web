package org.obarcia.springboot.repositories;

import org.obarcia.springboot.models.article.ArticleLite;
import org.springframework.data.repository.CrudRepository;

/**
 * Repositorio de artículos.
 * 
 * @author Heck
 */
public interface ArticleLiteRepository extends CrudRepository<ArticleLite, Integer>
{    
}