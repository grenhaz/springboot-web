package org.obarcia.springboot.repositories;

import java.util.List;
import org.obarcia.springboot.models.user.UserLite;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Repositorio de usuarios.
 * 
 * @author Heck
 */
public interface UserLiteRepository extends CrudRepository<UserLite, Integer>
{
    /**
     * Devuelve el total de usuarios.
     * @return Total de usuarios.
     */
    @Query("SELECT count(u) FROM UserLite u")
    Long countAll();
    /**
     * Devuelve el listado de usuarios paginado.
     * @param pageable Instancia de la paginación.
     * @return Listado paginado de usuarios.
     */
    List<UserLite> findAll(Pageable pageable);
}