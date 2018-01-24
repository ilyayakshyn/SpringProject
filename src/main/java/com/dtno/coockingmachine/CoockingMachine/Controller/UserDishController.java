package com.dtno.coockingmachine.CoockingMachine.Controller;

import com.dtno.coockingmachine.CoockingMachine.DAOPackage.UserDAO;
import com.dtno.coockingmachine.CoockingMachine.DAOPackage.UserDishDAO;
import com.dtno.coockingmachine.CoockingMachine.entity.UserDishes;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserDishController {

    @Autowired
    private UserDishDAO userDishDAO;
    @Autowired
    private UserDAO userDAO;

    @RequestMapping("/userDish")
    public ModelAndView showUserDish() {
        Map<String, Object> data = new HashMap<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ArrayList<UserDishes> table = new ArrayList<>();
        List<UserDishes> list = userDishDAO.findAllByUser(userDAO.findByLogin(auth.getName()));
        for (int i = 0; i < list.size(); i++) {
            table.add((list.get(i)));
        }

        data.put("user", userDAO.findByLogin(auth.getName()).getUserName());
        data.put("table", table);
        return new ModelAndView("userDish", data);
    }

    @RequestMapping(value = "/userDish", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestParam Map<String, String> body) throws JSONException {
        JSONArray json = new JSONArray(body .keySet().iterator().next());
        int dishId = json.getJSONObject(0).getInt("id");
        int isDeleted = userDishDAO.deleteByDishId(dishId);
        if(isDeleted==1){
            return "true";
        }
        return "false";
    }

}

