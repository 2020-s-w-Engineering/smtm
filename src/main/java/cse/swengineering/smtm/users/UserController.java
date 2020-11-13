package cse.swengineering.smtm.users;

import cse.swengineering.smtm.menus.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    // 로그인 처리
    @PostMapping("/login")
    public String processLogin(User user, HttpSession session) {
        if(userService.userAuth(user)){
            session.setAttribute("user", user);
            return "true";
        }
        return "false";
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
    public String setPreference(@RequestParam("korName") Menu menu,
                                @RequestParam int preference,
                                HttpSession session){
        System.out.println(menu);
        User user = (User) session.getAttribute("user");
        return userService.setPreference(user, menu, preference) ? "true" : "false";
    }

}