package org.obarcia.springboot.repositories;

import java.util.List;
import java.util.Map;
import org.obarcia.springboot.models.entity.user.User;
import org.springframework.data.domain.Pageable;

/**
 * Interface del respositorio personalizado.
 * 
 * @author Heck
 */
public interface UserExtensionRepository
{
    public List<User> findByFilter(Map<String, Object> filters, Pageable pageable);
    public Long countByFilter(Map<String, Object> filters);
}