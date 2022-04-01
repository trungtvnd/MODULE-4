package com.codegym.vn.democrud2tableangularsecurity.controller.product;


import com.codegym.vn.democrud2tableangularsecurity.model.product.Category;
import com.codegym.vn.democrud2tableangularsecurity.model.product.Product;
import com.codegym.vn.democrud2tableangularsecurity.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/products")
public class ProductController {
    @Autowired
    private IProductService iProductService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> showAll(){
        Iterable<Product> products = iProductService.findAll();
        if (!products.iterator().hasNext()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Product>> showAllPage(@PageableDefault(2) Pageable pageable){
        Page<Product> products = iProductService.findPage(pageable);
        if (!products.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<Iterable<Category>> showAllCategory(){
        Iterable<Category> categories = iProductService.findAllCategory();
        if (!categories.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> showOne(@PathVariable("id") Long id){
        Optional<Product> product = iProductService.findById(id);
        if (!product.isPresent()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return new ResponseEntity<>(iProductService.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@RequestBody Product product,@PathVariable("id") Long id ){
        Optional<Product> productEdit = iProductService.findById(id);
        if(!productEdit.isPresent()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product.setId(productEdit.get().getId());
        product = iProductService.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
        Optional<Product> productDelete = iProductService.findById(id);
        if(!productDelete.isPresent()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iProductService.delete(id);
        return new ResponseEntity<>(productDelete.get(), HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<Iterable<Product>> showAllByName(@RequestParam("search") String name){
        Iterable<Product> products = iProductService.findAllByName(name);
        if (!products.iterator().hasNext()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file")MultipartFile multipartFile
                                         ,@RequestParam("name") String name){
        System.out.println(multipartFile.getOriginalFilename());
        System.out.println(name);
        return new ResponseEntity<>("done", HttpStatus.OK);
    }
    @PostMapping("/upload1")
    public ResponseEntity<String > upload1(@RequestParam("file") MultipartFile multipartFile,
                                           @RequestParam("product") Product product){
        System.out.println(multipartFile.getOriginalFilename());
        System.out.println(product.getName());
        product.setDescription(multipartFile.getOriginalFilename());
        Category category = new Category();
        category.setId(1L);
        product.setCategory(category);
        iProductService.save(product);
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }
}
