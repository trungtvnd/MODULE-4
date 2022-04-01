package com.codegym.vn.democrud2tableangularsecurity.service.product;


import com.codegym.vn.democrud2tableangularsecurity.model.product.Category;
import com.codegym.vn.democrud2tableangularsecurity.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IProductService {
    Iterable<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    void delete(Long id);

    Iterable<Product> findAllByName(String name);

    Iterable<Category> findAllCategory();

    Page<Product> findPage(Pageable pageable);
}
