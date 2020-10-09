package cse.swengineering.smtm;

import cse.swengineering.smtm.menus.Diet;
import cse.swengineering.smtm.menus.DietService;
import cse.swengineering.smtm.menus.Menu;
import cse.swengineering.smtm.users.User;
import cse.swengineering.smtm.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AppRunner implements ApplicationRunner {

    UserRepository repository;

    @Autowired
    DietService dietService;

    public AppRunner(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        repository.save(new User("tjdqhr", "123", true));
        repository.save(new User("duswn", "456", true));
        repository.save(new User("dPwls", "789", false));

        List<Diet> dietList = dietService.getDietList();
        System.out.println(dietList.size() + "일치의 식단 정보를 저장하고 있습니다");
        for(Diet diet : dietList){
            System.out.println("=====  " + diet.getDate() + " 식단 =====");
            Arrays.stream(diet.getMenus().toArray(new Menu[0])).forEach(System.out::println);
        }
    }
}
