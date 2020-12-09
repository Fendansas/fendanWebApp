package by.grodno.pvt.site.webappsample.converter;


import by.grodno.pvt.site.webappsample.domain.User;
import by.grodno.pvt.site.webappsample.dto.UserDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;



@Component
public class UserDomainToDTOConverter implements Converter <User, UserDTO> {
    @Override
    public UserDTO convert(User source){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(source.getId());
        userDTO.setFirstName(source.getFirstName());
        userDTO.setLastName(source.getLastName());
        userDTO.setLogin(source.getLogin());
        userDTO.setRole(source.getRole());
        userDTO.setManey(source.getManey());
        return  userDTO;

    }
}

//    private String login;
//    private Role role;
//    private Integer maney;