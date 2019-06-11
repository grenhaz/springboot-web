package org.obarcia.springboot.repositories;

import org.obarcia.springboot.models.entity.user.User;
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
    /**
     * Devuelve un usuario por su UKEY.
     * @param ukey UKEY a buscar.
     * @return Instancia del usuario.
     */
    @Query("SELECT u FROM User u WHERE ukey = :ukey")
    User findByUkey(@Param("ukey") String ukey);
    /**
     * Devuelve un usuario por su Nickname.
     * @param nickname NIckname a buscar.
     * @return Instancia del usuario.
     */
    @Query("SELECT u FROM User u WHERE nickname = :nickname")
    User findByNickname(@Param("nickname") String nickname);
    /**
     * Devuelve un usuario por su e-mail.
     * @param email e-mail a buscar.
     * @return Instancia del usuario.
     */
    @Query("SELECT u FROM User u WHERE email = :email")
    User findByEmail(@Param("email") String email);
}