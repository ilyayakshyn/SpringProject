package com.dtno.coockingmachine.CoockingMachine.service;

import com.dtno.coockingmachine.CoockingMachine.entity.User;

public interface UserService {

    User getAccount(String login);
    Boolean saveUser(User user);
}
