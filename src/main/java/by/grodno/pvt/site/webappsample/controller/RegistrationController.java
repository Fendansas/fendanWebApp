package by.grodno.pvt.site.webappsample.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.spring5.expression.Fields;

import by.grodno.pvt.site.webappsample.domain.User;
import by.grodno.pvt.site.webappsample.dto.UserRegistrationDTO;
import by.grodno.pvt.site.webappsample.service.UserService;

@Controller
public class RegistrationController {

	@Autowired
	private UserService service;
	@Autowired
	private ConversionService convertionService;

	@GetMapping("/activate/{id}")
	String activation(@PathVariable Integer id) {

		service.activateUser(id);

		return "redirect:/login";
	}

	@GetMapping("/register")
	String getForm(UserRegistrationDTO user, Model model) {
		if (user == null) {
			user = new UserRegistrationDTO();
		}
		model.addAttribute("user", user);
		return "registerView";
	}

	
	@PostMapping("/register/new")
	String registerPage(@Valid UserRegistrationDTO user, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("userRegistrationDTO", user);
			return "registerView";
		}
		
		service.saveUser(convertionService.convert(user, User.class));

		return "redirect:/login";
	}

}
