package by.grodno.pvt.site.webappsample;

import by.grodno.pvt.site.webappsample.controller.UsersController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class MySmokeTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsersController controller;

    // тест на наличие контекста
    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }


    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print()) // пишет лог
                .andExpect(status().isOk()); //проверят статус, должно вернуть 200

    }

    //проверка на незарегистрированного пользователя
    @Test
    public void accessDeniedTest() throws Exception {
        this.mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    //Проверка входа
    @Test
    public void correctLoginTest() throws Exception {
        this.mockMvc.perform(formLogin().user("max@max.max").password("max"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
             //   .andExpect(redirectedUrl("/"));

        //.andExpect(redirectedUrl("/"));

    }
}
