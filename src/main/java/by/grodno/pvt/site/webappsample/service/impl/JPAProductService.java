package by.grodno.pvt.site.webappsample.service.impl;

import by.grodno.pvt.site.webappsample.domain.Product;
import by.grodno.pvt.site.webappsample.repo.ProductRepo;
import by.grodno.pvt.site.webappsample.service.ProductService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Service
@Transactional
public class JPAProductService implements ProductService, InitializingBean {
    @Autowired
    private ProductRepo repo;

    @Override
    public void addProduct(List<Product> products) {
        repo.saveAll(products);
    }

    @Override
    public List<Product> getProducts() {
        return repo.findAll();
    }

    @Override
    public void deleteProduct(Integer number) {
        repo.deleteById(number);
    }

    @Override
    public List<Product> findByExample(Product productSample) {
        Example<Product> exp = Example.of(productSample,
                ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        return repo.findAll(exp);
    }

    @Override
    public Page<Product> getPage(Integer pageNum, Integer pageSize) {
        return repo.findAll(PageRequest.of(pageNum, pageSize, Sort.Direction.ASC, "firstName"));
    }

    @Override
    public List<Product> findByFName(String fname) {
        return repo.findByName(fname);
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        Product product1 = new Product(null, "AK-47", "The best jf the best",null, new BigDecimal(500),null);

        repo.save(product1);

    }



    @Override
    public Product getProduct(Integer id) {
        return repo.getOne(id);
    }

    @Override
    public void saveProduct(Product product) {
        repo.save(product);

    }
}
