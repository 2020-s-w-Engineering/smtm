package cse.swengineering.smtm.menus;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/menus")
@RestController
public class MenuController {

    private final MenuService menuService;

    private final MenuRepository menuRepository;

    public MenuController(MenuService menuService, MenuRepository menuRepository) {
        this.menuService = menuService;
        this.menuRepository = menuRepository;
    }

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/{date}")
    public Diet getDiet(@PathVariable LocalDate date){
        Diet diet = menuService.getDiet(date);
        return diet;
    }

    // 모든날의 식단 정보를 가져올 필요 없을 것 같은데?
    // 캘린더에 표시할 때는 날짜별 선호도 평균만 있으면 되니까
    @GetMapping("/calendar")
    public List<Diet> getCalendar(){
        return menuService.getDiets();
    }

    @GetMapping("/images")
    public ResponseEntity<byte[]> getMenuImage(@RequestParam Menu id) {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(id.getImg());
    }

    @PostMapping("/images")
    public String uploadMenuImage(@RequestParam MultipartFile file) {
        String filename=file.getOriginalFilename();
        try{
            byte [] bytes=file.getBytes();
            BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(
                    new FileOutputStream("./src/main/resources/images"+"/"+filename));
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }catch(Exception e){System.out.println(e);}
        return "true";
    }

}
