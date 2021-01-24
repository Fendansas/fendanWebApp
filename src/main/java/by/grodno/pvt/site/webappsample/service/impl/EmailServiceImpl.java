package by.grodno.pvt.site.webappsample.service.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.grodno.pvt.site.webappsample.config.EmailProperties;
import by.grodno.pvt.site.webappsample.domain.User;
import by.grodno.pvt.site.webappsample.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private EmailProperties emailConfig;

	@Override
	public void sendUserActivationEmail(User user) {

		String to = user.getEmail();
		String from = "no-reply@student.com";

		// Get system properties
		final String username = emailConfig.getUsername();
		final String password = emailConfig.getPassword();

		Properties prop = new Properties();
		prop.put("mail.smtp.host", emailConfig.getHost());
		prop.put("mail.smtp.port", emailConfig.getPort());
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			message.setSubject("Activation link for Store.com");

			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(getMessage(user), "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);

			message.setContent(multipart);

			Transport.send(message);
		} catch (MessagingException mex) {
			throw new RuntimeException(mex);
		}

	}

	private String getMessage(User user) {
		return new StringBuilder("Hello ").append(user.getFirstName()).append("! ")
				.append("To activate your account press next link: ").append(emailConfig.getDomainHost())
				.append("/activate/").append(user.getId()).toString();
	}

}
