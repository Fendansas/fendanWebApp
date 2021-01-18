package by.grodno.pvt.site.webappsample.domain;//package by.grodno.pvt.site.webappsample.domain;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.util.Date;
//import java.util.List;
//
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "order")
//public class Order {
//
//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    private Integer id;
//
//    private Date creationDate;
//
//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(
//            joinColumns = @JoinColumn(name = "p1"),
//            inverseJoinColumns = @JoinColumn(name = "p2"))
//    private List<Product> productsOrder;
//
//
//
//}
