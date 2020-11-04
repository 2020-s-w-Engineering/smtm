package cse.swengineering.smtm;

import cse.swengineering.smtm.menus.Menu;
import cse.swengineering.smtm.menus.MenuRepository;
import cse.swengineering.smtm.menus.MenuService;
import cse.swengineering.smtm.users.User;
import cse.swengineering.smtm.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

@Component
public class AppRunner implements ApplicationRunner {

    UserRepository userRepository;
    MenuRepository menuRepository;

    @Autowired
    MenuService menuService;

    @Autowired
    ResourceLoader resourceLoader;

    public AppRunner(UserRepository userRepository, MenuRepository menuRepository) {
        this.userRepository = userRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Resource resource = resourceLoader.getResource("classpath:기숙사_수육국밥.jpg");
        BufferedImage bImage = ImageIO.read(resource.getFile());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos );
        byte [] data = bos.toByteArray();
        Menu menu = new Menu();
        menu.setKorName("기숙사_수육국밥");
        menu.setEngName("kukBAB");
        menu.setImg(data);
        menuRepository.save(menu);

//        menuService.init();
//        Menu menu1 = new Menu("된장찌개", "a");
//        Menu menu2 = new Menu("김치찌개", "b");
//        Menu menu3 = new Menu("초밥", "c");
//        menuRepository.save(menu1);
//        menuRepository.save(menu2);
//        menuRepository.save(menu3);
//        User sengbok = new User("sengbok", "123", true);
//        sengbok.getPreference().put(menu1, 1);
//        sengbok.getPreference().put(menu2, 2);
//        sengbok.getPreference().put(menu3, 3);
//        User yeonju = new User("yeonju", "456", true);
//        yeonju.getPreference().put(menu1, 3);
//        yeonju.getPreference().put(menu2, 2);
//        yeonju.getPreference().put(menu3, 1);
//        User yejin = new User("yejin", "789", false);
//        userRepository.save(sengbok);
//        userRepository.save(yeonju);
//        userRepository.save(yejin);

//        List<Diet> dietList = dietService.getDietList();
//        System.out.println(dietList.size() + "일치의 식단 정보를 저장하고 있습니다");
//        dietList.forEach(Diet::printKOR);
    }
}
