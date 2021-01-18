package by.grodno.pvt.site.webappsample.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import by.grodno.pvt.site.webappsample.domain.User;
import by.grodno.pvt.site.webappsample.domain.UserPicture;

public interface UserPictureRepo extends JpaRepository<UserPicture, Integer> {
}
