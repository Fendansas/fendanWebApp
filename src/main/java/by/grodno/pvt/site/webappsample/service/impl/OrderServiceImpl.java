package by.grodno.pvt.site.webappsample.service.impl;

import by.grodno.pvt.site.webappsample.domain.User;
import by.grodno.pvt.site.webappsample.domain.UserOrder;
import by.grodno.pvt.site.webappsample.repo.OrderRepo;
import by.grodno.pvt.site.webappsample.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo repo;

    @Override
    public UserOrder getOrCreateOrder(User user) {
        UserOrder order = repo.findOrderByUserAndStatusProcessing(user);

        if (order == null) {
            order = new UserOrder();
            order.setUser(user);
            repo.save(order);
        }

        return order;
    }
}
