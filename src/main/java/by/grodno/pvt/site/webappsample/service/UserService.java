package by.grodno.pvt.site.webappsample.service;

import java.util.List;

import org.springframework.data.domain.Page;

import by.grodno.pvt.site.webappsample.domain.User;




public interface UserService {
    List<User> getUsers();

    User getUser(Integer id);

    void addUser(List<User> user);

    void saveUser(User user);

    void deleteUser(Integer number);

    List<User> findByExample(User userSample);

    Page<User> getPage(Integer pageNum, Integer pageSize);

    List<User> findByFName(String fname);
}
