package by.grodno.pvt.site.webappsample.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import by.grodno.pvt.site.webappsample.domain.User;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {

    List<User> findByFirstName(String firstName);

    Optional<User> findByUsername(String userName);
}
