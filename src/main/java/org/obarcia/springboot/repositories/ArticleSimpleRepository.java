package org.obarcia.springboot.repositories;

import org.obarcia.springboot.models.article.ArticleSimple;
import org.springframework.data.repository.CrudRepository;

/**
 * Repositorio de artículos.
 * 
 * @author Heck
 */
public interface ArticleSimpleRepository extends CrudRepository<ArticleSimple, Integer>
{    
}