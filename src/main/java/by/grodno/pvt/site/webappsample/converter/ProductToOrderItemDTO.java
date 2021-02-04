package by.grodno.pvt.site.webappsample.converter;

import by.grodno.pvt.site.webappsample.domain.OrderItem;
import by.grodno.pvt.site.webappsample.dto.OrderItemDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductToOrderItemDTO implements Converter<OrderItem, OrderItemDTO> {

    @Override
    public OrderItemDTO convert(OrderItem orderItem) {

        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(orderItem.getId());
        orderItemDTO.setPrice(orderItem.getPrice());
        orderItemDTO.setName(orderItem.getProduct().getName());
        orderItemDTO.setDescription(orderItem.getProduct().getDescription());
        orderItemDTO.setQuantity(orderItem.getQuantity());

        return orderItemDTO;
    }
}
