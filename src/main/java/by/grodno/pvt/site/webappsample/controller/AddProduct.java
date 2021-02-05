package by.grodno.pvt.site.webappsample.controller;

import by.grodno.pvt.site.webappsample.domain.Product;
import by.grodno.pvt.site.webappsample.domain.User;
import by.grodno.pvt.site.webappsample.dto.ProductDTO;
import by.grodno.pvt.site.webappsample.dto.UserRegistrationDTO;
import by.grodno.pvt.site.webappsample.service.ProductService;
import by.grodno.pvt.site.webappsample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AddProduct {

    @Autowired
    private ProductService service;
    @Autowired
    private ConversionService convertionService;

    @GetMapping("/addProduct")
    String getForm(ProductDTO productDTO, Model model) {
        if (productDTO == null) {
            productDTO = new ProductDTO();
        }
        model.addAttribute("product", productDTO);
        return "addProductView";
    }

    @PostMapping("/addProduct/new")
    String registerPage(@Valid ProductDTO productDTO, BindingResult br, Model model) {

        if (br.hasErrors()) {
            model.addAttribute("productDTO", productDTO);
            System.out.println("Не коректные введеные данные");
            return "redirect:/addProduct";
        }

        service.saveProduct(convertionService.convert(productDTO, Product.class));

        return "redirect:/products";
    }



}
