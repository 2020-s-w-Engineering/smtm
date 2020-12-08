package cse.swengineering.smtm.users;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cse.swengineering.smtm.menus.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@SessionAttributes("user")
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
    public ResponseEntity<String> processLogin(User user,
                                               @CookieValue(value = "preference", required = false) String cookieExist,
                                               HttpSession session) throws JsonProcessingException {
        user = userService.userAuth(user);
        if(user != null){
            ResponseEntity.BodyBuilder builder = ResponseEntity.ok();
            if(cookieExist == null) {
                user = userService.calcPreference(user);// 쿠키에 선호도 정보 저장
                String cookieValue = jsonToCookie(objectMapper.writeValueAsString(user.getMonthAvgPreference()));
                ResponseCookie cookie = ResponseCookie.from("preference", cookieValue).path("/").build();
                builder = builder.header(HttpHeaders.SET_COOKIE, cookie.toString());
            }
            session.setAttribute("user", user);
            return builder.body(String.valueOf(user.isKorean()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/logout")
    public void processLogout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
    }

    // 회원가입
    @PostMapping("/register")
    public String processRegister(User user, SessionStatus sessionStatus) {
        if(userService.updateUserInfo(user)){
            sessionStatus.setComplete();
            return "true";
        }
        return "false";
    }

    // 회원정보변경
    @PostMapping("/update")
    public String processUpdateInfo(User user) {
        return userService.updateUserInfo(user) ? "true" : "false";
    }

    // 하루 식단 평균 선호도 한달 치
    @GetMapping("/preference")
    public String getPreference(@CookieValue(value = "preference", required = false) String cookieValue){
        return cookieValue == null ? "false" : cookieToJson(cookieValue);
    }
//    @GetMapping("/preference")
//    public Map<LocalDate, Float> getPreference(User user){
//        return user.getMonthAvgPreference();
//    }

    // 개별 메뉴에 대한 사용자의 선호도 정보
    @GetMapping("/preference/{id}")
    public String getPreference(User user,
                                @PathVariable("id") Menu menu){
        if(user.getPreferenceMap().containsKey(menu.getId()))
            return user.getPreferenceMap().get(menu.getId()).toString();
        return "0";
    }
    
    // 선호도 기입
    @PostMapping("/preference")
    public ResponseEntity<String> setPreference(User user,
                                                @RequestParam("id") Menu menu,
                                                @RequestParam int preference,
                                                HttpSession session) throws JsonProcessingException {
        userService.setPreference(user, menu, preference);
        ResponseEntity.BodyBuilder builder = ResponseEntity.ok();
        user = userService.calcPreference(user); // 쿠키에 선호도 정보 저장
        session.setAttribute("user", user);
        String cookieValue = jsonToCookie(objectMapper.writeValueAsString(user.getMonthAvgPreference()));
        ResponseCookie cookie = ResponseCookie.from("preference", cookieValue).path("/").build();
        builder = builder.header(HttpHeaders.SET_COOKIE, cookie.toString());
        return builder.body("true");
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