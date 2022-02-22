package controllers;

import model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.ProductService;

import java.util.List;

@Controller
@RequestMapping("products")
public class ProductController {
    private final ProductService productService = new ProductService();
    @GetMapping("")
    public String display(Model model){
        List<Product> products = productService.selectAll();
        model.addAttribute("products", products);
        return "display";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "/create";
    }

    @PostMapping("/create")
    public String createPost(Product product) {
        product.setId((int) (Math.random() * 10000));
        productService.create(product);
        return "redirect:/products";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.select(id));
        return "/edit";
    }

    @PostMapping("/edit")
    public String update(Product product) {
     for(int i = 0; i< productService.selectAll().size(); i++){
         if(productService.selectAll().get(i).getId() == product.getId()){
             productService.editById(i, product);
         }
     }
        return "redirect:/products";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.select(id));
        return "/delete";
    }
    @PostMapping("/delete")
    public String delete(Product product, RedirectAttributes redirect) {
        for(int i = 0; i< productService.selectAll().size(); i++){
            if(productService.selectAll().get(i).getId() == product.getId()){
                productService.deleteById(i);
            }
        }
        redirect.addFlashAttribute("success", "Removed customer successfully!");
        return "redirect:/products";
    }
    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.select(id));
        return "/view";
    }

}
