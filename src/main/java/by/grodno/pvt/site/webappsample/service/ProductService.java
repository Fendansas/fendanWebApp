package by.grodno.pvt.site.webappsample.service;
import by.grodno.pvt.site.webappsample.domain.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    Product getProduct(Integer id);

    void addProduct(List<Product> products);

    void saveProduct(Product product);

    void deleteProduct(Integer number);

    List<Product> findByExample(Product productSample);

    Page<Product> getPage(Integer pageNum, Integer pageSize);

    List<Product> findByFName(String fname);
}
