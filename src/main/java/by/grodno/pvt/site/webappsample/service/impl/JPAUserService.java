package by.grodno.pvt.site.webappsample.service.impl;

import by.grodno.pvt.site.webappsample.domain.Credentials;
import by.grodno.pvt.site.webappsample.domain.Role;
import by.grodno.pvt.site.webappsample.domain.User;
import by.grodno.pvt.site.webappsample.exception.UserNotFoundException;
import by.grodno.pvt.site.webappsample.repo.UserCredentialsRepo;
import by.grodno.pvt.site.webappsample.repo.UserRepo;
import by.grodno.pvt.site.webappsample.service.EmailService;
import by.grodno.pvt.site.webappsample.service.UserService;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JPAUserService implements UserService, InitializingBean {

    @Autowired
    private UserRepo repo;
    @Autowired
    private UserCredentialsRepo credRepo;
    @Autowired
    private EmailService emailService;


    @Override
    public void addUser(List<User> users) {
        repo.saveAll(users);
    }

    @Override
    public List<User> getUsers() {
        return repo.findAll();
    }

    @Override
    public void deleteUser(Integer number) {
        repo.deleteById(number);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        User oldUser = new User(null,"Sergey", "Danilhcik", "fendan@mail.ru",null
                , Role.ADMIN,null,new BigDecimal(1000),null,null, new Date());
        Credentials userCredentials = new Credentials(null,  new Date(), true, "123");
        oldUser.setCredentials(Collections.singletonList(userCredentials));
        repo.save(oldUser);

        User oldUser1 = new User(null,"Misha", "Dmeh", "fendansas@mail.ru",null
                , Role.USER,null,new BigDecimal(10000),null,null, new Date());
        Credentials userCredentials1 = new Credentials(null,  new Date(), true, "123");
        oldUser.setCredentials(Collections.singletonList(userCredentials));
        repo.save(oldUser1);


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
            Credentials next = user.getCredentials().iterator().next();
            next.setActive(true);
            credRepo.save(next);
            return user;
        }).orElseThrow(() -> new UserNotFoundException());

    }






}