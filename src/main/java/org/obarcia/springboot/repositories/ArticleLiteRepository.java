package org.obarcia.springboot.repositories;

import java.util.List;
import org.obarcia.springboot.models.article.ArticleLite;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Repositorio de artículos.
 * 
 * @author Heck
 */
public interface ArticleLiteRepository extends CrudRepository<ArticleLite, Integer>
{
    @Query("SELECT a FROM ArticleLite a")
    List<ArticleLite> getArticles(Pageable pageable);
}