package cse.swengineering.smtm.menus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/menus")
@RestController
public class MenuController {

    private final MenuService menuService;
    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;

    public MenuController(MenuService menuService, ResourceLoader resourceLoader, ObjectMapper objectMapper) {
        this.menuService = menuService;
        this.resourceLoader = resourceLoader;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/test")
    public byte[] test(){
        Set<Menu> menus = menuService.getDietList().get(0).getAllMenus();
        for (Menu menu : menus) {
            if(menu.getKorName().equals("수육국밥"))
                return menu.getImg().get(0);
        }
        return null;
    }

    @GetMapping("/{date}")
    public ResponseEntity<Diet> getDiet(@PathVariable LocalDate date,
                                        @CookieValue(value = "preference", required = false) String cookieExist) throws JsonProcessingException {
        Diet diet = menuService.getDiet(date);
        if(cookieExist != null) {
            String json  = cookieToJson(cookieExist);
            Map<String, Double> map = objectMapper.readValue(json, Map.class);
        }
        // DietService에서 dietList 가지고 있으므로 사실상 캐싱 필요없음
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(1L, TimeUnit.DAYS)).body(diet);
    }

    @GetMapping("/images")
    public List<byte[]> getMenuImage(@RequestParam Menu id) {
        Set<Menu> menus = menuService.getDietList().get(0).getAllMenus();
        for (Menu menu : menus) {
            if(menu.getId().equals(id.getId()))
                return menu.getImg();
        }
        return null;
    }

    @PostMapping("/images")
    public String uploadMenuImage(@RequestParam MultipartFile file) throws IOException {
        String filename=file.getOriginalFilename();
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
                new FileOutputStream("./src/main/resources/images"+"/" + filename + ".jpg"));
        bufferedOutputStream.write(bytes);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        return "true";
    }

    /*
    private methods
     */
    private String cookieToJson(String cookie) {
        cookie = cookie.replaceAll("\\+", "\"");
        return cookie.replaceAll("_", ",");
    }

}
