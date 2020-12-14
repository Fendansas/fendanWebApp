package by.grodno.pvt.site.webappsample.service.impl;

import by.grodno.pvt.site.webappsample.domain.Role;
import by.grodno.pvt.site.webappsample.domain.User;
import by.grodno.pvt.site.webappsample.repo.UserRepo;
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
import java.util.List;

@Service
@Transactional
public class JPAUserService implements UserService, InitializingBean {
    @Autowired
    private UserRepo repo;

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
    public List<User> findByExample(User userSample) {
        Example<User> exp = Example.of(userSample,
                ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        return repo.findAll(exp);
    }

    @Override
    public Page<User> getPage(Integer pageNum, Integer pageSize) {
        return repo.findAll(PageRequest.of(pageNum, pageSize, Sort.Direction.ASC, "firstName"));
    }

    @Override
    public List<User> findByFName(String fname) {
        return repo.findByFirstName(fname);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        User User = new User(null, "Vasia","Ivanovich", "fendansas1@mail.ru","fendansas", Role.SELLER, new BigDecimal(1500), null,null,null,null);
        User User2 = new User(null, "Sergey", "Danilchik", "fendansas@mail.ru", "qwertyu", Role.USER, new BigDecimal(1500), null,null,null,null);
        User User3 = new User(null, "Ivan", "Ivanov", "fendan@gmail.com", "zxcvbnm", Role.USER, new BigDecimal(1500), null,null,null,null);
        repo.save(User);
        repo.save(User2);
        repo.save(User3);
    }



    @Override
    public User getUser(Integer id) {
        return repo.getOne(id);
    }

    @Override
    public void saveUser(User user) {
        repo.save(user);

    }
}