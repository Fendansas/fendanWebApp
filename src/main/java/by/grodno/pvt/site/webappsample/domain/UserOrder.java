package by.grodno.pvt.site.webappsample.domain;//package by.grodno.pvt.site.webappsample.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "user_order")
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date creationDate;

    @NotNull
    @Column(name = "total_price")
    private BigDecimal totalPrice = BigDecimal.ZERO;

    private OrderStatus status = OrderStatus.PROCESSING;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public void addOrderItem(OrderItem orderItem) {
        this.totalPrice = totalPrice.add(orderItem.getPrice());
        this.items.add(orderItem);
    }
}
