package by.grodno.pvt.site.webappsample.controller;//package by.grodno.pvt.site.webappsample.controller;

import by.grodno.pvt.site.webappsample.converter.ProductDTOToDomainConverter;
import by.grodno.pvt.site.webappsample.domain.OrderItem;
import by.grodno.pvt.site.webappsample.domain.Product;
import by.grodno.pvt.site.webappsample.domain.User;
import by.grodno.pvt.site.webappsample.domain.UserOrder;
import by.grodno.pvt.site.webappsample.dto.OrderItemDTO;
import by.grodno.pvt.site.webappsample.dto.ProductDTO;
import by.grodno.pvt.site.webappsample.exception.NotEnoughProductsInStockException;
import by.grodno.pvt.site.webappsample.exception.UserNotFoundException;
import by.grodno.pvt.site.webappsample.service.OrderService;
import by.grodno.pvt.site.webappsample.service.ProductService;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import by.grodno.pvt.site.webappsample.service.SecurityService;
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

    @Autowired
    private ProductService productService;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ProductDTOToDomainConverter productDTOToDomainConverter;

    // Добавление в карзину продуктов
    @GetMapping("/products/order/{id}")
    public String addToOrder(@PathVariable Integer id, HttpSession session) {
        if (!productService.isProductInStock(id)) {
            throw new NotEnoughProductsInStockException();
        }

        Optional<User> userOptional = userService.findByEmail(securityService.getCurrentUserUsername());
        if(!userOptional.isPresent()) {
            throw new UserNotFoundException();
        }
        User user = userOptional.get();

        UserOrder order = orderService.getOrCreateOrder(user);

        order.setItems(OrderItem);

        //ордер айтем сервис который умеет создавать ордер айтемы из продуктов

        //добавить ордер айтем в ордер

        //

        List<ProductDTO> attribute = getSoldProducts(session);

        if (attribute == null) {
            session.setAttribute("soldProducts", new ArrayList<Product>());
        }
// Удостовериться что продукт есть на складе
        // Если у пользователя нет заказа - ордера - создать заказ
        // Добавить в заказ данный продукт сделав из него ордер айтем
        // Положить в хттп сессию ордер айтемы заказа пользователя
        getSoldProducts(session).add(conversionService.convert(productService.getProduct(id), ProductDTO.class));

        return "redirect:/productslist";
    }


    private List<ProductDTO> getSoldProducts(HttpSession session) {
        return (List<ProductDTO>) session.getAttribute("soldProducts");
    }

    // список продуктов в карзине
    @GetMapping("/sold")
    public String sold(Model model, HttpSession session) {

        List<ProductDTO> soldProducts = getSoldProducts(session);

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

        for (ProductDTO productDTO : soldProducts) {
            Product product = productService.getProduct(productDTO.getId());//получаю id по id иду в базу
            if (product.getQuantity() > 0) {
                product.setQuantity(product.getQuantity() - 1); // проверяю если остаток не 0 // -1 продук
                products.add(product);
                productService.saveProduct(product); //сохраняю продукт
            }
        }
        userService.addProductToUser(products,user1);

        session.setAttribute("soldProducts", new ArrayList<Product>());
        return "sold";
    }


// Удаление из карзины

    @GetMapping("/sold/{id}")
    public String soldDelete(@PathVariable Integer id, Model model, HttpSession session) {

        List<ProductDTO> attribute = getSoldProducts(session);


        for (ProductDTO productDTO : attribute) {
            if (productDTO .getId().equals(id)) {
                attribute.remove(productDTO );
                return "redirect:/sold";
            }
        }
        return "redirect:/sold";
    }


    // список продуктов в карзине
    @GetMapping("/userProducts")
    public String userProducts(Model model, HttpSession session) {

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String username = auth.getName();
//
//        Optional<User> optionalUser = userService.findByEmail(username);
//        User user = optionalUser.isPresent() ? optionalUser.get() : new User();
//
//        List<Product> products = user.getProducts();
//        model.addAttribute("products", products);
         return "userProducts";
    }





}


