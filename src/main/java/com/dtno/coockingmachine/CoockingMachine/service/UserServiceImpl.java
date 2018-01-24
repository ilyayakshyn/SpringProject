package com.dtno.coockingmachine.CoockingMachine.service;

import com.dtno.coockingmachine.CoockingMachine.DAOPackage.UserDAO;
import com.dtno.coockingmachine.CoockingMachine.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;


@Service
public class UserServiceImpl implements UserService {
    private static Logger log = Logger.getLogger(UserService.class.getName());

    @Autowired
    private UserDAO UserDAO;

    @Override
    public User getAccount(String login) {
        log.info("getting account by login: " + login);
        try {
            User user = UserDAO.findByLogin(login);
            log.info("account was got with login: " + login);
            return user;
        } catch (Exception e) {
            log.log(Level.SEVERE, "Exception while getting account by login: " + login + ", --" + e);
            throw e;
        }
    }

    @Override
    public Boolean saveUser(User user) {
        log.info("saving account by login: " + user.getLogin());
        try {
            UserDAO.save(user);
            log.info("account was saved with login: " + user.getLogin());
            return true;
        } catch (Exception e) {
            log.log(Level.SEVERE, "Exception while saving account by login: " + user.getLogin() + ", --" + e);
            return false;
        }
    }
}
