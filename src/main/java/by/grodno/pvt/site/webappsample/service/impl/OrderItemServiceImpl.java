package by.grodno.pvt.site.webappsample.service.impl;

import by.grodno.pvt.site.webappsample.domain.OrderItem;
import by.grodno.pvt.site.webappsample.domain.Product;
import by.grodno.pvt.site.webappsample.repo.ProductRepo;
import by.grodno.pvt.site.webappsample.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    ProductRepo repo;

    @Override
    public OrderItem addProduct(Integer id) {
        OrderItem orderItem = new OrderItem();
        Optional<Product> product = repo.findById(id);

        orderItem.setOrder(product);

        return null;
    }
}
