package com.example.demospringboot.controller;

import com.example.demospringboot.model.AppUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class HomeController {

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
    @GetMapping("/home")
    public String home(){
        return "/template-home/home";
    }
    @GetMapping("/user")
    public String user(Model model){
        model.addAttribute("user", getPrincipal());
        return "user";
    }
    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("user", getPrincipal());
        return "admin";
    }

    @GetMapping("/accessDenied")
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "/access-denied";
    }

}
