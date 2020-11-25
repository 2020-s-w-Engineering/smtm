package cse.swengineering.smtm.menus;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/menus")
@RestController
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/{date}")
    public ResponseEntity<Diet> getDiet(@PathVariable LocalDate date){
        Diet diet = menuService.getDiet(date);
        // DietService에서 dietList 가지고 있으므로 사실상 캐싱 필요없음
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(1L, TimeUnit.DAYS)).body(diet);
    }

    @GetMapping("/images")
    public ResponseEntity<byte[]> getMenuImage(@RequestParam Menu id) {
        if(id.getImg() != null)
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(id.getImg());
        else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
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
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return "true";
    }

}
