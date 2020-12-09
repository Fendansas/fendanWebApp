package by.grodno.pvt.site.webappsample.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_table")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String firstName;

	private String lastName;

	@Email
	@Column(nullable = false, unique = true)
	private String email;

	@Length(min = 6, max = 20)
	@Column(nullable = false, unique = true, updatable = false)
	private String login;

	@Column(nullable = false)
	private Role role;

	private Integer maney;

	private String avatarFileName;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "ownerUser")
    private List<Credentials> credentials;

	@OneToOne
	@JoinColumn(name = "users_id")
	private Order order;



}
