package by.grodno.pvt.site.webappsample.service;

import by.grodno.pvt.site.webappsample.domain.OrderItem;
import by.grodno.pvt.site.webappsample.domain.User;
import by.grodno.pvt.site.webappsample.domain.UserOrder;

public interface OrderService {

    UserOrder getOrCreateOrder(User user);

    UserOrder addOrderItem(UserOrder order, OrderItem item);
}
