package cg.service;

import cg.model.Category;
import cg.model.Product;
import cg.repository.IRepositoryCategory;
import cg.repository.IRepositoryProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class ProductService implements IProductService{
    @Autowired
    private IRepositoryProduct repository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
      return (Page<Product>) repository.findAll();
    }

    @Override
    public Optional<Product> findOne(int id) {
        if(repository.findById(id).isPresent()){
            return repository.findById(id);
        }
        return Optional.empty();
    }

    @Override
    public void save(Product product) {
        repository.save(product);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Product> findAllByName(String name, Pageable pageable) {
        return repository.findAllByNameContaining(name, pageable);
    }


}
