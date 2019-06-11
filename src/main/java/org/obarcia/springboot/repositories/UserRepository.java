package org.obarcia.springboot.repositories;

import org.obarcia.springboot.models.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Repositorio de usuarios.
 * 
 * @author Heck
 */
public interface UserRepository extends CrudRepository<User, Integer>
{
    @Query("SELECT u FROM User u WHERE ukey = :ukey")
    User getUserByUkey(@Param("ukey") String ukey);
    @Query("SELECT u FROM User u WHERE nickname = :nickname")
    User getUserByNickname(@Param("nickname") String nickname);
    @Query("SELECT u FROM User u WHERE email = :email")
    User getUserByEmail(@Param("email") String email);
}