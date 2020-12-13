package by.grodno.pvt.site.webappsample.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    private String avatarFileName;

    @Column(nullable = false)
    private BigDecimal price;

    @OneToMany(targetEntity=ItemInOrder.class,mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<ItemInOrder> itemInOrders;


}
