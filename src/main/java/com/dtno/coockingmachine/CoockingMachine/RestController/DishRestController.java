package com.dtno.coockingmachine.CoockingMachine.RestController;

import com.dtno.coockingmachine.CoockingMachine.DAOPackage.DishDAO;
import com.dtno.coockingmachine.CoockingMachine.DAOPackage.IngredientDAO;
import com.dtno.coockingmachine.CoockingMachine.entity.Dishes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;

@RestController
public class DishRestController {


    @Autowired
    private DishDAO dishDAO;

    @Autowired
    private IngredientDAO ingredientDAO;

    @RequestMapping("/api/dish")
    public Object dishById(@RequestParam(value = "dishId", required = false) Integer dishId, @RequestParam(value = "ingredient", required = false) List<Integer> ingredients) {
        if(dishId != null) {
            return dishDAO.findByDishId(dishId);
        }
        if(ingredients != null){
            List<Integer> dishIdList = ingredientDAO.findAllByIngredientsList(ingredients, ingredients.size());
            List<Dishes> outDishList = new ArrayList<>();
            for (int i = 0; i < dishIdList.size(); i++) {
                outDishList.add(dishDAO.findByDishId(dishIdList.get(i)));
            }
            return outDishList;
        }
        return dishDAO.findAll();
    }


}
