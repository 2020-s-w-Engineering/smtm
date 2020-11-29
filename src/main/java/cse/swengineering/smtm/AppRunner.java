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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    private List<Menu> menuList = new ArrayList<>();

    private Menu getMenu(String korName) {
        for(Menu menu : menuList){
            if(menu.getKorName().equals(korName))
                return menu;
        }
        return null;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        menuService.init();

        Resource resource = resourceLoader.getResource("classpath:/images/수육국밥.jpg");
        BufferedImage bImage = ImageIO.read(resource.getFile());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos);
        byte [] data = bos.toByteArray();
        Optional<Menu> byId = menuRepository.findById(41L);
        Menu menu = byId.get();
        menu.getImg().add(data);
        Set<Menu> menus = menuService.getDietList().get(0).getBreakfastMains().get("A").getMenus();
        menus.add(menu);

        User donghun = new User("qwer", "1234", true);
        donghun.getPreferenceMap().put(1L, 1);
        donghun.getPreferenceMap().put(2L, 2);
        donghun.getPreferenceMap().put(3L, 3);
        donghun.getPreferenceMap().put(4L, 4);
        donghun.getPreferenceMap().put(5L, 5);
        userRepository.save(donghun);
    }
}
