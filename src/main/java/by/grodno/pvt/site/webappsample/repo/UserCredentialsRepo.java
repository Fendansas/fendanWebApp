package by.grodno.pvt.site.webappsample.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import by.grodno.pvt.site.webappsample.domain.UserCredentials;

public interface UserCredentialsRepo extends JpaRepository<UserCredentials, Integer> {

}
