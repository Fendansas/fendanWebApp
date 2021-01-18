package by.grodno.pvt.site.webappsample.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import by.grodno.pvt.site.webappsample.domain.User;
import by.grodno.pvt.site.webappsample.domain.UserPicture;
import by.grodno.pvt.site.webappsample.dto.Avatar;
import by.grodno.pvt.site.webappsample.repo.UserPictureRepo;
import by.grodno.pvt.site.webappsample.service.StorageService;
import by.grodno.pvt.site.webappsample.service.UserService;

@Service
public class ImageStoreService implements StorageService {

	@Autowired
	private UserService service;
	@Autowired
	private UserPictureRepo pictureRepo;

	@Override
	public void store(Integer id, MultipartFile file) throws IOException {
		String string = UUID.randomUUID().toString();
		User user = service.getUser(id);

		File file2 = new File(string);

		UserPicture picture = user.getPicture();
		if (picture == null) {
			picture = new UserPicture();
		}
		picture.setFileName(file2.getAbsolutePath());

		user.setPicture(picture);

		try (InputStream in = file.getInputStream(); OutputStream out = new FileOutputStream(file2)) {
			IOUtils.copy(in, out);
		}
		pictureRepo.save(picture);
		service.saveUser(user);
	}

	@Override
	public Avatar getFile(Integer id) {
		User user = service.getUser(id);
		if (user.getPicture() != null && user.getPicture().getFileLocation() != null) {
			Avatar avatar = new Avatar();
			avatar.setFullFilePath(user.getPicture().getFileLocation());
			return avatar;
		}
		return null;
	}

}
