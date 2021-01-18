package by.grodno.pvt.site.webappsample.scheduling;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import by.grodno.pvt.site.webappsample.domain.User;
import by.grodno.pvt.site.webappsample.domain.UserCredentials;
import by.grodno.pvt.site.webappsample.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Component
public class UserExpirationHandler {

	@Autowired
	private UserService service;

	@Scheduled(fixedRate = 10 * 1000)
	public void invalidateUsers() {

		List<User> users = service.getUsers();

		users.stream().filter(u -> {

			UserCredentials userCredentials = u.getCredentials().stream().filter(UserCredentials::getActive)
					.sorted((cr1, cr2) -> cr1.getCreationDate().compareTo(cr2.getCreationDate())).findFirst().get();
			if (userCredentials.getCreationDate()
					.getTime() < new Date(new Date().getTime() - 3 * 30 * 24 * 60 * 60 * 1000).getTime()) {
				return true;
			}
			return false;
		}).forEach(u -> {
			log.info("Has expired password: " + u.toString());
		});

	}

}
