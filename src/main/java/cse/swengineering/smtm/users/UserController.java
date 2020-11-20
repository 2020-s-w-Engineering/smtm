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
import java.io.UnsupportedEncodingException;
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
                                       HttpSession session) throws JsonProcessingException, UnsupportedEncodingException {
        User savedUser = userService.userAuth(user);
        if(savedUser != null){
            ResponseEntity.BodyBuilder builder = ResponseEntity.ok();
            session.setAttribute("user", savedUser); // 세션에 유저 정보 설정
            if(cookieExist.equals("empty")) {
                Map<LocalDate, Float> preference = calcPreference(savedUser); // 쿠키에 선호도 정보 저장
                String cookieValue = objectMapper.writeValueAsString(preference);
                cookieValue = cookieValue.replaceAll("\"", "+");
                cookieValue = cookieValue.replaceAll(",", "_");
                ResponseCookie cookie = ResponseCookie.from("preference", cookieValue)
                        .path("/")
                        .build();
                builder = builder.header(HttpHeaders.SET_COOKIE, cookie.toString());
            }
            return builder.body(String.valueOf(user.isKorean()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    private Map<LocalDate, Float> calcPreference(User user) {
        return userService.calcPreference(user);
    }

    // 회원가입
    @PostMapping("/register")
    public String processRegister(User user) {
        return userService.updateUserInfo(user) ? "true" : "false";
    }

    // 회원정보변경
    @PostMapping("/update")
    public String processUpdateInfo(User user, HttpSession session) {
        return userService.updateUserInfo(user) ? "true" : "false";
    }

    // 사용자의 선호도 정보
    @GetMapping("/preference")
    public String getPreference(@CookieValue(value = "preference", required = false, defaultValue = "empty") String cookieValue){
        if(cookieValue.equals("empty")){
            return "false";
        }
        else {
            cookieValue = cookieValue.replaceAll("\\+", "\"");
            cookieValue = cookieValue.replaceAll("_", ",");
            return cookieValue;
        }
    }
    
    // 선호도 기입
    @PostMapping("/preference")
    public String setPreference(@RequestParam("id") Menu menu,
                                @RequestParam int preference,
                                HttpSession session){
        System.out.println(menu);
        User user = (User) session.getAttribute("user");
        return userService.setPreference(user, menu, preference) ? "true" : "false";
    }

}