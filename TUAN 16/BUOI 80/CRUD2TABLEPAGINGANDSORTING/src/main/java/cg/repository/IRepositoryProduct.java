package cg.repository;

import cg.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryProduct extends PagingAndSortingRepository<Product, Integer> {
    Page<Product> findAllByNameContaining(String name, Pageable pageable );
}
