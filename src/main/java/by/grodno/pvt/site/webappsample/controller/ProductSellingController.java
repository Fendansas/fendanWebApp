package by.grodno.pvt.site.webappsample.controller;//package by.grodno.pvt.site.webappsample.controller;
//
//import by.grodno.pvt.site.webappsample.domain.Product;
//import by.grodno.pvt.site.webappsample.domain.User;
//import by.grodno.pvt.site.webappsample.dto.ProductDTO;
//import by.grodno.pvt.site.webappsample.service.ProductService;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.convert.ConversionService;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//@Controller
//public class ProductSellingController {
//
//    static public final Integer SIZE = 5;
//
//    @Autowired
//    private ProductService productService;
//
//    @Autowired
//    private ConversionService conversionService;
//
//    @GetMapping("/products/buy/{id}")
//    public String editProductForm(@PathVariable Integer id, HttpSession session) {
//        List<ProductDTO> attribute = getSoldProducts(session);
//
//        if (attribute == null) {
//            session.setAttribute("soldProducts", new ArrayList<Product>());
//        }
//
//        getSoldProducts(session).add(conversionService.convert(productService.getProduct(id), ProductDTO.class));
//
//        return "redirect:/productslist";
//    }
//
//    private List<ProductDTO> getSoldProducts(HttpSession session) {
//        return (List<ProductDTO>) session.getAttribute("soldProducts");
//    }
//
//    @GetMapping("/sold")
//    public String sold(Model model, HttpSession session) {
//
//        List<ProductDTO> attribute = getSoldProducts(session);
//
//        if (attribute == null) {
//            session.setAttribute("soldProducts", new ArrayList<ProductDTO>());
//        }
//
//        model.addAttribute("products", getSoldProducts(session));
//
//        return "soldProductsList"; /// Сделать вью
//    }
//
//    @GetMapping("/sold/apply")
//    public String soldApply(Model model, HttpSession session) {
//
//        List<ProductDTO> attribute = getSoldProducts(session);
//
//        //cartService.submitOrder(attribute);
//
//        session.setAttribute("soldProducts", new ArrayList<Product>());
//
//        return "sold";
//    }
//}


