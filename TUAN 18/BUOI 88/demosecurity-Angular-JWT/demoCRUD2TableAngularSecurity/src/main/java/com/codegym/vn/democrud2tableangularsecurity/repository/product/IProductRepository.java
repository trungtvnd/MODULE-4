package com.codegym.vn.democrud2tableangularsecurity.repository.product;



import com.codegym.vn.democrud2tableangularsecurity.model.product.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IProductRepository extends PagingAndSortingRepository<Product, Long> {
    Iterable<Product> findAllByNameContaining(String name);
}
