package com.dtno.coockingmachine.CoockingMachine.Controller;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Controller
public class LoginController {

    private static Logger log = Logger.getLogger(LoginController.class.getName());

    private static ModelAndView modelAndView = new ModelAndView("login");

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error) {
        log.info("logging page vision");
        if (error != null) {
            modelAndView.addObject("error", "Invalid username or password");
        }
        return modelAndView;
    }


}
