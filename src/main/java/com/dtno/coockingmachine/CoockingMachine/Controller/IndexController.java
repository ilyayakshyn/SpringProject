package com.dtno.coockingmachine.CoockingMachine.Controller;


import com.dtno.coockingmachine.CoockingMachine.DAOPackage.UserDAO;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
    public ModelAndView loginPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Map<String, String> map = new HashMap<>();
        map.put("user", userDAO.findByLogin(auth.getName()).getUserName());
        return new ModelAndView("index", map);
    }
}
