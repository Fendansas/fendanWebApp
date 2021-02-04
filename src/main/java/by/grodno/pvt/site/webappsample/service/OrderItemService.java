package by.grodno.pvt.site.webappsample.service;

import by.grodno.pvt.site.webappsample.domain.OrderItem;

public interface OrderItemService {
  //  рдер айтем сервис который умеет создавать ордер айтемы из продуктов

    OrderItem createOrderItemWithProduct(Integer id, Integer quantity);
}
