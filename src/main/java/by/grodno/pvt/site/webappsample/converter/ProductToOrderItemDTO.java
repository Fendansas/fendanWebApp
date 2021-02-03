package by.grodno.pvt.site.webappsample.converter;

import by.grodno.pvt.site.webappsample.domain.Product;
import by.grodno.pvt.site.webappsample.dto.OrderItemDTO;
import by.grodno.pvt.site.webappsample.dto.ProductDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductToOrderItemDTO implements Converter<Product, OrderItemDTO> {
    @Override
    public OrderItemDTO convert(Product product) {

        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(product.getId());
        orderItemDTO.setPrice(product.getPrice());
        orderItemDTO.setName(product.getName());
        orderItemDTO.setDescription(product.getDescription());

        return orderItemDTO;
    }
}
