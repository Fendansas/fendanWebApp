package by.grodno.pvt.site.webappsample.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.criterion.Order;


import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
//сделать дто
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private BigDecimal price;

    @Positive
    @Column(nullable = false)
    private Integer quantity;

    @OneToOne
    private Product product;

    @ManyToOne
    private Order order;
}
