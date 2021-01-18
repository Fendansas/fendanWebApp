package by.grodno.pvt.site.webappsample.service;

import by.grodno.pvt.site.webappsample.domain.User;

public interface EmailService {

	void sendUserActivationEmail(User user);

}
