package org.obarcia.springboot.repositories;

import org.obarcia.springboot.models.user.UserLite;
import org.springframework.data.repository.CrudRepository;

/**
 * Repositorio de usuarios.
 * 
 * @author Heck
 */
public interface UserLiteRepository extends CrudRepository<UserLite, Integer>
{    
}