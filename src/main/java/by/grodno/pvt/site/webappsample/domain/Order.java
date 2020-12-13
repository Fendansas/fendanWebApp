package by.grodno.pvt.site.webappsample.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ORDER_TABLE")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @OneToMany(targetEntity=ItemInOrder.class,mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<ItemInOrder> itemInOrders;

    @OneToOne(mappedBy = "order")
    private User user;



}

