package cse.swengineering.smtm.users;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@org.springframework.stereotype.Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> users(){
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public User process(@RequestParam String userId,
                        @RequestParam String password,
                        @RequestParam boolean isKorean) {
        return userRepository.save(new User(userId, password, isKorean));
    }

    @GetMapping("/users/{id}")
    public Optional<User> employees(@PathVariable Long id) {
        return userRepository.findById(id);
    }

}