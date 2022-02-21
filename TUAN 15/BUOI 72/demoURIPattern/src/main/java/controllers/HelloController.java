package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
    @RequestMapping("/car?/s?o?/infor1")
    public String infor1(Model model){
    model.addAttribute("message", "Infor1");
    return "/index";
    }
@RequestMapping("/c*/s*d/infor2")
    public ModelAndView infor2(Model model){
        ModelAndView modelAndView = new ModelAndView("/index");
        model.addAttribute("message", "infor2");
        return modelAndView;
    }
}
