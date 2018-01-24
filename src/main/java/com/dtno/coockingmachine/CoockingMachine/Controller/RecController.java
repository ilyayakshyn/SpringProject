package com.dtno.coockingmachine.CoockingMachine.Controller;

import com.dtno.coockingmachine.CoockingMachine.DAOPackage.DishDAO;
import com.dtno.coockingmachine.CoockingMachine.DAOPackage.UserDAO;
import com.dtno.coockingmachine.CoockingMachine.entity.Dishes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class RecController {

    @Autowired
    private DishDAO dishDAO;
    @Autowired
    private UserDAO userDAO;

    @RequestMapping("/rec")
    public ModelAndView index() {
        Map<String, Object> data = new HashMap<>();
        ArrayList<TableDish> table = new ArrayList<>();
        List<Dishes> list = dishDAO.findAll();
        for (int i = 0; i < list.size(); i++) {
            table.add(new TableDish(list.get(i)));
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        data.put("user", userDAO.findByLogin(auth.getName()).getUserName());
        data.put("table", table);
        return new ModelAndView("rec", data);
    }
}

//TODO: убрать это ваще, возвращать обычный список Dishes
class TableDish {

    public String imgSrc;
    public int dishId;
    public String dishName;
    public String dishDetails;

    public TableDish() {
    }

    public TableDish(Dishes dish) {
        this.dishId = dish.getDishId();
        this.imgSrc = dish.getImgSrc();
        this.dishName = dish.getName();
        this.dishDetails = dish.getDetails();
    }
}
