package by.grodno.pvt.site.webappsample.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import by.grodno.pvt.site.webappsample.domain.User;
import by.grodno.pvt.site.webappsample.service.UserService;

@Component("editUserVouter")
public class EditUserVouter {

	@Autowired
	private UserService service;

	public boolean checkUserId(Authentication authentication, Integer id) {
		User user = service.getUser(id);
		
		String username = ((org.springframework.security.core.userdetails.User)authentication.getPrincipal()).getUsername();
		
		return username.equals(user.getEmail());
	}

}
