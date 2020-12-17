package by.grodno.pvt.site.webappsample.dto;


import by.grodno.pvt.site.webappsample.domain.Role;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String login;
    private Role role;
    private BigDecimal money;
    private String password;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
