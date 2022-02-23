package cg.service;

import cg.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> selectAll();

    Product create(Product product);

    Product delete(int id);

    Product selectById(int id);

    List<Product> searchProduct(String key);
}
