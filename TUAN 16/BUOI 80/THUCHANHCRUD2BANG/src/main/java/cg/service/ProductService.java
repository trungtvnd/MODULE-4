package cg.service;

import cg.model.Product;
import cg.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository repository;
    @Override
    public List<Product> selectAll() {
        return (List<Product>) repository.findAll();
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public Product selectById(int id) {
       if(repository.findById(id).isPresent()){
           return repository.findById(id).get();
       }return null;
    }

    @Override
    public Page<Product> searchByName(String name, Pageable pageable) {
        return repository.findAllByNameContaining(name, pageable);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
