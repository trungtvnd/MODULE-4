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
        return productRepository.selectAll();
    }

    @Override
    public Product create(Product product) {
        return productRepository.create(product);
    }

    @Override
    public Product delete(int id) {
        return productRepository.delete(id);
    }

    @Override
    public Product selectById(int id) {
        return productRepository.selectById(id);
    }

    @Override
    public List<Product> searchProduct(String key) {
        return productRepository.search(key);
    }


}
