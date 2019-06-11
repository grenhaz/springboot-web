package org.obarcia.springboot.repositories;

import java.util.List;
import org.obarcia.springboot.models.entity.article.Article;
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
    /**
     * Devuelve el listado paginado de artículos.
     * @param pageable Instancia de la paginación.
     * @return Listado de artículos.
     */
    List<Article> findAll(Pageable pageable);
    /**
     * Buscar un artículos por su título.
     * @param title Título a buscar.
     * @return Instancia del artículo.
     */
    @Query("SELECT a FROM Article a WHERE title = :title")
    Article findByTitle(@Param("title")String title);
    /**
     * Devuelve el total de artículos por tipo.
     * @param type Tipo de artículo.
     * @return Total de artículos.
     */
    @Query("SELECT COUNT(a) FROM Article a WHERE type = :type")
    Long countByType(@Param("type") String type);
    /**
     * Devuelve el listado de artículos por tipo paginado.
     * @param type Tipo del artículo.
     * @param pageable Instancia de la paginación.
     * @return Listado.
     */
    @Query("SELECT a FROM Article a WHERE type = :type")
    List<Article> findByType(@Param("type") String type, Pageable pageable);
}