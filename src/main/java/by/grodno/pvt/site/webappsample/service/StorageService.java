package by.grodno.pvt.site.webappsample.service;


import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import by.grodno.pvt.site.webappsample.dto.Avatar;

public interface StorageService {

	void store(Integer id, MultipartFile file) throws IOException;

	Avatar getFile(Integer id);
}
