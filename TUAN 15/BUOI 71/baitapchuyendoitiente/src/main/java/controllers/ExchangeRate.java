package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExchangeRate {
    @GetMapping("/converter")
    public String convert(){
        return "convert";
    }

    @PostMapping("/converter")
    public ModelAndView convert(double usd, double rate){
        ModelAndView modelAndView = new ModelAndView("convert");
        modelAndView.addObject("rate", rate);
        modelAndView.addObject("usd", usd);
        modelAndView.addObject("result", rate*usd);
        return modelAndView;
        }


}
