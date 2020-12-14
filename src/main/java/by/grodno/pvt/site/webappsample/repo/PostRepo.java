package by.grodno.pvt.site.webappsample.repo;

import by.grodno.pvt.site.webappsample.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Integer> {
}
