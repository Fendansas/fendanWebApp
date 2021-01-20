package by.grodno.pvt.site.webappsample.service;


import by.grodno.pvt.site.webappsample.dto.Avatar;
import by.grodno.pvt.site.webappsample.dto.AvatarP;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageProductService {

	void store(Integer id, MultipartFile file) throws IOException;

	AvatarP getFile(Integer id);
}
