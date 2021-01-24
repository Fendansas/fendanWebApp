package by.grodno.pvt.site.webappsample;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;
import by.grodno.pvt.site.webappsample.controller.UsersController;

@SpringBootTest
@AutoConfigureMockMvc
public class SpringBootTestApp {

    @Autowired
    private UsersController controller;

    @Test // JUnit 5!!!
    public void contextLoads() {
        assertNotNull(controller);
    }

    @Autowired
    private MockMvc mvc;

    @Test
    public void testMockMVC() throws Exception {

        mvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Sign In")));

    }


}