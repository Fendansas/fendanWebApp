package by.grodno.pvt.site.webappsample.service.impl;

import java.util.*;

import by.grodno.pvt.site.webappsample.domain.Product;
import by.grodno.pvt.site.webappsample.repo.ProductRepo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.grodno.pvt.site.webappsample.domain.User;
import by.grodno.pvt.site.webappsample.domain.UserCredentials;
import by.grodno.pvt.site.webappsample.domain.UserRole;
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
        repo.save(getUser("max@max.max", "Maxim"));
        repo.save(getUser("max1@max.max", "Naxim"));
        repo.save(getUser("max2@max.max", "Baxim"));
        repo.save(getUser("max3@max.max", "Eaxim"));
        repo.save(getUser("max4@max.max", "IMaxim"));
        repo.save(getUser("max5@max.max", "Maxim"));
        repo.save(getUser("ma6@max.max", "Saxim"));
        repo.save(getUser("max7@max.max", "Oaxim"));
        repo.save(getUser("max8@max.max", "Maxim"));
        repo.save(getUser("max9@max.max", "Waxim"));
        repo.save(getUser("max0@max.max", "Maxim"));
        repo.save(getUser("max12@max.max", "Aaxim"));
        repo.save(getUser("max13@max.max", "Maxim"));
        repo.save(getUser("max14@max.max", "Vaxim"));
    }

    private User getUser(String email, String firstName) {
        User oldUser = new User(null, firstName, "Naumovich", email, null, UserRole.ADMIN, null, null);
        UserCredentials userCredentials = new UserCredentials(null, new Date(), true, "max");
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
    public void addProductToUser(Product product) {
        List<Product> products = new ArrayList<>();
        products.add(product);

//		products.add(new Product());
//
//	repo.save();

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
