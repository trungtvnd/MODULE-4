package cg.controllers;

import cg.model.Product;
import cg.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Controller
@RequestMapping("product")
public class ProductController {

    @Value("${file-upload}")
    private String fileUpload;
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
    public ModelAndView createPost(@ModelAttribute Product product, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("create");
        MultipartFile multipartFile = product.getFile();
        String fileName = multipartFile.getOriginalFilename();
        try{
            FileCopyUtils.copy(product.getFile().getBytes(), new File(fileUpload+fileName));

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        product.setImage("image/" + fileName);

        if( bindingResult.hasErrors()){
            modelAndView.addObject("errorMessage", bindingResult.getAllErrors());
            return modelAndView;
        }
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
        if(product.getFile().getSize()!=0){
            MultipartFile multipartFile = product.getFile();
            String fileName = multipartFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(product.getFile().getBytes(), new File(fileUpload+ fileName));

            }catch (IOException e){
                System.out.println(e.getMessage());
            }
            product.setImage("image/" + fileName);
        }else {
            product.setImage((productService.selectById(product.getId()).getImage()));
        }
        Product product1 = productService.create(product);
        if(product1!=null){
            modelAndView.addObject("message", "Update Successfully");

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
        productService.delete(id);
        List<Product> products = productService.selectAll();
        modelAndView.addObject("products", products);
        return modelAndView;
    }


    @PostMapping("/search")
    public ModelAndView search(@RequestParam("search") String name) {
        ModelAndView modelAndView = new ModelAndView("list");
        List<Product> products = productService.searchProduct(name);
        modelAndView.addObject("products", products);
        modelAndView.addObject("search", name);
        return modelAndView;
    }


}
