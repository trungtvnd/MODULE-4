package cg.service;

import cg.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {

    Iterable<Product>  selectAll();

    Product save (Product product);

    void delete(int id);

    Product selectById(int id);

    Page<Product> searchByName(String name, Pageable pageable);

    Page<Product> findAll(Pageable pageable);

}
