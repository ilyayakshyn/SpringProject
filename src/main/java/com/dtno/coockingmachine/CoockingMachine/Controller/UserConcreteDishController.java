package com.dtno.coockingmachine.CoockingMachine.Controller;
import com.dtno.coockingmachine.CoockingMachine.DAOPackage.UserDAO;
import com.dtno.coockingmachine.CoockingMachine.DAOPackage.UserDishDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserConcreteDishController {
    @Autowired
    private UserDishDAO dishDAO;
    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "/userDish/{dishId}", method = RequestMethod.GET)
    public ModelAndView ShowDishById(@PathVariable int dishId){
        System.out.println(dishId);
        Map<String, Object> data = new HashMap<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        data.put("user", userDAO.findByLogin(auth.getName()).getUserName());
        data.put("dish", dishDAO.findAllByDishId(dishId));

        return new ModelAndView("dish", data);
    }
}
