package org.obarcia.springboot.repositories;

import org.obarcia.springboot.models.user.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Repositorio de usuarios.
 * 
 * @author Heck
 */
public interface UserRepository extends CrudRepository<User, Integer>
{    
}