package by.grodno.pvt.site.webappsample.dto;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserDTO {

	private Integer id;

	@Length(min = 3, max = 20)
	private String firstName;
	
	@Length(min = 3, max = 20)
	private String lastName;

}
