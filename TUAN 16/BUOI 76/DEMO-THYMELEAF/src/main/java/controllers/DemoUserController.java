package controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DemoUserController {
    @RequestMapping("displayUser")
    public ModelAndView displayUser(){
        ModelAndView modelAndView = new ModelAndView("user");
        User user = new User(1, "Trung", "HN", 1);
        User user1 = new User(2, "Sang", "HN", 0);
        User user2 = new User(3, "Kien", "HN", 2);
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);
        users.add(user2);
        modelAndView.addObject("user", users);
        return modelAndView;
    }
}
