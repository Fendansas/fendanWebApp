package by.grodno.pvt.site.webappsample.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Credentials {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private Boolean active;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User ownerUser;



}
