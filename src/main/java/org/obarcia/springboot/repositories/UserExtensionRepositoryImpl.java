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
import org.obarcia.springboot.models.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;

/**
 * Implementación del repositorio de usuarios extendido.
 * 
 * @author Heck
 */
@Repository
public class UserExtensionRepositoryImpl implements UserExtensionRepository
{
    @Autowired
    EntityManager em;
    
    @Override
    public List<User> findByFilter(Map<String, Object> filters, Pageable pageable)
    {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = builder.createQuery(User.class);

        Root<User> root = cq.from(User.class);
        
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
        Root<User> root = cq.from(User.class);
        cq.select(builder.count(root));
        
        applyFilters(cq, root, builder, filters);
        
        return em.createQuery(cq).getSingleResult();
    }
    private void applyFilters(CriteriaQuery cq, Root<User> root, CriteriaBuilder builder, Map<String, Object> filters)
    {
        List<Predicate> predicates = new ArrayList<>();

        if (filters != null) {
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                if (entry.getValue() != null && !entry.getValue().toString().isEmpty()) {
                    switch (entry.getKey()) {
                        case "id":
                            predicates.add(builder.equal(root.get("id"), Integer.parseInt(entry.getValue().toString())));
                            break;
                        case "nickname":
                            predicates.add(builder.like(builder.lower(root.<String>get("nickname")), "%" + entry.getValue().toString().toLowerCase() + "%"));
                            break;
                        case "email":
                            predicates.add(builder.like(builder.lower(root.<String>get("email")), "%" + entry.getValue().toString().toLowerCase() + "%"));
                            break;
                        case "user_role":
                            predicates.add(builder.equal(root.get("user_role"), entry.getValue()));
                            break;
                        case "created":
                            predicates.add(builder.equal(root.get("created"), entry.getValue()));
                            break;
                        case "active":
                            predicates.add(builder.equal(root.get("active"), Boolean.valueOf(entry.getValue().toString())));
                            break;
                        case "all":
                            predicates.add(
                                builder.or(
                                    builder.like(root.<String>get("nickname"), "%" + entry.getValue() + "%"),
                                    builder.like(root.<String>get("email"), "%" + entry.getValue() + "%")
                                )
                            );
                            break;
                    }
                }
            }
        }
        cq.where(predicates.toArray(new Predicate[0]));
    }
}