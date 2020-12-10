package cse.swengineering.smtm.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import cse.swengineering.smtm.menus.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 로그인
    @PostMapping("/login")
    public String processLogin(User user) {
        user = userService.userAuth(user);
        if(user != null) {
            user = userService.calcPreference(user);// 쿠키에 선호도 정보 저장
            return String.valueOf(user.isKorean());
        }
        return null;
    }

    @GetMapping("/logout")
    public void processLogout() {}

    // 회원가입
    @PostMapping("/register")
    public String processRegister(User user) {
        if(userService.register(user)){
            return "true";
        }
        return "false";
    }

    // 회원정보변경
    @PostMapping("/update")
    public String processUpdateInfo(User user,
                                    @RequestParam("korean") boolean isKorean) {
        user.setKorean(isKorean);
        return userService.updateUserInfo(user) ? "true" : "false";
    }

    // 하루 식단 평균 선호도 한달 치
//    @GetMapping("/preference")
//    public String getPreference(@CookieValue(value = "preference", required = false) String cookieValue){
//        return cookieValue == null ? "false" : cookieToJson(cookieValue);
//    }
    @GetMapping("/preference")
    public Map<LocalDate, Float> getPreference(User user){
        user = userService.userAuth(user);
        return user.getMonthAvgPreference();
    }

    // 개별 메뉴에 대한 사용자의 선호도 정보
    @GetMapping("/preference/{id}")
    public String getPreference(User user,
                                @PathVariable("id") Menu menu){
        user = userService.userAuth(user);
        // 매개변수에서 그냥 User user로 받으면 DomainCalssConverter가 작동해서 새로운 User를 만들어버리나본데?
        if(user.getPreferenceMap().containsKey(menu.getId()))
            return user.getPreferenceMap().get(menu.getId()).toString();
        return "0";
    }
    
    // 선호도 기입
    @PostMapping("/preference")
    public String setPreference(User user,
                                @RequestParam("id") Menu menu,
                                @RequestParam int preference) {
        if(userService.setPreference(user, menu, preference)) {
            return "true";
        }
        else // 적절한 예외처리(여유 있으면)
            return "false";
    }

    /*
    private methods
     */
    private String cookieToJson(String cookie) {
        cookie = cookie.replaceAll("\\+", "\"");
        return cookie.replaceAll("_", ",");
    }

    private String jsonToCookie(String json) {
        json = json.replaceAll("\"", "+");
        return json.replaceAll(",", "_");
    }

}