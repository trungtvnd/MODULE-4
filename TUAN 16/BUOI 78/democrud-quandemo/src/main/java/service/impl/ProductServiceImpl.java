package service.impl;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ICategoryService;
import service.IProductService;

import java.util.ArrayList;
@Service
public class ProductService implements IProductService {

    @Autowired
    private ICategoryService categoryService;
    @Override
    public ArrayList<Product> getAllProduct() {
        return null;
    }

    @Override
    public String saveProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Product product) {

    }

    @Override
    public Product getProduct(int id) {
        return null;
    }
}
