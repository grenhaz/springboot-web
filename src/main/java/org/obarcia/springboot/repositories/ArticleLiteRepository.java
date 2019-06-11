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
    @Query("SELECT count(a) FROM ArticleLite a")
    Long countAll();
    List<ArticleLite> findAll(Pageable pageable);
}