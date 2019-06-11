package org.obarcia.springboot.repositories;

import java.util.List;
import org.obarcia.springboot.models.article.ArticleSimple;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Repositorio de artículos.
 * 
 * @author Heck
 */
public interface ArticleSimpleRepository extends CrudRepository<ArticleSimple, Integer>
{
    @Query("SELECT count(a) FROM ArticleSimple a")
    Long countAll();
    List<ArticleSimple> findAll(Pageable pageable);
}