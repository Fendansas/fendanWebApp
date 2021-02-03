package by.grodno.pvt.site.webappsample.service.impl;


import by.grodno.pvt.site.webappsample.domain.Product;
import by.grodno.pvt.site.webappsample.domain.User;
import by.grodno.pvt.site.webappsample.dto.ProductDTO;
import by.grodno.pvt.site.webappsample.dto.UserDTO;
import by.grodno.pvt.site.webappsample.exception.ProductNotFoundException;
import by.grodno.pvt.site.webappsample.exception.UserNotFoundException;
import by.grodno.pvt.site.webappsample.repo.ProductRepo;
import by.grodno.pvt.site.webappsample.service.ProductService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JPAProductService implements ProductService, InitializingBean {

    @Autowired
    private ProductRepo repo;

    @Override
    public Boolean isProductInStock(Integer id) {
        Product product = getProduct(id);
        return product.getQuantity() > 0;
    }

    @Override
    public List<Product> getProducts() {
        return repo.findAll();
    }

    //------ PAGE
    @Override
    public Page<Product> getProductPage(Integer pageNum, Integer size, String fieldName, Sort.Direction direction) {
        Pageable pagable;
        if (fieldName != null) {
            if (direction == null)
                direction = Sort.Direction.ASC;
            pagable = PageRequest.of(pageNum, size, direction, fieldName);
        } else {
            pagable = PageRequest.of(pageNum, size);
        }

        return repo.findAll(pagable);
    }
    //-----

    @Override
    public void afterPropertiesSet() throws Exception {

//        Product product1 = new Product(null, "AK-47", "The best of the best",null, new BigDecimal(500), 5,null,null, null);
//        repo.save(product1);
//        Product product2 = new Product(null, "M4", "US ARMY",null, new BigDecimal(500),5,null, null,null);
//        repo.save(product2);
//        Product product3 = new Product(null, "G3", "7.62*51",null, new BigDecimal(500),5,null,null,null);
//        repo.save(product3);
//        Product product4 = new Product(null, "G36", "Germany",null, new BigDecimal(500),5,null,null,null);
//        repo.save(product4);
//        Product product5 = new Product(null, "M1911", "Colt",null, new BigDecimal(500),5,null,null,null);
//        repo.save(product5);
//        Product product6 = new Product(null, "PM", "RF",null, new BigDecimal(500),5,null,null,null);
//        repo.save(product6);
//        Product product7 = new Product(null, "G17", "Glock",null, new BigDecimal(500),5,null,null,null);
//        repo.save(product7);
//        Product product8 = new Product(null, "P99", "Walter",null, new BigDecimal(500),5,null,null,null);
//        repo.save(product8);

    }


    @Override
    public Product getProduct(Integer id) {
        return repo.getOne(id);
    }

    @Override
    public void saveProduct(Product product) {
        repo.save(product);

    }


    @Override
    public Optional<Product> findById(Integer id) {
        return repo.findById(id);
    }

    @Override
    public void edit(ProductDTO productDTO) {
        Product findById = repo.findById(productDTO.getId()).orElseThrow(() -> new ProductNotFoundException());
        findById.setName(productDTO.getName());
        findById.setDescription(productDTO.getDescription());
        findById.setPrice(productDTO.getPrice());
        findById.setQuantity(productDTO.getQuantity());

        repo.save(findById);
    }

}
