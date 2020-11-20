package cse.swengineering.smtm.menus;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Autowired
    ResourceLoader resourceLoader;

    @Test
    public void getDiet() throws Exception {
        // 어떤 것도 테스트하고 있지 않음
        mockMvc.perform(MockMvcRequestBuilders.get("/menus/2020-11-04"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getMenuImage() throws Exception {
        Resource resource = resourceLoader.getResource("classpath:/images/수육국밥.jpg");
        BufferedImage bImage = ImageIO.read(resource.getFile());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos );
        byte [] data = bos.toByteArray();
        Menu menu = new Menu();
        menu.setKorName("수육국밥");
        menu.setEngName("kukBAB");
        menu.setImg(data);
        menu.setId(22L);
        menuRepository.save(menu);

        mockMvc.perform(get("/menus/images")
                .param("id", "22"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void uploadMenuImage() throws Exception {
        String filename = "donghun.txt";
        MockMultipartFile mockFile = new MockMultipartFile("file", filename, "text/plain", "hello world".getBytes());

        mockMvc.perform(multipart("/menus/images")
                .file(mockFile))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string("true"));

        File file = new File("./src/main/resources/images/" + filename);
        assertThat(file.exists()).isTrue();
    }

}