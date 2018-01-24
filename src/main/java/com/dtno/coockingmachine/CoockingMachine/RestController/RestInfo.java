package com.dtno.coockingmachine.CoockingMachine.RestController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RestInfo {

    @RequestMapping("/api")
    public ModelAndView showApis(){
        return new ModelAndView();
    }
}
