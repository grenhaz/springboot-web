package org.obarcia.springboot.repositories;

import java.util.List;
import org.obarcia.springboot.models.article.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Repositorio de artículos.
 * 
 * @author Heck
 */
public interface ArticleRepository extends CrudRepository<Article, Integer>
{
    @Query("SELECT a FROM Article a")
    List<Article> getArticles();
}