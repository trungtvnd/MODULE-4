package controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.IProductService;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @GetMapping
    public ModelAndView showProduct() {
        ModelAndView modelAndView = new ModelAndView("list");
        ArrayList<Product> products = iProductService.getAll();
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deleteProduct(@PathVariable ("id") int id){
        ModelAndView modelAndView = new ModelAndView("list");
        Product product = iProductService.getProduct(id);
        if(product!=null){
            iProductService.deleteProduct(product);
        }else {
        modelAndView.addObject("message",new String(("ID không hợp lệ").getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8) );
        }

        ArrayList<Product> products = iProductService.getAll();
        modelAndView.addObject("products", products);
        return modelAndView;

    }

    @GetMapping("/{id}/view")
    public ModelAndView viewProduct(@PathVariable ("id") int id){
        ModelAndView modelAndView = new ModelAndView("detail");
        Product product = iProductService.getProduct(id);
        if(product != null){
                modelAndView.addObject("product", product);
        }else {
            modelAndView.addObject("message", "ID khong hop le");
        }
        return modelAndView;
    }
}
