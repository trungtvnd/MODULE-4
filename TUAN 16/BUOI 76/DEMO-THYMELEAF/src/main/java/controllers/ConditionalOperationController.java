package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ConditionalOperationController {
    @RequestMapping("/conditional-operation-example")
        public String operatorExample(){
        return "conditional-operation-example";
        }
}
