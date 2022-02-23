package cg.controllers;

import cg.model.Product;
import cg.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping
    public ModelAndView display() {
        ModelAndView modelAndView = new ModelAndView("list");
        List<Product> products = productService.selectAll();
        if (products.isEmpty()) {
            modelAndView.addObject("message", "No Product to Display");
            modelAndView.addObject("color", "red");
        }
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createGet() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createPost(@ModelAttribute Product product) {
        ModelAndView modelAndView = new ModelAndView("create");
        Product pro = productService.create(product);
        if (pro != null) {
            modelAndView.addObject("message", "Create successfully");
        }
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editGet(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Product pro = productService.selectById(id);
        if (pro == null) {
            modelAndView.addObject("message", "ID invalid");
        } else {
            modelAndView.addObject("product", pro);
        }
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editPost(@PathVariable int id, @ModelAttribute Product product) {
        ModelAndView modelAndView = new ModelAndView("edit");
        product.setId(id);
        Product product1 = productService.create(product);
        if (product1 != null) {
            modelAndView.addObject("message", "edit successfully");
        }
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView showDetail(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        Product product = productService.selectById(id);
        if (product != null) {
            modelAndView.addObject("product", product);
        } else {
            modelAndView.addObject("message", "Id invalid!");
        }
        return modelAndView;
    }


    @GetMapping("/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("list");
        Product product = productService.delete(id);
        if (product == null) {
            modelAndView.addObject("message", "Id invalid!");
            modelAndView.addObject("color", "red");
        }
        List<Product> products = productService.selectAll();
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @PostMapping("/product/search")
    public ModelAndView search(String key) {
        ModelAndView modelAndView = new ModelAndView("list");
        List<Product> products = productService.searchProduct(key);
        modelAndView.addObject("products", products);
        return modelAndView;
    }


}
