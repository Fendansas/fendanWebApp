package by.grodno.pvt.site.webappsample.controller;//package by.grodno.pvt.site.webappsample.controller;

import by.grodno.pvt.site.webappsample.converter.ProductDTOToDomainConverter;
import by.grodno.pvt.site.webappsample.domain.Product;
import by.grodno.pvt.site.webappsample.domain.User;
import by.grodno.pvt.site.webappsample.dto.ProductDTO;
import by.grodno.pvt.site.webappsample.service.ProductService;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import by.grodno.pvt.site.webappsample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductSellingController {

    static public final Integer SIZE = 5;

    @Autowired
    private ProductService productService;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductDTOToDomainConverter productDTOToDomainConverter;

    // Добавление в карзину продуктов
    @GetMapping("/products/buy/{id}")
    public String editProductForm(@PathVariable Integer id, HttpSession session) {
        List<ProductDTO> attribute = getSoldProducts(session);

        if (attribute == null) {
            session.setAttribute("soldProducts", new ArrayList<Product>());
        }

        getSoldProducts(session).add(conversionService.convert(productService.getProduct(id), ProductDTO.class));

        return "redirect:/productslist";
    }

    //уточнить как этоработает
    private List<ProductDTO> getSoldProducts(HttpSession session) {
        return (List<ProductDTO>) session.getAttribute("soldProducts");
    }

    // список продуктов в карзине
    @GetMapping("/sold")
    public String sold(Model model, HttpSession session) {

        List<ProductDTO> soldProducts = getSoldProducts(session);

//        BigDecimal totalPrice = null;
//        for (ProductDTO productDTO : soldProducts) {
//            BigDecimal x = productDTO.getPrice();
//            totalPrice.add(x);
//
//        }
 //       System.out.println(totalPrice);
       // model.addAttribute

        if (soldProducts == null) {
            session.setAttribute("soldProducts", new ArrayList<ProductDTO>());
        }

        model.addAttribute("products", getSoldProducts(session));

        return "soldProductsList";
    }

    @GetMapping("/sold/apply{user}")
    public String soldApply(@PathVariable User user, Model model, HttpSession session) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        System.out.println(username);
        List<ProductDTO> soldProducts = getSoldProducts(session);

        Optional<User> optionalUser = userService.findByEmail(username);
        User user1 = optionalUser.isPresent() ? optionalUser.get() : new User();

        List<Product> products = new ArrayList<>();

       // BigDecimal g; g.add(new BigDecimal(12));

        //////////////////////////////////////////////////////
        for (ProductDTO productDTO : soldProducts) {
            Product product = productService.getProduct(productDTO.getId());//получаю id по id иду в базу
            if (product.getQuantity() > 0) {
                product.setQuantity(product.getQuantity() - 1); // проверяю если остаток не 0 // -1 продук
                products.add(product);
                productService.saveProduct(product); //сохраняю продукт
            }
        }
        userService.addProductToUser(products,user1);
        //userService.addProductToUser(products); // сделать метод добавляющий продукты в список пользователя
        session.setAttribute("soldProducts", new ArrayList<Product>());
        return "sold";
    }


// Удаление из карзины

    @GetMapping("/sold/{id}")
    public String soldDelete(@PathVariable Integer id, Model model, HttpSession session) {

        List<ProductDTO> attribute = getSoldProducts(session);
        //ProductDTO productDTO = new ProductDTO();

        for (ProductDTO customer : attribute) {
            if (customer.getId().equals(id)) {
                attribute.remove(customer);
                return "redirect:/sold";
            }
        }
        return "redirect:/sold";
    }

//    @GetMapping("/sold")
//    public String totalPrice ( @PathVariable Integer id,Model model, HttpSession session) {
//
//        List<ProductDTO> attribute = getSoldProducts(session);
//        //ProductDTO productDTO = new ProductDTO();
//        BigDecimal totalprice = null;
//
//        for (ProductDTO customer : attribute) {
//           totalprice.add(customer.getPrice());
//        }
//        return "totalprice";
//    }


}


