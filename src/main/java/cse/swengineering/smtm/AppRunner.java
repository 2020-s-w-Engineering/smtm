package cse.swengineering.smtm;

import cse.swengineering.smtm.users.User;
import cse.swengineering.smtm.users.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

    UserRepository repository;

//    @Autowired
//    DietService dietService;

    public AppRunner(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        repository.save(new User("sengbok", "123", true));
        repository.save(new User("yeonju", "456", true));
        repository.save(new User("yejin", "789", false));

//        List<Diet> dietList = dietService.getDietList();
//        System.out.println(dietList.size() + "일치의 식단 정보를 저장하고 있습니다");
//        dietList.forEach(Diet::printKOR);
    }
}
