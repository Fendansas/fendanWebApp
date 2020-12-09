//package by.grodno.pvt.site.webappsample.service;
//
//import static org.junit.Assert.assertEquals;
//
//import java.util.Date;
//
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
//
//import com.google.common.collect.Lists;
//
//import by.grodno.pvt.site.webappsample.domain.OldUser;
//
//@ContextConfiguration(classes = SpringTest.ContextConfig.class)
//public class SpringTest extends AbstractJUnit4SpringContextTests {
//
//	@Configuration
//	@ComponentScan("by.grodno.pvt.site.webappsample.service")
//	public class ContextConfig {
//
//	}
//
//	@Autowired
//	ReportingService service;
//
//	@Autowired
//	UserRepository repo;
//
//	@Test
//	public void test() {
//		// GIVEN
//		repo.addUser(Lists.newArrayList(new OldUser(null, "fname", "lname", new Date(), true)));
//
//		// WHEN
//		Integer allUsersCount = service.getAllUsersCount();
//
//		// THEN
//		assertEquals(1, allUsersCount.intValue());
//	}
//
//}
