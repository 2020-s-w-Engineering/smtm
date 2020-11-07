package cse.swengineering.smtm.menus;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MenuControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MenuService menuService;

    @Autowired
    MenuRepository menuRepository;

    @Test
    public void getDiet() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/menus/2020-11-04"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}