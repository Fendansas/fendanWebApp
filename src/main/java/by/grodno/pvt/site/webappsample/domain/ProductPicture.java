package by.grodno.pvt.site.webappsample.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ProductPicture {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;

	private String fileName;
	private String fileLocation;

}
