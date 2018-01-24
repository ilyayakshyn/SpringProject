package com.dtno.coockingmachine.CoockingMachine.Controller;

import com.dtno.coockingmachine.CoockingMachine.DAOPackage.DishDAO;
import com.dtno.coockingmachine.CoockingMachine.DAOPackage.IngredientDAO;
import com.dtno.coockingmachine.CoockingMachine.DAOPackage.UserDAO;
import com.dtno.coockingmachine.CoockingMachine.entity.Ingredients;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SearchController {

    @Autowired
    private DishDAO dishDAO;

    @Autowired
    private IngredientDAO ingredientDAO;

    @Autowired
    private UserDAO userDAO;

    // example: localhost:8081/search?ingredient=5&ingredient=7&ingredient=2......
    @RequestMapping("search")
    public ModelAndView searchByIngredients(
            @RequestParam(value = "ingredient", required = false) List<Integer> ingredients) {
        if (ingredients != null) {
            System.out.println(ingredients.size());
            List<Integer> list = ingredientDAO.findAllByIngredientsList(ingredients, ingredients.size());
            for (int i = 0; i < list.size(); i++) {
                System.out.println("dish_id: " + list.get(i));
            }
        }
        return new ModelAndView();
    }

    @RequestMapping(value = "/search1", method = RequestMethod.GET)
    public ModelAndView search() {
        Map<String, Object> data = new HashMap<>();

        List<Ingredients> list = ingredientDAO.findAll();
        String[] array = new String[list.size()];
        Integer[] id = new Integer[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i).getName();
            id[i] = list.get(i).getIngredient_id();
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        data.put("user", userDAO.findByLogin(auth.getName()).getUserName());
        data.put("ingr", array);
        data.put("ids", id);
        return new ModelAndView("search1", data);
    }

    @RequestMapping(value = "/search1", method = RequestMethod.POST)
    @ResponseBody
    public String jsonResponse(HttpServletRequest request, @RequestParam Map<String, String> body) throws JSONException {
        JSONArray json = new JSONArray(body .keySet().iterator().next());

        List<Integer> jsonIngredientList = new ArrayList<>();
        for (int i = 0; i < json.length(); i++) {
            int ingredientId = json.getJSONObject(i).getInt("id");
            jsonIngredientList.add(ingredientId);
        }
        List<TableDish> listDishes = new ArrayList<>();

        List<Integer> dishIdList = ingredientDAO.findAllByIngredientsList
                (jsonIngredientList, jsonIngredientList.size());

        for (int i = 0; i < dishIdList.size(); i++) {
            listDishes.add(new TableDish(dishDAO.findByDishId(dishIdList.get(i))));
        }

        System.out.println("jsonIngredientList = " + jsonIngredientList.size());
        System.out.println("listDishes = " + listDishes.size());
        System.out.println("dishIdList = " + dishIdList.size());

        JSONArray out = new JSONArray();

        for (int i = 0; i < listDishes.size(); i++) {
            TableDish dish = listDishes.get(i);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("dishId", dish.dishId);
            jsonObject.put("dishName", dish.dishName);
            jsonObject.put("dishDetails", dish.dishDetails);
            jsonObject.put("imgSrc", dish.imgSrc);
            out.put(jsonObject);
        }

        return out.toString();
    }

}
