package cg.repository;

import cg.model.Product;

import java.util.List;


public interface IProductRepository {
   List<Product> selectAll();

   Product create(Product product);

   Product delete(int id);

   Product selectById(int id);
   List<Product> search(String key);
}
