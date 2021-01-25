package by.grodno.pvt.site.webappsample.repo;

import by.grodno.pvt.site.webappsample.domain.ProductPicture;
//import by.grodno.pvt.site.webappsample.domain.UserPicture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPictureRepo extends JpaRepository<ProductPicture, Integer> {


}
