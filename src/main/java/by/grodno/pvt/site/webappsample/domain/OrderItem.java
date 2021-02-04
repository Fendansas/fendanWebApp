package by.grodno.pvt.site.webappsample.domain;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,name = "price")
    private BigDecimal price;

    @Positive
    @Column(nullable = false,name = "quantity")
    private Integer quantity;

    @OneToOne
    private Product product;

    @ManyToOne
    private UserOrder order;

    public OrderItem(BigDecimal price, @Positive Integer quantity, Product product) {
        this.price = price;
        this.quantity = quantity;
        this.product = product;
    }
}
