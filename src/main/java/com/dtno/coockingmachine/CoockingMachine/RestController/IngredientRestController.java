package com.dtno.coockingmachine.CoockingMachine.RestController;

import com.dtno.coockingmachine.CoockingMachine.DAOPackage.IngredientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IngredientRestController {

    @Autowired
    private IngredientDAO ingredientDAO;

    @RequestMapping("/api/ingredients")
    public Object ingredientById(@RequestParam(value = "ingredientId", required = false) Integer ingredientId){
        if(ingredientId != null){
            return ingredientDAO.findByIngredientId(ingredientId);
        }
        System.out.println(ingredientDAO.findAll().get(1).getCategory());
        return ingredientDAO.findAll();
    }
}
