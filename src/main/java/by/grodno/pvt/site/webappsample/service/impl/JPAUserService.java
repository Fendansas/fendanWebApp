package by.grodno.pvt.site.webappsample.service.impl;

import java.util.*;

import by.grodno.pvt.site.webappsample.domain.*;
import by.grodno.pvt.site.webappsample.repo.ProductRepo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.grodno.pvt.site.webappsample.dto.UserDTO;
import by.grodno.pvt.site.webappsample.exception.UserNotFoundException;
import by.grodno.pvt.site.webappsample.repo.UserCredentialsRepo;
import by.grodno.pvt.site.webappsample.repo.UserRepo;
import by.grodno.pvt.site.webappsample.service.EmailService;
import by.grodno.pvt.site.webappsample.service.UserService;

@Service
@Transactional
public class JPAUserService implements UserService, InitializingBean {

    @Autowired
    private UserRepo repo;

    @Autowired
    private UserCredentialsRepo credRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    public ProductRepo productRepo;

    @Override
    public void addUser(List<User> users) {
        repo.saveAll(users);
    }

    @Override
    public List<User> getUsers() {
        return repo.findAll();
    }



    @Override
    @Transactional
    public void deleteUser(Integer number) {
        //Optional<User> user = repo.findById(number);
        repo.deleteById(number);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        repo.save(getUser("fendan@mail.ru", "sas"));
        repo.save(getUser("fendan1@mail.ru", "sas"));
        repo.save(getUser("fendan2@mail.ru", "sas"));
        repo.save(getUser("fendan3@mail.ru", "sas"));
        repo.save(getUser("fendan4@mail.ru", "sas"));
        repo.save(getUser("fendan5@mail.ru", "sas"));
        repo.save(getUser("fendan6@mail.ru", "sas"));
        repo.save(getUser("fendan7@mail.ru", "sas"));
        repo.save(getUser("fendan8@mail.ru", "sas"));
        repo.save(getUser("fendan9@mail.ru", "sas"));
        repo.save(getUser("fendan10@mail.ru", "sas"));
        repo.save(getUser("fendan11@mail.ru", "sas"));
        repo.save(getUser("fendan12@mail.ru", "sas"));
        repo.save(getUser("fendan13@mail.ru", "sas"));
    }

    private User getUser(String email, String firstName) {
        User oldUser = new User(null, firstName, "Dan", email, null, UserRole.ADMIN, null, new ArrayList<>());
        UserCredentials userCredentials = new UserCredentials(null, new Date(), true, "sas");
        oldUser.setCredentials(Collections.singletonList(userCredentials));
      return oldUser;
    }

    @Override
    public User getUser(Integer id) {
        return repo.getOne(id);
    }

    @Override
    public void saveUser(User user) {
        repo.save(user);
        emailService.sendUserActivationEmail(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(repo.findByEmail(email));
    }

    @Override
    public void activateUser(Integer id) {
        Optional<User> findById = repo.findById(id);

        findById.map(user -> {
            UserCredentials next = user.getCredentials().iterator().next();
            next.setActive(true);
            credRepo.save(next);
            return user;
        }).orElseThrow(() -> new UserNotFoundException());

    }

    @Override
    public Page<User> getUsersPage(Integer pageNum, Integer size, String fieldName, Sort.Direction direction) {
        Pageable pagable;
        if (fieldName != null) {
            if (direction == null)
                direction = Sort.Direction.ASC;
            pagable = PageRequest.of(pageNum, size, direction, fieldName);
        } else {
            pagable = PageRequest.of(pageNum, size);
        }

        return repo.findAll(pagable);
    }

    ///////////////////////////////////////////////////////////////////
    @Override
    public void addProductToUser(List<Product> products, User user) {
//       // List<Product> products = new ArrayList<>();
////        for (int i = 0; i<products.size(); i++) {
////            products.add(product);
////        }
//        //user.setProducts(products);
//        List<Product> oldList = user.getProducts();
//        for (int i = 0; i<products.size();i++) {
//            Product addProduct = products.get(i);
//            oldList.add(addProduct);
//        }
//        user.setProducts(oldList);
//        //Integer id = user.getId();
//        repo.save(user);   //не сохраняет из за одинаковых id
//
////		products.add(new Product());
////
////	repo.save();

    }

    @Override
    public void addOrderToUser(List<UserOrder> orders, User user) {

    }

    ////////////////////////////////////////////////////////////////////////
    @Override
    public void edit(UserDTO userDTO) {
        User findById = repo.findById(userDTO.getId()).orElseThrow(() -> new UserNotFoundException());
        findById.setFirstName(userDTO.getFirstName());
        findById.setLastName(userDTO.getLastName());
        repo.save(findById);
    }

}
