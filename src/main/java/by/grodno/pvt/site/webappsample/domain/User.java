package by.grodno.pvt.site.webappsample.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_table")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;

	@Email
	@Column(unique = true,name = "email")
	private String email;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			joinColumns = @JoinColumn(name = "u1"),
			inverseJoinColumns = @JoinColumn(name = "u2"))
	private List<UserCredentials> credentials;

	private UserRole role;

	@OneToOne(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private UserPicture picture;

	@OneToMany( mappedBy = "user",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<UserOrder> orders = new ArrayList<>();

}
