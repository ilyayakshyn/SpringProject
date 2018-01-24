package com.dtno.coockingmachine.CoockingMachine.Controller;

import com.dtno.coockingmachine.CoockingMachine.entity.User;
import com.dtno.coockingmachine.CoockingMachine.entity.enums.UserRoleEnum;
import com.dtno.coockingmachine.CoockingMachine.service.UserDetailsServiceImpl;
import com.dtno.coockingmachine.CoockingMachine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@Controller
public class RegistrationController {

    private static Logger log = Logger.getLogger(RegistrationController.class.getName());

    @Autowired
    private UserService userService;

    @Autowired
    protected AuthenticationManager authenticationManager;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        log.info("Start registration");
        ModelAndView model = new ModelAndView("registration");
        model.addObject("userForm", new User());
        return model;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registrationTry(@ModelAttribute("userForm") User userForm, HttpServletRequest request){

        if(userForm.getUserName().length() > 35){
            return new ModelAndView("registration").addObject("error", "Username is too long");
        }
        if(userForm.getLogin().length() > 35){
            return new ModelAndView("registration").addObject("error", "Login is too long");
        }
        if(userForm.getPassword().length() > 35){
            return new ModelAndView("registration").addObject("error", "Password is too long");
        }

        Boolean isRegistrationSuccess = userService.saveUser(userForm);
        if(isRegistrationSuccess) {
            log.info("registration success for user with login: " + userForm.getLogin());

            // WTf CODE ASHASFASHFASFHASFHASf

            //TODO:
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userForm.getLogin(), userForm.getPassword());
            request.getSession();
            token.setDetails(new WebAuthenticationDetails(request));
            Authentication authenticatedUser = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
            return new ModelAndView("redirect:/index");
        }
        log.info("registration failed");

        return new ModelAndView("registration").addObject("error", "This login is already used");
    }
}
