package cse.swengineering.smtm.menus;

import cse.swengineering.smtm.users.User;
import cse.swengineering.smtm.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class MenuController {

    @Autowired
    private MenuRepository repository;

//    @GetMapping("/menus/week")
//    public List<User> users(){
//        return repository.findAll();
//    }
//
//    @GetMapping("/menus/{date}")
//    public Optional<User> employees(@PathVariable Long id){
//        return repository.findById(id);
//    }
//
//    @GetMapping("/menus/calendar")
//    public Optional<User> employees(@PathVariable Long id){
//        return repository.findById(id);
//    }
//
//    @PostMapping("/users")
//    public User process(@RequestParam String userId,
//                        @RequestParam String password,
//                        @RequestParam boolean isKorean){
//        return repository.save(new User(userId, password, isKorean));
//    }



}
