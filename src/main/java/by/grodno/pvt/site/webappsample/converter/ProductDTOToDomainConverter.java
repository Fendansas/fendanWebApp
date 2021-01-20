package by.grodno.pvt.site.webappsample.converter;


import by.grodno.pvt.site.webappsample.domain.Product;
import by.grodno.pvt.site.webappsample.dto.ProductDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class ProductDTOToDomainConverter implements Converter<ProductDTO, Product> {


    @Override
    public Product convert(ProductDTO productDTO) {
        Product product1= new Product();
        product1.setId(productDTO.getId());
        product1.setName(productDTO.getName());
        product1.setDescription(productDTO.getDescription());
        product1.setPrice(productDTO.getPrice());
        product1.setQuantity(productDTO.getQuantity());

        return product1;
    }


}
