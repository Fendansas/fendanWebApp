package by.grodno.pvt.site.webappsample.service.impl;

import by.grodno.pvt.site.webappsample.domain.Role;
import by.grodno.pvt.site.webappsample.domain.User;
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

}