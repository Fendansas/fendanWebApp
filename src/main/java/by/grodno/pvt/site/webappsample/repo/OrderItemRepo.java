package by.grodno.pvt.site.webappsample.repo;

import by.grodno.pvt.site.webappsample.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepo  extends JpaRepository<OrderItem, Integer> {

}
