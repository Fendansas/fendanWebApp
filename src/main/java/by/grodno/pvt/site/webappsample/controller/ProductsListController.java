package by.grodno.pvt.site.webappsample.controller;

import by.grodno.pvt.site.webappsample.domain.Product;

import by.grodno.pvt.site.webappsample.dto.Avatar;
import by.grodno.pvt.site.webappsample.dto.ProductDTO;

import by.grodno.pvt.site.webappsample.repo.ProductRepo;
import by.grodno.pvt.site.webappsample.service.ProductService;
import by.grodno.pvt.site.webappsample.service.StorageService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductsListController {

    static public final Integer SIZE = 5;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductService productService;
    @Autowired
    private StorageService imgService;
    @Autowired
    private ConversionService convertionService;

    @GetMapping("/productslist")
    public String getAllProducts(@RequestParam(required = false, name = "pn") Integer pageNum,
                                 @RequestParam(required = false, name = "sort") Sort.Direction sortDirection,
                                 @RequestParam(required = false, name = "fieldName") String sortField, Model model) {
        if (pageNum == null) {
            pageNum = Integer.valueOf(0);
        } else {
            pageNum -= 1;
        }
        Page<Product> productsPage = productService.getProductPage(pageNum, SIZE, sortField, sortDirection);

        List<ProductDTO> productDTOS = productsPage.get().map(p -> convertionService.convert(p, ProductDTO.class))
                .collect(Collectors.toList());
        model.addAttribute("products", productDTOS);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", productsPage.getTotalPages());
        model.addAttribute("fieldName", sortField);
        model.addAttribute("sort", sortDirection);
        return "productslist";
    }

    @PostMapping("/productslist/{id}/img")
    public String handleFileUpload(@PathVariable("id") Integer id, @RequestParam("file") MultipartFile file)
            throws IOException {
        imgService.store(id, file);
        return "redirect:/productslist";
    }

    @GetMapping("/productslist/{id}/img")
    public void getImmage(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
        Avatar file = imgService.getFile(id);

        if (file != null) {
            try (InputStream is = file.getData()) {
                IOUtils.copy(is, response.getOutputStream());
            }
        }
    }

    @GetMapping("/apis/v1/productslist")
    @ResponseBody
    public List<ProductDTO> getAllPoducts() {
        return productService.getProducts().stream().map(u -> convertionService.convert(u, ProductDTO.class))
                .collect(Collectors.toList());
    }


    // Изминения
        @GetMapping("/productslist/edit/{id}")
        @PreAuthorize("@editUserVouter.checkUserId(authentication,#id)")
        public String editProductForm(@PathVariable Integer id, Model model) {

            model.addAttribute("product", productService.getProduct(id));

            return "editProductForm";
    }

    @PostMapping("/productslist/edit/{id}")
    public String editProduct(@PathVariable Integer id, @Valid ProductDTO productDTO, BindingResult br, Model model) {

        if (br.hasErrors()) {
            model.addAttribute("productDTO", productDTO);
            return "editProductView";
        }

        Product product = new Product();
        product.setId(id);
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setQuantity(productDTO.getQuantity());
        productService.edit(productDTO);

        return "redirect:/productslist";
    }
//Details
    @GetMapping("/userProducts/edit/{id}")
    @PreAuthorize("@editUserVouter.checkUserId(authentication,#id)")
    public String details(@PathVariable Integer id, Model model) {

        model.addAttribute("product", productService.getProduct(id));

        return "editProductForm";}


   // details
}
