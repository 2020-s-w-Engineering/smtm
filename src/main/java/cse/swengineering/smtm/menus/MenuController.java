package cse.swengineering.smtm.menus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import java.time.LocalDate;
import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/menus")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
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

    @GetMapping("/menus/images/{id}")
    public ResponseEntity<byte[]> getMenuImage(@PathVariable Long id) throws IllegalAccessException {
//        Optional<Menu> byId = menuRepository.findById(id);
//        Menu menu = null;
//        if(byId.isPresent()) {
//             menu = byId.get();
//        }
//        else {
//            throw new IllegalAccessException();
//        }
//        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(menu.getImg());
        return null;
    }

}
