package org.obarcia.springboot.repositories;

import java.util.List;
import org.obarcia.springboot.models.article.ArticleSimple;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Repositorio de artículos.
 * 
 * @author Heck
 */
public interface ArticleSimpleRepository extends CrudRepository<ArticleSimple, Integer>
{
    @Query("SELECT a FROM ArticleSimple a")
    List<ArticleSimple> getArticles();
}