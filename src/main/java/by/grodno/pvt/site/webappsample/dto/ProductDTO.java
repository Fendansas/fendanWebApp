package by.grodno.pvt.site.webappsample.dto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {

    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
}


