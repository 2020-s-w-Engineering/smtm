package cse.swengineering.smtm.menus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.*;

@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private List<Diet> dietList = new ArrayList<>();
    private List<Menu> menuList = new ArrayList<>();

    @Autowired
    ResourceLoader resourceLoader;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<Diet> getDietList() {
        return dietList;
    }
    public void setDietList(List<Diet> dietList) {this.dietList = dietList;}

    public Diet getDiet(LocalDate date){
        for(Diet diet : dietList){
            if(diet.getDate().equals(date))
                return diet;
        }
        return null;
    }

    public List<Diet> getDiets() {
        return getDietList();
    }

    public void init() throws IOException {
        menuList = menuRepository.findAll();

        final String LOCAL_DATE_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";
//        File file = new File(SmtmApplication.class.getClass().getResource("/data2.txt").toURI());
        Resource resource = resourceLoader.getResource("classpath:/data2.txt");
        File file = resource.getFile();
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        List<String> splitByDate = new ArrayList<>();

        // 텍스트 파일에서 모든 데이터 읽고 날짜별로 구분하여 splitByDate에 저장
        line = bufferedReader.readLine();
        while (true) {
            if (line == null)
                break;
            else {
                String concat = line;
                line = bufferedReader.readLine();
                while (line != null && !line.matches(LOCAL_DATE_REGEX)) {
                    concat = concat + "," + line;
                    line = bufferedReader.readLine();
                }
                splitByDate.add(concat);
            }
        }

        for(String str : splitByDate){
            Diet diet = new Diet();
            diet.setDate(LocalDate.parse(str.substring(0, 10)));
            dietList.add(diet);
        }

        // splitByDate를 순회하며 하나하나를 Diet으로 변환
        List<Main> mains = new ArrayList<>();
        for (String str : splitByDate) {
            int index = 2;
            String[] split = str.split(",");
            boolean flag = false;
            for (int i = 0; i < 3; i++) {
                if(index >= split.length) {
                    break;
                }

                Main mainA = new Main();
                mainA.setCalories(split[index]);
                while (++index < split.length && !split[index].contains("메인")) { // index 검사와 동시에 증가
                    mainA.getMenus().add(getMenu(split[index]));
                }
                mains.add(mainA);

                if(index >= split.length) {
                    Main mainC = new Main();
                    ++index;
                    mains.add(mainC);
                    break;
                }

                Main mainC = new Main();
                if (split[index].equals("메인C")) {
                    mainC.setCalories(split[++index]);
                    while (++index < split.length && !split[index].contains("메인")) {
                        mainC.getMenus().add(getMenu(split[index]));
                    }
                    ++index;
                    mains.add(mainC);
                }
                else {
                    ++index;
                    mains.add(mainC);
                }

                if(index >= split.length) {
                    break;
                }
            }
        }

        int index = 0;
        for(Diet diet : dietList){
            Map<String, Main> main = new HashMap<>();
            main.put("A", mains.get(index++));
            main.put("C", mains.get(index++));
            diet.setBreakfastMains(main);
            main = new HashMap<>();
            main.put("A", mains.get(index++));
            main.put("C", mains.get(index++));
            diet.setLunchMains(main);
            main = new HashMap<>();
            main.put("A", mains.get(index++));
            main.put("C", mains.get(index++));
            diet.setDinnerMains(main);
        }

        setImage();
        // debug
//        for(Diet diet : dietList){
//            diet.print();
//        }

    }

    private void setImage() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:/images");
        File imageDir = resource.getFile();
        File[] files = imageDir.listFiles();
        boolean found = false;
        for(File file : files) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            BufferedImage bImage = ImageIO.read(file);
            ImageIO.write(bImage, "jpg", bos);
            byte [] data = bos.toByteArray();
            String menuName = file.getName().substring(0, file.getName().indexOf("."));
            for(Diet diet : dietList){
                Set<Menu> allMenus = diet.getAllMenus();
                for(Menu menu : allMenus){
                    if(menuName.contains(menu.getKorName())) {
                        menu.getImg().add(data);
                        found = true;
                        break;
                    }
                }
                if(found) break;
            }
            bos.reset();
        }
    }

    private Menu getMenu(String korName) {
        for(Menu menu : menuList){
            if(menu.getKorName().equals(korName))
                return menu;
        }
        return null;
    }
}
