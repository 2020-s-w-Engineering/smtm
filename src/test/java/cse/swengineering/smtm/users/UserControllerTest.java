package cse.swengineering.smtm.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import cse.swengineering.smtm.menus.Menu;
import cse.swengineering.smtm.menus.MenuRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.http.Cookie;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void loginSuccess() throws Exception {
        User user = new User("donghun", "1031");
        userRepository.save(user);

        mockMvc.perform(post("/users/login")
                        .param("userId", user.getUserId())
                        .param("password", user.getPassword())
                        .param("korean", Boolean.toString(user.isKorean())))
                .andExpect(status().isOk())
                .andExpect(request().sessionAttribute("user", user))
                .andExpect(cookie().exists("preference"))
                .andExpect(content().string(String.valueOf(user.isKorean())))
                .andDo(print());
    }

    @Test
    public void loginFailure() throws Exception {
        mockMvc.perform(post("/users/login")
                .param("userId", "wrongId")
                .param("password", "wrongPassword"))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    public void registerSuccess() throws Exception {
        User user = new User("testId", "password");
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

    @Test
    public void getPreference() throws Exception {
        String cookieValue = "{+2020-11-06+:4.3_+2020-11-05+:4.42_+2020-11-04+:4.5}";
        Cookie cookie = new Cookie("preference", cookieValue);

        mockMvc.perform(get("/users/preference")
                .cookie(cookie))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.2020-11-06").value("4.3"))
                .andExpect(jsonPath("$.2020-11-05").value("4.42"))
                .andExpect(jsonPath("$.2020-11-04").value("4.5"));
    }

    @Test
    public void setPreference() throws Exception {
        User user = new User("donghun", "1031", true);
        userRepository.save(user);
        Optional<Menu> byId = menuRepository.findById(1L);
        Menu menu = byId.get();

        mockMvc.perform(post("/users/preference")
                .param("id", menu.getId().toString())
                .param("preference", "5")
                .sessionAttr("user", user))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string("true"));

        Optional<User> userById = userRepository.findById(user.getId());
        User savedUser = userById.get();
        assertThat(savedUser.getPreference().get(1L)).isEqualTo(5);
    }


}