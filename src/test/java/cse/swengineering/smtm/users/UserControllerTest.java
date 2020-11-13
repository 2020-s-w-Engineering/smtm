package cse.swengineering.smtm.users;

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

    @Autowired
    MenuRepository menuRepository;

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
    public void setPreference() throws Exception {
        User user = new User("donghun", "1031", true);
        userRepository.save(user);
        Optional<Menu> byId = menuRepository.findById("김치");
        Menu menu = byId.get();

        mockMvc.perform(post("/users/preference")
                .param("korName", menu.getKorName())
                .param("preference", "5")
                .sessionAttr("user", user))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string("true"));

        Optional<User> userById = userRepository.findById(user.getId());
        User savedUser = userById.get();
        assertThat(savedUser.getPreference().get("김치")).isEqualTo(5);
    }


}