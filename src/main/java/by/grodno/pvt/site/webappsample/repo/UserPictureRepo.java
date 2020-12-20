package by.grodno.pvt.site.webappsample.repo;

import by.grodno.pvt.site.webappsample.domain.Picture;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPictureRepo extends JpaRepository<Picture, Integer> {
}
