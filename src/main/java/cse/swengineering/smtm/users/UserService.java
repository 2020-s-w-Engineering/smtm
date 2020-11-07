package cse.swengineering.smtm.users;

import cse.swengineering.smtm.menus.Diet;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean userAuth(User user) {
        List<User> users = userRepository.findAll();
        for(User dbUser : users){
            if(dbUser.equals(user))
                return true;
        }
        return false;
    }

    public boolean updateUserInfo(User user){
        if(user.getId() == null) { // save
            List<User> users = userRepository.findAll();
            for (User dbUser : users) {
                if (dbUser.getUserId().equals(user.getUserId())) // 중복되는 아이디인 경우 false
                    return false;
            }
        }
        // user의 id가 있는 경우(update)
        // 중복되는 id가 없는 경우(save)
        userRepository.save(user); // 위의 두 경우 모두 처리
        return true;
    }

    public boolean setPreference(){
        return false;
    }

    public float calcPreference(Diet diet) {
        // todo 식단의 모든 메뉴에 대한 선호도를 계산하여 평균값 리턴
        return 0.0f;
    }

}
