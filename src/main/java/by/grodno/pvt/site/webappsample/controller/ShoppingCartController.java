package by.grodno.pvt.site.webappsample.controller;//package by.grodno.pvt.site.webappsample.controller;
//
//import by.grodno.pvt.site.webappsample.exception.NotEnoughProductsInStockException;
//import by.grodno.pvt.site.webappsample.service.ProductService;
//import by.grodno.pvt.site.webappsample.service.ShoppingCartService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//public class ShoppingCartController {
//
//
//    private final ShoppingCartService shoppingCartService;
//
//    private final ProductService productService;
//
//    @Autowired
//    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService) {
//        this.shoppingCartService = shoppingCartService;
//        this.productService = productService;
//    }
//
//    @GetMapping("/shoppingCart")
//    public ModelAndView shoppingCart() {
//        ModelAndView modelAndView = new ModelAndView("/shoppingCart");
//        modelAndView.addObject("products", shoppingCartService.getProductsInCart());
//        modelAndView.addObject("total", shoppingCartService.getTotal().toString());
//        return modelAndView;
//    }
//
//    @GetMapping("/shoppingCart/addProduct/{productId}")
//    public ModelAndView addProductToCart(@PathVariable("productId") Integer productId) {
//        productService.findById(productId).ifPresent(shoppingCartService::addProduct);
//        return shoppingCart();
//    }
//
//    @GetMapping("/shoppingCart/removeProduct/{productId}")
//    public ModelAndView removeProductFromCart(@PathVariable("productId") Integer productId) {
//        productService.findById(productId).ifPresent(shoppingCartService::removeProduct);
//        return shoppingCart();
//    }
//
////    @GetMapping("/shoppingCart/checkout")
////    public ModelAndView checkout() {
////        try {
////            shoppingCartService.checkout();
////        } catch (NotEnoughProductsInStockException e) {
////            return shoppingCart().addObject("outOfStockMessage", e.getMessage());
////        }
////        return shoppingCart();
////    }

//}


