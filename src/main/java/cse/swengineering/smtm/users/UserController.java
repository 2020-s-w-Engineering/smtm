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

import javax.servlet.http.HttpSession;
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
    public ResponseEntity<String> processLogin(User user,
                                               @CookieValue(value = "preference", required = false, defaultValue = "empty") String cookieExist,
                                               HttpSession session) throws JsonProcessingException {
        User savedUser = userService.userAuth(user);
        if(savedUser != null){
            ResponseEntity.BodyBuilder builder = ResponseEntity.ok();
            session.setAttribute("user", savedUser); // 세션에 유저 정보 설정
            if(cookieExist.equals("empty")) {
                Map<LocalDate, Float> preference = userService.calcPreference(savedUser); // 쿠키에 선호도 정보 저장
                String cookieValue = jsonToCookie(objectMapper.writeValueAsString(preference));
                ResponseCookie cookie = ResponseCookie.from("preference", cookieValue).path("/").build();
                builder = builder.header(HttpHeaders.SET_COOKIE, cookie.toString());
            }
            return builder.body(String.valueOf(savedUser.isKorean()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    // 회원가입
    @PostMapping("/register")
    public String processRegister(User user) {
        return userService.updateUserInfo(user) ? "true" : "false";
    }

    // 회원정보변경
    @PostMapping("/update")
    public String processUpdateInfo(User user, HttpSession session) {
        if(userService.updateUserInfo(user)){
            session.setAttribute("user", user);
            return "true";
        }
        return "false";
    }

    // 사용자의 선호도 정보
    @GetMapping("/preference")
    public String getPreference(@CookieValue(value = "preference", required = false, defaultValue = "empty") String cookieValue){
        return cookieValue.equals("empty") ? "false" : cookieToJson(cookieValue);
    }
    
    // 선호도 기입
    @PostMapping("/preference")
    public String setPreference(@RequestParam("id") Menu menu,
                                @RequestParam int preference,
                                HttpSession session){
        User user = (User) session.getAttribute("user");
        return userService.setPreference(user, menu, preference) ? "true" : "false";
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