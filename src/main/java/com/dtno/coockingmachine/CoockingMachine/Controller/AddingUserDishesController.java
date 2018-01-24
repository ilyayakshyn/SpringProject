package com.dtno.coockingmachine.CoockingMachine.Controller;

import com.dtno.coockingmachine.CoockingMachine.DAOPackage.UserDAO;
import com.dtno.coockingmachine.CoockingMachine.DAOPackage.UserDishDAO;
import com.dtno.coockingmachine.CoockingMachine.entity.User;
import com.dtno.coockingmachine.CoockingMachine.entity.UserDishes;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AddingUserDishesController {

    @Autowired
    private UserDishDAO userDishDAO;

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "/userRec", method = RequestMethod.GET)
    public ModelAndView UserRecPage(){
        Map<String, Object > map = new HashMap<>();
        ModelAndView model = new ModelAndView("addUserDish");
      //  model.addObject("userDishes", new UserDishes());
      //  return model;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        map.put("user", userDAO.findByLogin(auth.getName()).getUserName());
        return new ModelAndView("addUserDish", map).addObject("userDishes", new UserDishes());
    }

    @RequestMapping(value = "/userRec", method = RequestMethod.POST)
    public ModelAndView addUserRec(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("userDishes") UserDishes userDishes, @RequestParam("file") MultipartFile file) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Map<String, Object > map = new HashMap<>();
        //userDishDAO.save(new UserDishes());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(userDishes.getName().length() > 70){
            return new ModelAndView("error")
                    .addObject("error", "Dishname is too long");
        }

        if(userDishes.getDetails().length() > 290){
            return new ModelAndView("error")
                    .addObject("error", "Dish details is too long");
        }

        if(userDishes.getCookingReceptie().length() > 24900){
            return new ModelAndView("error")
                    .addObject("error", "Dish cooking receptie is too long");
        }

        if(file.isEmpty()){
            System.out.println("empty");
            return new ModelAndView("error")
                    .addObject("error", "File is empty");

        }

        if(file.getSize() > 10_000_000L){
            System.out.println("too large");
            return new ModelAndView("error")
                    .addObject("error", "File size is too large");
        }

        if(!file.getContentType().contains("image")){
            System.out.println("not image");
            return new ModelAndView("error")
                    .addObject("error", "File type is not supported");
        }


        User user = userDAO.findByLogin(auth.getName());
        String newFileName = user.getUser_id() + "_" + System.currentTimeMillis() + ".jpg";

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("resources\\static\\user_dish_pic\\" + newFileName);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        userDishes.setImgSrc("user_dish_pic/" + newFileName);
        userDishes.setUser(user);

        userDishDAO.save(userDishes);

        return new ModelAndView("redirect:/userRec");
    }
}
