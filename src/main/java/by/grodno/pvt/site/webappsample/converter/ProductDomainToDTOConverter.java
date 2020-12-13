package by.grodno.pvt.site.webappsample.converter;

import by.grodno.pvt.site.webappsample.domain.Product;
import by.grodno.pvt.site.webappsample.dto.ProductDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class ProductDomainToDTOConverter implements Converter<Product, ProductDTO> {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public ProductDTO convert(Product source) {

        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        MAPPER.setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL);
        return MAPPER.convertValue(source, ProductDTO.class);
    }
}
