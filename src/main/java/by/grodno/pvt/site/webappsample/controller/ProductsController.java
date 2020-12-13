package by.grodno.pvt.site.webappsample.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import by.grodno.pvt.site.webappsample.service.ProductService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.resource.HttpResource;




import by.grodno.pvt.site.webappsample.dto.Avatar;
import by.grodno.pvt.site.webappsample.dto.ProductDTO;
import by.grodno.pvt.site.webappsample.service.StorageService;
import by.grodno.pvt.site.webappsample.service.UserService;


@Controller
public class ProductsController {
    @Autowired
    private ProductService productService;
    @Autowired
    private StorageService imgService;
    @Autowired
    private ConversionService convertionService;

    @GetMapping("/products")
    public String getAllProducts(Model model) {

        List<ProductDTO> products = productService.getProducts().stream().map(u -> convertionService.convert(u, ProductDTO.class))
                .collect(Collectors.toList());

        model.addAttribute("products", products);

        return "products";
    }

    @PostMapping("/products/{id}/img")
    public String handleFileUpload(@PathVariable("id") Integer id, @RequestParam("file") MultipartFile file)
            throws IOException {
        imgService.store(id, file);
        return "redirect:/products";
    }

    @GetMapping("/products/{id}/img")
    public void getImmage(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
        Avatar file = imgService.getFile(id);

        if (file != null) {
            try (InputStream is = file.getData()) {
                IOUtils.copy(is, response.getOutputStream());
            }
        }
    }

}

