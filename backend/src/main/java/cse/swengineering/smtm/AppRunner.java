package cse.swengineering.smtm;

import cse.swengineering.smtm.users.User;
import cse.swengineering.smtm.users.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

    UserRepository repository;

    public AppRunner(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        repository.save(new User("tjdqhr", "123", true));
        repository.save(new User("duswn", "456", true));
        repository.save(new User("dPwls", "789", false));
    }
}
