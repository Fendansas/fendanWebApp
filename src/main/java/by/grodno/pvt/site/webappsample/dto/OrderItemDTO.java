package by.grodno.pvt.site.webappsample.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDTO {

    private Integer id;

    private BigDecimal price;

    private String name;

    private String description;


}
