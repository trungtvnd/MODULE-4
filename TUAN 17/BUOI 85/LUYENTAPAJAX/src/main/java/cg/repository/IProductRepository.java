package cg.repository;

import cg.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;

public interface IProductRepository extends PagingAndSortingRepository<Product, Long> {
    Iterable<Product> findAllByNameContaining(String name);
}
