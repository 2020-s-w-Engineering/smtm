package cse.swengineering.smtm.users;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void loginSuccess() throws Exception {
        User user = new User("donghun", "1031");
        userRepository.save(user);

        mockMvc.perform(post("/users/login")
                        .param("userId", user.getUserId())
                        .param("password", user.getPassword()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string("true"));
    }

    @Test
    public void loginFailure() throws Exception {
        mockMvc.perform(post("/users/login")
                .param("userId", "wrongId")
                .param("password", "wrongPassword"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string("false"));
    }

    @Test
    public void registerSuccess() throws Exception {
        User user = new User("donghun", "1031");

        mockMvc.perform(post("/users/register")
        .param("userId", user.getUserId())
        .param("password", user.getPassword())
        .param("korean", Boolean.toString(user.isKorean())))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string("true"));
    }

    public void registerFailureByDuplication() throws Exception {
        User user = new User("donghun", "1031");
        userRepository.save(user);
        User duplicatedUser = new User("donghun", "2222");

        mockMvc.perform(post("/users/register")
                .param("userId", duplicatedUser.getUserId())
                .param("password", duplicatedUser.getPassword())
                .param("korean", Boolean.toString(duplicatedUser.isKorean())))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string("false"));
    }

    @Test
    public void update() throws Exception {
        User user = new User("donghun", "1031", true);
        User savedUser = userRepository.save(user);
        assertThat(user).isSameAs(savedUser);
        User change = new User("donghun", "2222", false);

        mockMvc.perform(post("/users/update")
                .param("id", Long.toString(savedUser.getId()))
                .param("userId", savedUser.getUserId())
                .param("password", change.getPassword())
                .param("korean", Boolean.toString(change.isKorean())))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string("true"));

        Optional<User> byId = userRepository.findById(savedUser.getId());
        savedUser = byId.get();
        assertThat(savedUser).isEqualTo(change);
    }


}