package cse.swengineering.smtm.users;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andDo(print())
                .andExpect(status().isOk());
    }

//    @Test
//    public void getUser() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string("{\"id\":1,\"userId\":\"tjdqhr\",\"password\":\"123\",\"korean\":true}"));
//    }

    @Test
    public void addUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
            .param("userId", "hello123")
            .param("password", "1234")
            .param("isKorean", "true"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}