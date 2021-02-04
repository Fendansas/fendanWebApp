package by.grodno.pvt.site.webappsample.service.impl;

import by.grodno.pvt.site.webappsample.domain.OrderItem;
import by.grodno.pvt.site.webappsample.domain.Product;
import by.grodno.pvt.site.webappsample.repo.OrderItemRepo;
import by.grodno.pvt.site.webappsample.service.OrderItemService;
import by.grodno.pvt.site.webappsample.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderItemRepo repo;

    @Override
    public OrderItem createOrderItemWithProduct(Integer id, Integer quantity) {
        Product product = productService.getProduct(id);
        OrderItem orderItem = new OrderItem(product.getPrice().multiply(BigDecimal.valueOf(quantity)),
                quantity,  product);

        return repo.save(orderItem);
    }
}
