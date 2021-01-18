package by.grodno.pvt.site.webappsample.converter;


import by.grodno.pvt.site.webappsample.domain.Product;
import by.grodno.pvt.site.webappsample.dto.ProductDTO;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class ProductDomainToDTOConverter implements Converter<Product, ProductDTO> {


    @Override
    public ProductDTO convert(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());

        return productDTO;
    }
}
