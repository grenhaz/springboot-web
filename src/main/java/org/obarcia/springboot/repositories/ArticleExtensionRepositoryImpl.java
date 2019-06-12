package org.obarcia.springboot.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.obarcia.springboot.models.entity.article.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * Implementación del repositorio de artículos extendido.
 * 
 * @author Heck
 */
@Repository
public class ArticleExtensionRepositoryImpl implements ArticleExtensionRepository
{
    @Autowired
    EntityManager em;
    
    @Override
    public List<Article> findByFilter(Map<String, Object> filters, Pageable pageable)
    {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Article> cq = builder.createQuery(Article.class);

        Root<Article> root = cq.from(Article.class);
        
        applyFilters(cq, root, builder, filters);
        
        // TODO: Aplicar la ordenación
        cq.orderBy(builder.desc(root.get("publish")));
        
        return em.createQuery(cq).setFirstResult((int)pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
    }
    @Override
    public Long countByFilter(Map<String, Object> filters)
    {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = builder.createQuery(Long.class);
        Root<Article> root = cq.from(Article.class);
        cq.select(builder.count(root));
        
        applyFilters(cq, root, builder, filters);
        
        return em.createQuery(cq).getSingleResult();
    }
    private void applyFilters(CriteriaQuery cq, Root<Article> root, CriteriaBuilder builder, Map<String, Object> filters)
    {
        List<Predicate> predicates = new ArrayList<>();

        if (filters != null) {
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                if (entry.getValue() != null && !entry.getValue().toString().isEmpty()) {
                    switch (entry.getKey()) {
                        case "id":
                            predicates.add(builder.equal(root.get("id"), Integer.parseInt(entry.getValue().toString())));
                            break;
                        case "title":
                            predicates.add(builder.like(builder.lower(root.<String>get("tile")), "%" + entry.getValue().toString().toLowerCase() + "%"));
                            break;
                        case "description":
                            predicates.add(builder.like(builder.lower(root.<String>get("description")), "%" + entry.getValue().toString().toLowerCase() + "%"));
                            break;
                        case "type":
                            if (!entry.getValue().toString().equals("all")) {
                                predicates.add(builder.equal(root.get("type"), entry.getValue()));
                            }
                            break;
                        case "tag":
                            if (!entry.getValue().toString().equals("games")) {
                                predicates.add(builder.like(root.<String>get("tags"), "%[" + entry.getValue().toString().toUpperCase() + "]%"));
                            }
                            break;
                        case "active":
                            predicates.add(builder.equal(root.get("active"), Boolean.valueOf(entry.getValue().toString())));
                            break;
                        case "important":
                            predicates.add(builder.equal(root.get("important"), Boolean.valueOf(entry.getValue().toString())));
                            break;
                        case "publish":
                            predicates.add(builder.equal(root.get("publish"), entry.getValue()));
                            break;
                        case "all":
                            predicates.add(builder.like(root.<String>get("title"), "%" + entry.getValue() + "%"));
                            predicates.add(builder.like(root.<String>get("description"), "%" + entry.getValue() + "%"));
                            break;
                    }
                }
            }
        }
        predicates.add(builder.equal(root.get("active"), true));
        cq.where(predicates.toArray(new Predicate[0]));
    }
}