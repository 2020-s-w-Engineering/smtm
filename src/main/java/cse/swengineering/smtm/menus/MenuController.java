package cse.swengineering.smtm.menus;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/menus")
@RestController
public class MenuController {

    private final MenuService menuService;
    private final ResourceLoader resourceLoader;

    public MenuController(MenuService menuService, ResourceLoader resourceLoader) {
        this.menuService = menuService;
        this.resourceLoader = resourceLoader;
    }

//    @GetMapping("/test")
//    public byte[] test(){
//        Set<Menu> menus = menuService.getDietList().get(0).getAllMenus();
//        for (Menu menu : menus) {
//            if(menu.getKorName().equals("수육국밥"))
//                return menu.getImg().get(0);
//        }
//        return null;
//    }

    public List<byte[]> test(){
        Set<Menu> menus = menuService.getDietList().get(0).getAllMenus();
        for (Menu menu : menus) {
            if(menu.getKorName().equals("수육국밥"))
                return menu.getImg();
        }
        return null;
    }

    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getMenuImage(@RequestParam Menu id) {
        Set<Menu> menus = menuService.getDietList().get(0).getAllMenus();
        for (Menu menu : menus) {
            if(menu.getId().equals(id.getId()))
                return menu.getImg().get(0);
        }
        return null;
    }

    @GetMapping("/{date}")
    public ResponseEntity<Diet> getDiet(@PathVariable LocalDate date) {
        Diet diet = menuService.getDiet(date);
        // DietService에서 dietList 가지고 있으므로 사실상 캐싱 필요없음
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(1L, TimeUnit.DAYS)).body(diet);
    }

    @GetMapping("/images")
    public List<byte[]> getMenuImages(@RequestParam Menu id) {
        Set<Menu> menus = menuService.getDietList().get(0).getAllMenus();
        for (Menu menu : menus) {
            if(menu.getId().equals(id.getId()))
                return menu.getImg();
        }
        return null;
    }

    @PostMapping("/images")
    public String uploadMenuImage(@RequestParam MultipartFile file) throws IOException {
        String filename=file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf("."));
        Resource resource = resourceLoader.getResource("classpath:/images");
        File imageDir = resource.getFile();
        File[] files = imageDir.listFiles();
        int count = 0;
        for(File f : files) {
            if(f.getName().contains(filename))
                count++;
        }
        filename = filename + (count+1);

        byte [] bytes=file.getBytes();
        BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(
                new FileOutputStream("./target/classes/images"+"/" + filename + ".jpg"));
        bufferedOutputStream.write(bytes);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        bufferedOutputStream=new BufferedOutputStream(
                new FileOutputStream("./src/main/resources/images"+"/" + filename + ".jpg"));
        bufferedOutputStream.write(bytes);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();

        menuService.addImage(filename);
        return "true";
    }

}
