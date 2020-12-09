package by.grodno.pvt.site.webappsample.service;

import by.grodno.pvt.site.webappsample.domain.Product;
import by.grodno.pvt.site.webappsample.domain.User;

import java.util.List;

public interface ProductRepository {
    List<Product> getProducts();

    void addProduct(List<Product> products);

    void deleteProduct(Integer number);
}
