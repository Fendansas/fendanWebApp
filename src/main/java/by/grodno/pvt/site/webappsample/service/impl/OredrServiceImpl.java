package by.grodno.pvt.site.webappsample.service.impl;//package by.grodno.pvt.site.webappsample.service.impl;
//
//import by.grodno.pvt.site.webappsample.domain.Order;
//import by.grodno.pvt.site.webappsample.domain.Product;
//import by.grodno.pvt.site.webappsample.domain.User;
//import by.grodno.pvt.site.webappsample.repo.OrderRepo;
//import by.grodno.pvt.site.webappsample.repo.ProductRepo;
//import by.grodno.pvt.site.webappsample.repo.UserCredentialsRepo;
//import by.grodno.pvt.site.webappsample.repo.UserRepo;
//import by.grodno.pvt.site.webappsample.service.EmailService;
//import by.grodno.pvt.site.webappsample.service.OrderService;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//
//@Service
//@Transactional
//public class OredrServiceImpl implements OrderService, InitializingBean {
//
//    @Autowired
//    private OrderRepo repo;
//
//
//    @Override
//    public List<Order> getOrders() {
//        return repo.findAll();
//    }
//
//
//    @Override
//    public Order getOrder(Integer id) {
//        return repo.getOne(id);
//    }
//
//
//
//    @Override
//    public void addOrder(List<Order> orders) {
//        repo.saveAll(orders);
//
//    }
//
//
//
//    @Override
//    public void saveOrder(Order order) {
//        repo.save(order);
//
//    }
//
//
//
//
//    @Override
//    public Page<Order> getOrderPage(Integer pageNum, Integer size, String fieldName, Sort.Direction direction) {
//        Pageable pagable;
//        if (fieldName != null) {
//            if (direction == null)
//                direction = Sort.Direction.ASC;
//            pagable = PageRequest.of(pageNum, size, direction, fieldName);
//        } else {
//            pagable = PageRequest.of(pageNum, size);
//        }
//
//        return repo.findAll(pagable);
//
//
//    }
//
//
//
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//
//    }
//}
