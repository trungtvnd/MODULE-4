package cg.controllers;

import cg.model.Category;
import cg.model.Product;
import cg.service.ICategoryService;
import cg.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Value("${file-upload}")
    private String fileUpload;

    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IProductService productService;

    @GetMapping
    public ModelAndView display(@SortDefault(sort = {"name", "price"},
            direction = Sort.Direction.ASC)@PageableDefault(3) Pageable pageable
            , @RequestParam("search")Optional<String> key) {
        ModelAndView modelAndView = new ModelAndView("list");

        Page<Product> products;

        if(key.isPresent()){
            products = productService.searchByName(key.get(), pageable);
            modelAndView.addObject("products", products);
            modelAndView.addObject("check", false);
        } else {
            products = productService.findAll(pageable);
            modelAndView.addObject("products", products);
            modelAndView.addObject("check", true);
        }

        return modelAndView;
    }
//    @GetMapping("/findAll")
//    public ModelAndView findAll(@PageableDefault(3) Pageable pageable){
//        ModelAndView modelAndView = new ModelAndView("list");
//        Page<Product> productPage = productService.findAll(pageable);
//        modelAndView.addObject("product", productPage);
//        return modelAndView;
//    }

    @GetMapping("/create")
    public ModelAndView createGet() {
        ModelAndView modelAndView = new ModelAndView("create");
        List<Category> categories = categoryService.selectAll();
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createPost(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("create");
        MultipartFile multipartFile = product.getMultipartFile();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(product.getMultipartFile().getBytes(), new File(fileUpload + fileName));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        product.setImage(fileName);

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("errorMessage", bindingResult.getAllErrors());
            List<Category> categories = categoryService.selectAll();
            modelAndView.addObject("categories", categories);
            modelAndView.addObject("product", new Product());

            return modelAndView;
        }
        Product pro = productService.save(product);
        if (pro != null) {
            modelAndView.addObject("message", "Create successfully");
            List<Category> categories = categoryService.selectAll();
            modelAndView.addObject("categories", categories);
        }
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editGet(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Product product = productService.selectById(id);
        if (product == null) {
            modelAndView.addObject("message", "ID invalid");
        } else {
            modelAndView.addObject("product", product);
        }
        List<Category> categories = categoryService.selectAll();
        modelAndView.addObject("categories", categories);
        return modelAndView;

    }

    @PostMapping("/edit/{id}")
    public ModelAndView editPost(@PathVariable("id") int id, @ModelAttribute Product product, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("edit");
        product.setId(id);
            if (product.getMultipartFile().getSize() != 0) {
                MultipartFile multipartFile = product.getMultipartFile();
                String fileName = multipartFile.getOriginalFilename();
                try {
                    FileCopyUtils.copy(product.getMultipartFile().getBytes(), new File(fileUpload + fileName));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                product.setImage("image/" + fileName);
            } else {
                product.setImage(productService.selectById(product.getId()).getImage());
        }

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("errorMessage", bindingResult.getAllErrors());
            List<Category> categories = categoryService.selectAll();
            modelAndView.addObject("categories", categories);
            modelAndView.addObject("product", new Product());
            return modelAndView;
        } else {
            Product product1 = productService.save(product);
            if (product1 != null) {
                List<Category> categories = categoryService.selectAll();
                modelAndView.addObject("categories", categories);
                modelAndView.addObject("message", "Edit successfully");
            }
            return modelAndView;
        }

    }
    @GetMapping("/view/{id}")
    public ModelAndView showDetail(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("detail");
        Product product = productService.selectById(id);
        if(product!=null){
            modelAndView.addObject("product", product);
        }else {
            modelAndView.addObject("message", "ID invalid");
        }
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id,@PageableDefault(2) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("list");
        Product product = productService.selectById(id);
        if(product == null){
            modelAndView.addObject(("message"), "ID Invalid");
        }
        productService.delete(id);
        Page<Product> products =  productService.findAll(pageable);
        modelAndView.addObject("products", products);
        return  modelAndView;
    }

    @PostMapping("/search")
    public ModelAndView search(@RequestParam("search") String search, Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("list");
        Page<Product> products = productService.searchByName(search, pageable);
        modelAndView.addObject("products", products);
        modelAndView.addObject("search", search);
        return modelAndView;
    }
}
