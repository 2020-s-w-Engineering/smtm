package cse.swengineering.smtm.menus;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.experimental.results.ResultMatchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
                .andExpect(jsonPath("$.date").value("2020-11-04"))
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
        menu.getImg().add(data);
        menu.setId(22L);
        menuRepository.save(menu);

        mockMvc.perform(get("/menus/images")
                .param("id", "22"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void uploadMenuImage() throws Exception {
        String filename = "한우채끝스테이크";
        String filename2 = "김치";
        MockMultipartFile mockFile = new MockMultipartFile("file", filename, "image/jpeg", getClass().getResourceAsStream("/images/김치1.jpg"));
        MockMultipartFile mockFile2 = new MockMultipartFile("file", filename2, "image/jpeg", getClass().getResourceAsStream("/images/김치1.jpg"));

        // 기존에 없던 파일 추가하는 경우
        mockMvc.perform(multipart("/menus/images")
                .file(mockFile))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().string("true"));

        // 기존에 있던 파일 추가하는 경우
        mockMvc.perform(MockMvcRequestBuilders.multipart("/menus/images")
                .file(mockFile2))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().string("true"));

//        File myObj = new File("filename.txt");
//        if (myObj.delete()) {
//            System.out.println("Deleted the file: " + myObj.getName());
//        } else {
//            System.out.println("Failed to delete the file.");
//        }
    }

}