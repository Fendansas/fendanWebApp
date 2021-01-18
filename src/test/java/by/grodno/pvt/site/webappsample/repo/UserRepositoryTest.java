package by.grodno.pvt.site.webappsample.repo;//package by.grodno.pvt.site.webappsample.repo;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.io.IOException;
//
//import javax.sql.DataSource;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.opentable.db.postgres.embedded.EmbeddedPostgres;
//
//import by.grodno.pvt.site.webappsample.domain.User;
//import by.grodno.pvt.site.webappsample.repo.UserRepo;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//@ContextConfiguration(classes = { UserRepositoryTest.EmbeddedPostgresContextConfiguration.class })
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class UserRepositoryTest {
//
//	private static final String EMAIL = "max@max.max";
//
//	@Configuration
//	@EntityScan(basePackageClasses = User.class)
//	@EnableJpaRepositories(basePackageClasses = UserRepo.class)
//	public static class EmbeddedPostgresContextConfiguration {
//
//		@Bean
//		@Primary
//		public DataSource embeddedPG() throws IOException {
//			return EmbeddedPostgres.start().getPostgresDatabase();
//		}
//
//	}
//
//	@Autowired
//	private UserRepo repo;
//
//	@Test
//	public void testRepoPresent() {
//		assertNotNull(repo);
//	}
//
//	@Before
//	public void setUpDB() {
//		User user = new User();
//
//		user.setEmail(EMAIL);
//		user.setFirstName("fName");
//		user.setLastName("lName");
//
//		repo.save(user);
//	}
//
//	@Test
//	public void testCreate_readByUserName() {
//		// WHEN
//		User findByEmail = repo.findByEmail(EMAIL);
//
//		// THEN
//		assertNotNull(findByEmail);
//		assertEquals("fName", findByEmail.getFirstName());
//	}
//
//}
