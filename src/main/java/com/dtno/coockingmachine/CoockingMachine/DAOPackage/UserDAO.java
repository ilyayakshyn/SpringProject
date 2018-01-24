package com.dtno.coockingmachine.CoockingMachine.DAOPackage;

import com.dtno.coockingmachine.CoockingMachine.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserDAO extends CrudRepository<User, Long> {

    public User findByLogin(String login);

    public List<User> findAll();

    public User save(User user);
}
