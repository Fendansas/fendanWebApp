package by.grodno.pvt.site.webappsample.repo;//package by.grodno.pvt.site.webappsample.repo;

import by.grodno.pvt.site.webappsample.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {


}
