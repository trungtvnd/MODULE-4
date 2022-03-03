package cg.service;

import cg.model.Category;
import cg.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IProductService {
    Page<Product> findAll(Pageable pageable);

    Optional<Product> findOne(int id);

    void save(Product product);

    void delete(int id);

    Page<Product> findAllByName(String name, Pageable pageable);


}
