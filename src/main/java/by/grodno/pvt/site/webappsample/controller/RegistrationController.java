package by.grodno.pvt.site.webappsample.controller;


import by.grodno.pvt.site.webappsample.domain.User;
import by.grodno.pvt.site.webappsample.dto.UserRegistrationDTO;
import by.grodno.pvt.site.webappsample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserService service;

    @Autowired
    private ConversionService conversionService;

    @GetMapping("/acrivate/{id}")
    String activation(@PathVariable Integer id) {
        service.activateUser(id);
        return "redirect:/login";
    }

    @PostMapping("/register/new")
    String registerPage(UserRegistrationDTO nesUserDTO){
        service.saveUser(conversionService.convert(nesUserDTO, User.class));
        return "redirect:/login";

    }

}
