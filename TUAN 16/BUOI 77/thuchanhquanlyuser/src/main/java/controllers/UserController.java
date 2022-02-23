package controllers;

import model.Login;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import service.UserDAO;

@Controller
public class UserController {

    @GetMapping("/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("home", "Login", new Login());
        return modelAndView;
    }
    @PostMapping("/login")
    public ModelAndView login1(@ModelAttribute("Login") Login login){
        User user;
        user = UserDAO.checkLogin(login);
        ModelAndView modelAndView;
        if(user == null){
             modelAndView = new ModelAndView("error");
            return modelAndView;
        } else {
             modelAndView = new ModelAndView("user");
            modelAndView.addObject("user", user);
            return modelAndView;
        }
    }

}
