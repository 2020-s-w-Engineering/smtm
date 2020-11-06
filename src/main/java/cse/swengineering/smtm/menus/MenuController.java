package cse.swengineering.smtm.menus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import java.time.LocalDate;
import java.util.*;

@Controller
public class MenuController {

    private final MenuRepository menuRepository;

    public MenuController(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Autowired
    ObjectMapper objectMapper;

    // 쿠키 테스트
    @GetMapping("/write/cookie")
    public ResponseEntity writeCookie() throws JsonProcessingException {
        List<Diet> dietList = new ArrayList<>();
        Diet diet1 = new Diet();
        diet1.setDate(LocalDate.now());
        diet1.setAvgOfPreference(4.2f);
        Main main = new Main();
        main.setType("A");
        main.setCalories("958kcal");
        HashSet<Menu> mainA = new HashSet<>();
        mainA.add(new Menu("김치", "kimchi"));
        main.setMenusA(mainA);
        HashMap<String, Main> whatTheFuck = new HashMap<>();
        diet1.setBreakfastMains(whatTheFuck);
        diet1.setLunchMains(whatTheFuck);
        diet1.setDinnerMains(whatTheFuck);
        Diet diet2 = new Diet();
        diet2.setDate(LocalDate.parse("2020-10-31"));
        diet2.setAvgOfPreference(4.42f);
        Diet diet3 = new Diet();
        diet3.setDate(LocalDate.parse("2020-07-01"));
        diet3.setAvgOfPreference(5.0f);
        dietList.add(diet1);
        dietList.add(diet2);
        dietList.add(diet3);
        String diets = objectMapper.writeValueAsString(dietList);
        diets = diets.replaceAll("\"", "+");
        diets = diets.replaceAll(",", "_");
        System.out.println(diets);
        ResponseCookie cookie = ResponseCookie.from("dietList", diets)
                                                .path("/")
                                                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }

    // 쿠키 테스트
    @ResponseBody
    @GetMapping("/read/cookie")
    public String readCookie(@CookieValue(value = "dietList", defaultValue = "unknown") String dietList) {
        dietList = dietList.replaceAll("\\+", "\"");
        dietList = dietList.replaceAll("_", ",");
        return dietList;
    }

    @GetMapping("/menus/{date}")
    public Diet getDiet(@PathVariable LocalDate date){
        return null;
    }

    @GetMapping("/menus")
    public List<Diet> getDiets(){
        return null;
    }

    @GetMapping("/menus/images/{id}")
    public ResponseEntity<byte[]> getMenuImage(@PathVariable Long id) throws IllegalAccessException {
        Optional<Menu> byId = menuRepository.findById(id);
        Menu menu = null;
        if(byId.isPresent()) {
             menu = byId.get();
        }
        else {
            throw new IllegalAccessException();
        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(menu.getImg());
    }

}
