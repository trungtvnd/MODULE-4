package cg.controllers;

import cg.model.Category;
import cg.model.Product;
import cg.service.ICategoryService;
import cg.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("product")
public class ProductController {
    @Autowired
    IProductService productService;

    @Autowired
    ICategoryService categoryService;

    @ModelAttribute(name = "category")
    private Iterable<Category> categories(){
         return categoryService.findAll();
    }

    @GetMapping
    public ModelAndView display(@PageableDefault(3) Pageable pageable){
    ModelAndView modelAndView = new ModelAndView("list");
    Page<Product> products = productService.findAll(pageable);
       if(products.isEmpty()){
           modelAndView.addObject("message", "No Products");
       }
       modelAndView.addObject("products", products);

        return modelAndView;
    }
}
