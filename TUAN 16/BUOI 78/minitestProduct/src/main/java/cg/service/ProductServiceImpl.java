package cg.service;

import cg.model.Product;
import cg.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private IProductRepository productRepository;




    @Override
    public List<Product> selectAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product selectById(int id) {
        if(productRepository.findById(id).isPresent()){
            return productRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public List<Product> searchProduct(String key) {
        return (List<Product>) productRepository.findAllByNameContaining(key);
    }


}
