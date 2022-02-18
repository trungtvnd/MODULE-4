package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import service.DictionaryService;

@Controller
public class DictionaryController {

    @GetMapping("/searchDic")
    public String findMeaningGet(){
        return "dic";
    }

    @PostMapping("/searchDic")
    public ModelAndView findMeaningPost(String key){
        ModelAndView modelAndView = new ModelAndView("dic");
        modelAndView.addObject("key", key );
        if(DictionaryService.dictionary.containsKey(key)){
            String result = DictionaryService.dictionary.get(key);
            modelAndView.addObject("result", result);
        }else {
            modelAndView.addObject("result", "NOT FOUND");
        }return modelAndView;

    }
}
