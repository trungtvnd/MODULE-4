package controllers;

import model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DemoBieuThuc {
    @RequestMapping("demoBieuThuc")
    public ModelAndView demo(){
        ModelAndView modelAndView = new ModelAndView("demoBieuThuc");
        String name = "CodeGym";
        Customer customer = new Customer(1L, "CodeGym HN");
        modelAndView.addObject("name", name);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }
}
