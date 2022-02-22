package service;

import model.Product;

import java.util.List;

public interface IProductService {
    List<Product> selectAll();

    Product select(int id);

    void create(Product product);

    void editById(int index, Product product);

    void deleteById(int index);
}
