package org.obarcia.springboot.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.obarcia.springboot.models.entity.article.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;

/**
 * Implementación del repositorio de comentarios extendido.
 * 
 * @author Heck
 */
@Repository
public class CommentExtensionRepositoryImpl implements CommentExtensionRepository
{
    @Autowired
    EntityManager em;
    
    @Override
    public List<Comment> findByFilter(Map<String, Object> filters, Pageable pageable)
    {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Comment> cq = builder.createQuery(Comment.class);

        Root<Comment> root = cq.from(Comment.class);
        
        applyFilters(cq, root, builder, filters);
        
        // Aplicar la ordenación
        cq.orderBy(QueryUtils.toOrders(pageable.getSort(), root, builder));
        
        return em.createQuery(cq).setFirstResult((int)pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
    }
    @Override
    public Long countByFilter(Map<String, Object> filters)
    {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = builder.createQuery(Long.class);
        Root<Comment> root = cq.from(Comment.class);
        cq.select(builder.count(root));
        
        applyFilters(cq, root, builder, filters);
        
        return em.createQuery(cq).getSingleResult();
    }
    private void applyFilters(CriteriaQuery cq, Root<Comment> root, CriteriaBuilder builder, Map<String, Object> filters)
    {
        List<Predicate> predicates = new ArrayList<>();

        if (filters != null) {
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                if (entry.getValue() != null && !entry.getValue().toString().isEmpty()) {
                    switch (entry.getKey()) {
                        case "id":
                            predicates.add(builder.equal(root.get("id"), Integer.parseInt(entry.getValue().toString())));
                            break;
                        case "content":
                            predicates.add(builder.like(builder.lower(root.<String>get("content")), "%" + entry.getValue().toString().toLowerCase() + "%"));
                            break;
                        case "erased":
                            predicates.add(builder.equal(root.get("erased"), Boolean.valueOf(entry.getValue().toString())));
                            break;
                        case "important":
                            predicates.add(builder.equal(root.get("important"), Boolean.valueOf(entry.getValue().toString())));
                            break;
                        case "publish":
                            predicates.add(builder.equal(root.get("publish"), entry.getValue()));
                            break;
                        case "tag":
                            if (!entry.getValue().toString().equals("games")) {
                                predicates.add(builder.like(root.get("article").<String>get("tags"), "%[" + entry.getValue().toString().toUpperCase() + "]%"));
                            }
                            break;
                        case "all":
                            predicates.add(builder.like(root.<String>get("content"), "%" + entry.getValue() + "%"));
                            break;
                    }
                }
            }
        }
        cq.where(predicates.toArray(new Predicate[0]));
    }
}