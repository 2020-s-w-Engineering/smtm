package cse.swengineering.smtm.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@org.springframework.stereotype.Controller
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/users")
    public List<User> users(){
        return repository.findAll();
    }

    @PostMapping("/users")
    public User process(@RequestParam String userId,
                        @RequestParam String password,
                        @RequestParam boolean isKorean) {
        return repository.save(new User(userId, password, isKorean));
    }

    @GetMapping("/users/{id}")
    public Optional<User> employees(@PathVariable Long id) {
        return repository.findById(id);
    }

}
