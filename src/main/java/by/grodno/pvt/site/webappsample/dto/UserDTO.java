package by.grodno.pvt.site.webappsample.dto;


import by.grodno.pvt.site.webappsample.domain.Role;
import lombok.Data;

@Data
public class UserDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String login;
    private Role role;
    private Integer maney;


}
