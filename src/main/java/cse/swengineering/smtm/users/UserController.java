package cse.swengineering.smtm.users;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
    public String processUpdateInfo(User user) {
        return userService.updateUserInfo(user) ? "true" : "false";
    }

}