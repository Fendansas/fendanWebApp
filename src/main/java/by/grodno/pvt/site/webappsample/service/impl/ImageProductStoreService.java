package by.grodno.pvt.site.webappsample.service.impl;

import by.grodno.pvt.site.webappsample.domain.Product;
import by.grodno.pvt.site.webappsample.domain.ProductPicture;
import by.grodno.pvt.site.webappsample.domain.User;
import by.grodno.pvt.site.webappsample.domain.UserPicture;
import by.grodno.pvt.site.webappsample.dto.Avatar;
import by.grodno.pvt.site.webappsample.dto.AvatarP;
import by.grodno.pvt.site.webappsample.repo.ProductPictureRepo;
import by.grodno.pvt.site.webappsample.repo.UserPictureRepo;
import by.grodno.pvt.site.webappsample.service.ProductService;
import by.grodno.pvt.site.webappsample.service.StorageProductService;
import by.grodno.pvt.site.webappsample.service.StorageService;
import by.grodno.pvt.site.webappsample.service.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Service
public class ImageProductStoreService implements StorageProductService {

	@Autowired
	private ProductService service;
	@Autowired
	private ProductPictureRepo pictureRepo;

	@Override
	public void store(Integer id, MultipartFile file) throws IOException {
		String string = UUID.randomUUID().toString();
		Product product = service.getProduct(id);

		File file2 = new File(string);

		ProductPicture picture = product.getPicture();
		if (picture == null) {
			picture = new ProductPicture();
		}
		picture.setFileName(file2.getName());
		picture.setFileLocation(file2.getAbsolutePath());

		product.setPicture(picture);

		try (InputStream in = file.getInputStream(); OutputStream out = new FileOutputStream(file2)) {
			IOUtils.copy(in, out);
		}
		picture.setProduct(product);
		pictureRepo.save(picture);
		service.saveProduct(product);
	}

	@Override
	public AvatarP getFile(Integer id) {
		Product product = service.getProduct(id);
		if (product.getPicture() != null && product.getPicture().getFileLocation() != null) {
			AvatarP avatar = new AvatarP();
			avatar.setFullFilePath(product.getPicture().getFileLocation());
			return avatar;
		}
		return null;
	}

}
