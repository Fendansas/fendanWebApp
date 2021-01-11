package by.grodno.pvt.site.webappsample.converter;

import by.grodno.pvt.site.webappsample.domain.Credentials;
import by.grodno.pvt.site.webappsample.domain.Role;
import by.grodno.pvt.site.webappsample.domain.User;
import by.grodno.pvt.site.webappsample.dto.UserRegistrationDTO;
import by.grodno.pvt.site.webappsample.repo.UserCredentialsRepo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;

@Component
public class UserRegistrationDTOToUserConverter implements Converter<UserRegistrationDTO, User> {

    @Override
    public User convert(UserRegistrationDTO source) {
        User user = new User();
        user.setFirstName(source.getFirstName());
        user.setLastName(source.getLastName());
        user.setEmail(source.getEmail());
        user.setRole(Role.USER);
        Credentials credentials = new Credentials(null, new Date(), false, source.getPassword());

        user.setCredentials(Collections.singletonList(credentials));
        return user;

    }

}
