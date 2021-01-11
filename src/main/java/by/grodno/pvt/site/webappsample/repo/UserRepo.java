package by.grodno.pvt.site.webappsample.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import by.grodno.pvt.site.webappsample.domain.User;



public interface UserRepo extends JpaRepository<User, Integer> {



    User findByEmail(String email);
}
