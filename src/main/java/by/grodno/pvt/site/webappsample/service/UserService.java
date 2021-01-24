package by.grodno.pvt.site.webappsample.service;

import java.util.List;
import java.util.Optional;

import by.grodno.pvt.site.webappsample.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;

import by.grodno.pvt.site.webappsample.domain.User;
import by.grodno.pvt.site.webappsample.dto.UserDTO;



public interface UserService {

	List<User> getUsers();

	User getUser(Integer id);

	void addUser(List<User> user);

	void saveUser(User user);

	void deleteUser(Integer number);

	Optional<User> findByEmail(String email);

	void activateUser(Integer id);

	Page<User> getUsersPage(Integer pageNum, Integer size, String fieldName, Direction direction);

	void addProductToUser (List<Product> products);

	/**
	 * Updates FirstName and LastName only.
	 * 
	 * @param userDTO
	 */
	void edit(UserDTO userDTO);
}
