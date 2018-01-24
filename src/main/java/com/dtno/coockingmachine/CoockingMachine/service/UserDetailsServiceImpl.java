package com.dtno.coockingmachine.CoockingMachine.service;

import com.dtno.coockingmachine.CoockingMachine.DAOPackage.UserDAO;
import com.dtno.coockingmachine.CoockingMachine.entity.enums.UserRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDAO UserDAO;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        System.out.println("in load user " + login);
        // тут хз нахуя сейчас эта обёртка, если мы можем прст юзануть сразу наш UserDAO через автовайред
        // крч походу можно смело убрать, но пусть пока будет
        com.dtno.coockingmachine.CoockingMachine.entity.User user = userService.getAccount(login);
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(UserRoleEnum.USER.name()));

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), roles);
        return userDetails;
    }
}
