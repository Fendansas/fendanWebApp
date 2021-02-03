package by.grodno.pvt.site.webappsample.service;

import by.grodno.pvt.site.webappsample.domain.Product;

import by.grodno.pvt.site.webappsample.dto.ProductDTO;
import by.grodno.pvt.site.webappsample.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Boolean isProductInStock(Integer id);

    List<Product> getProducts();

    Product getProduct(Integer id);

    void saveProduct(Product product);

    //Page Products
    Page<Product> getProductPage(Integer pageNum, Integer size, String fieldName, Sort.Direction direction);

    void edit(ProductDTO productDTO);
}
