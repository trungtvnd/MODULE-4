package cg.repository;

import cg.model.Product;
import org.springframework.data.repository.CrudRepository;



public interface IProductRepository extends CrudRepository<Product, Integer> {
   Iterable<Product> findAllByNameContaining(String name);
}
