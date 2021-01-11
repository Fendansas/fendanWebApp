package by.grodno.pvt.site.webappsample.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.resource.HttpResource;




import by.grodno.pvt.site.webappsample.domain.User;
import by.grodno.pvt.site.webappsample.dto.Avatar;
import by.grodno.pvt.site.webappsample.dto.UserDTO;
import by.grodno.pvt.site.webappsample.service.StorageService;
import by.grodno.pvt.site.webappsample.service.UserService;

@Controller
public class UsersController {

	@Autowired
	private UserService userService;
	@Autowired
	private StorageService imgService;
	@Autowired
	private ConversionService convertionService;

	@GetMapping("/users")
	public String getAllUsers(Model model) {

		List<UserDTO> users = userService.getUsers().stream().map(u -> convertionService.convert(u, UserDTO.class))
				.collect(Collectors.toList());

		model.addAttribute("users", users);

		return "usersList";
	}

	@PostMapping("/users/{id}/img")
	public String handleFileUpload(@PathVariable("id") Integer id, @RequestParam("file") MultipartFile file)
			throws IOException {
		imgService.store(id, file);
		return "redirect:/users";
	}

	@GetMapping("/users/{id}/img")
	public void getImmage(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
		Avatar file = imgService.getFile(id);

		if (file != null) {
			try (InputStream is = file.getData()) {
				IOUtils.copy(is, response.getOutputStream());
			}
		}
	}

	@GetMapping("/apis/v1/users")
    @ResponseBody
    public List<UserDTO> getAllUsers() {
	    return userService.getUsers().stream().map(u-> convertionService.convert(u,UserDTO.class))
                .collect(Collectors.toList());
    }

}