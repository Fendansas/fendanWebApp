package by.grodno.pvt.site.webappsample.converter;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import by.grodno.pvt.site.webappsample.domain.User;
import by.grodno.pvt.site.webappsample.dto.UserDTO;

@Component
public class UserDomainToDTOConverter implements Converter<User, UserDTO> {


    @Override
    public UserDTO convert(User user) {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());

        return userDTO;
    }
}

//    private String login;
//    private Role role;
//    private Integer money;