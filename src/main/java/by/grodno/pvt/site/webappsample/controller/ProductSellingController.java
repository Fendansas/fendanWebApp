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
import by.grodno.pvt.site.webappsample.service.*;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductSellingController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderItemService orderItemService;

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
    public String addToOrder(@PathVariable Integer id, @RequestParam Integer quantity, HttpSession session) {
        if (!productService.isProductInStock(id, quantity)) {
            throw new NotEnoughProductsInStockException();
        }

        Optional<User> userOptional = userService.findByEmail(securityService.getCurrentUserUsername());
        if(!userOptional.isPresent()) {
            throw new UserNotFoundException();
        }
        User user = userOptional.get();

        UserOrder order = orderService.getOrCreateOrder(user);
        OrderItem orderItem = orderItemService.createOrderItemWithProduct(id, quantity);  //нужно получить колчество с сайта

        orderService.addOrderItem(order, orderItem);

        List<OrderItemDTO> cart = getItemsFromSession(session);

        cart.add(conversionService.convert(orderItem, OrderItemDTO.class));

        session.setAttribute("orderItems", cart);

        return "redirect:/productslist";
    }

    private List<OrderItemDTO> getItemsFromSession(HttpSession session) {
        List<OrderItemDTO> list = (List<OrderItemDTO>) session.getAttribute("soldProducts");
        if(list == null) {
            return new ArrayList<>();
        } else {
            return list;
        }
    }

    // список продуктов в карзине
    @GetMapping("/sold")
    public String sold(Model model, HttpSession session) {

        List<OrderItemDTO> orderItems = getItemsFromSession(session);

        model.addAttribute("products", orderItems);

        return "soldProductsList";
    }

    @GetMapping("/sold/apply{user}")
    public String soldApply(@PathVariable User user, Model model, HttpSession session) {

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String username = auth.getName();
//        System.out.println(username);
//        List<ProductDTO> soldProducts = getSoldProducts(session);
//
//        Optional<User> optionalUser = userService.findByEmail(username);
//        User user1 = optionalUser.isPresent() ? optionalUser.get() : new User();
//
//        List<Product> products = new ArrayList<>();
//
//       // BigDecimal g; g.add(new BigDecimal(12));
//
//        for (ProductDTO productDTO : soldProducts) {
//            Product product = productService.getProduct(productDTO.getId());//получаю id по id иду в базу
//            if (product.getQuantity() > 0) {
//                product.setQuantity(product.getQuantity() - 1); // проверяю если остаток не 0 // -1 продук
//                products.add(product);
//                productService.saveProduct(product); //сохраняю продукт
//            }
//        }
//        userService.addProductToUser(products,user1);
//
//        session.setAttribute("soldProducts", new ArrayList<Product>());
//        return "sold";

        return null;
    }


// Удаление из карзины

    @GetMapping("/sold/{id}")
    public String soldDelete(@PathVariable Integer id, Model model, HttpSession session) {

        List<OrderItemDTO> attribute = getItemsFromSession(session);

        for (OrderItemDTO orderItemDTO : attribute) {
            if (orderItemDTO.getId().equals(id)) {
                attribute.remove(orderItemDTO);
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


