package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CalculatorController {
    @RequestMapping("Calculator")
    public ModelAndView calculator(){
        ModelAndView modelAndView = new ModelAndView("Calculator");
        return modelAndView;
    }
}
