package org.obarcia.springboot.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.hibernate.HibernateException;
import org.obarcia.springboot.components.datatables.DataTablesColumn;
import org.obarcia.springboot.components.datatables.DataTablesOrder;
import org.obarcia.springboot.components.datatables.DataTablesRequest;
import org.obarcia.springboot.components.datatables.DataTablesResponse;
import org.obarcia.springboot.exceptions.SaveException;
import org.obarcia.springboot.models.entity.user.User;
import org.obarcia.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public DataTablesResponse<User> getUsers(DataTablesRequest req)
    {
        // Aplicar filtros
        Map<String, Object> filters = new HashMap<>();
        for (Map.Entry<String, DataTablesColumn> entry : req.getColumns().entrySet()) {
            filters.put(entry.getKey(), entry.getValue().getSearch());
        }
        if (req.getSearch() != null && !req.getSearch().isEmpty()) {
            filters.put("all", req.getSearch());
        }
        
        // Orden
        Sort sort = Sort.unsorted();
        for (DataTablesOrder order: req.getOrders()) {
            if (order.getDir() == DataTablesOrder.ORDER_ASC) {
                sort.and(Sort.by(order.getData()).ascending());
            } else {
                sort.and(Sort.by(order.getData()).descending());
            }
        }
        
        // Paginación
        Pageable pagination = PageRequest.of(req.getPage(), req.getLength(), sort);
        
        // Obtener los registros
        List<User> records = userRepository.findByFilter(filters, pagination);
        DataTablesResponse<User> response = new DataTablesResponse<>();
        response.setDraw(req.getDraw());
        response.setData(records);
        response.setRecordsFiltered(userRepository.countByFilter(filters));
        response.setRecordsTotal(userRepository.count());
        return response;
    }
    @Override
    @Transactional(readOnly = true)
    public User getUserById(int id)
    {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        
        return null;
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