package service;

import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {

    private static List<Product> products;

    static {
        products = new ArrayList<>();
        products.add(new Product(1, "MacBook Pro ", 1500, "Laptop", "Apple"));
        products.add(new Product(2, "Dell Xps 13", 1400, "Laptop", "Dell"));
        products.add(new Product(3, "Lenovo Thinkpad t590", 1500, "Laptop", "Lenovo"));
        products.add(new Product(4, "MSI Pro ", 1300, "Laptop", "MSI"));
    }

    @Override
    public List<Product> selectAll() {
        return products;
    }

    @Override
    public Product select(int id) {
        Product product = null;
        for (Product p : products) {
            if (p.getId() == id) {
                product = p;
            }
        }
        return product;
    }

    @Override
    public void create(Product product) {
        products.add(product);
    }

    @Override
    public void editById(int index, Product product) {
        products.set(index, product);
    }

    @Override
    public void deleteById(int index) {
        products.remove(index);
    }
}
