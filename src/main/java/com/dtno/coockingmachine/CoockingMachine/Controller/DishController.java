package com.dtno.coockingmachine.CoockingMachine.Controller;


import com.dtno.coockingmachine.CoockingMachine.DAOPackage.DishDAO;
import com.dtno.coockingmachine.CoockingMachine.DAOPackage.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DishController {

    @Autowired
    private DishDAO dishDAO;
    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "/dish/{dishId}", method = RequestMethod.GET)
    public ModelAndView ShowDishById(@PathVariable int dishId){
        System.out.println(dishId);
        Map<String, Object> data = new HashMap<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        data.put("user", userDAO.findByLogin(auth.getName()).getUserName());
        data.put("dish", dishDAO.findByDishId(dishId));

        return new ModelAndView("dish", data);
    }
}
