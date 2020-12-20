package by.grodno.pvt.site.webappsample.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "email.server")
public class EmailProperties {

    String host;
    String port;
    String domainHost;
    String username;
    String password;

}
