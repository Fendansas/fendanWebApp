package by.grodno.pvt.site.webappsample.repo;

import by.grodno.pvt.site.webappsample.domain.User;
import by.grodno.pvt.site.webappsample.domain.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<UserOrder, Integer> {

    UserOrder findOrderByUserAndStatusProcessing(User user);
}
