package org.obarcia.springboot.repositories;

import java.util.List;
import org.obarcia.springboot.models.article.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Repositorio de artículos.
 * 
 * @author Heck
 */
public interface ArticleRepository extends CrudRepository<Article, Integer>
{
    @Query("SELECT count(a) FROM Article a")
    Long countAll();
    List<Article> findAll(Pageable pageable);
    /**
     * Buscar un artículos por su título.
     * @param title Título a buscar.
     * @return Instancia del artículo.
     */
    @Query("SELECT a FROM Article a WHERE title = :title")
    Article findByTitle(@Param("title")String title);
}