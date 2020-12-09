package by.grodno.pvt.site.webappsample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReportingService {


	@Autowired
	private UserService jdbcUserService;

	public Integer getAllUsersCount() {
		return jdbcUserService.getUsers().size();
	}
}
