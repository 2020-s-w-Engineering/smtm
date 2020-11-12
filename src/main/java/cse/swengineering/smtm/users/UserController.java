package cse.swengineering.smtm.users;

import cse.swengineering.smtm.menus.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    UserRepository userRepository;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // test 용도
    @GetMapping
    public List<User> users(){
        List<User> all = userRepository.findAll();
        return all;
    }

    // 로그인 처리
    @PostMapping("/login")
    public String processLogin(User user) {
        return userService.userAuth(user) ? "true" : "false";
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
    
    // 선호도 기입
    @PostMapping("/preference")
    // 문서에는 getPreference라고 되어 있는데 그게 잘못된거겠지
    // 유저 정보는 세션에 있는거 쓰지뭐
    public String setPreference(Menu menu,
                                @RequestParam int preference,
                                HttpSession session){
//        User user = (User) session.getAttribute("user");
//        userService.setPreference(user, menu, preference);
        return null;
    }

}