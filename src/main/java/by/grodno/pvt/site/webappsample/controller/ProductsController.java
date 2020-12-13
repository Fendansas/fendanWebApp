package by.grodno.pvt.site.webappsample.controller;

import by.grodno.pvt.site.webappsample.domain.Product;

import by.grodno.pvt.site.webappsample.service.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductsController {
    @Autowired
    private ProductRepository repo;

    @PostMapping(path = "/products", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveProduct(@RequestBody List<Product> product) {
        repo.addProduct(product);
    }

    @GetMapping("/products")
    public List<Product> getAllPoducts() {
        return repo.getProducts();
    }


}

