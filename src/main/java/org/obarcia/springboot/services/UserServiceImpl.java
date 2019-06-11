package org.obarcia.springboot.services;

import org.hibernate.HibernateException;
import org.obarcia.springboot.components.datatables.DataTablesRequest;
import org.obarcia.springboot.components.datatables.DataTablesResponse;
import org.obarcia.springboot.exceptions.SaveException;
import org.obarcia.springboot.models.entity.user.User;
import org.obarcia.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio de usuarios.
 * 
 * @author obarcia
 */
@Service
public class UserServiceImpl implements UserService
{
    /**
     * Instancia del repositorio de usuarios.
     */
    @Autowired
    private UserRepository userRepository;
    
    @Override
    @Transactional(readOnly = true)
    public DataTablesResponse<User> getUsersLite(DataTablesRequest req)
    {
        // TODO: return userLiteRepository.getUsers(req);
        return null;
    }
    @Override
    @Transactional(readOnly = true)
    public User getUserById(int id)
    {
        return userRepository.findById(id).get();
    }
    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }
    @Override
    @Transactional(readOnly = true)
    public User getUserByNickname(String nickname)
    {
        return userRepository.findByNickname(nickname);
    }
    @Override
    @Transactional(readOnly = true)
    public User getUserByUkey(String ukey)
    {
        return userRepository.findByUkey(ukey);
    }
    @Override
    @Transactional
    public void save(User user) throws SaveException
    {
        try {
            userRepository.save(user);
        } catch (HibernateException e) {
            throw new SaveException();
        }
    }
}