package lk.rajaguru.web.app.core.service;

import jakarta.ejb.Remote;
import lk.rajaguru.web.app.core.model.Product;
import lk.rajaguru.web.app.core.model.ProductCategory;

import java.util.List;

@Remote
public interface ProductService {
    void saveProduct(Product product);
    Product getProduct(int id);
    List<Product> getProducts();
    Product getProductByName(String name);
    List<Product> getProductsByCategory(ProductCategory category);
    void updateProduct(Product product);
    void deleteProduct(Product product);
}
