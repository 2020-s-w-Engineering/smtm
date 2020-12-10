package cse.swengineering.smtm.users;

import cse.swengineering.smtm.menus.Diet;
import cse.swengineering.smtm.menus.Menu;
import cse.swengineering.smtm.menus.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    MenuService menuService;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User userAuth(User user) {
        List<User> users = userRepository.findAll();
        for(User dbUser : users){
            if(dbUser.equals(user))
                return dbUser;
        }
        return null;
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

    public boolean setPreference(User user, Menu menu, int preference){
        user = userRepository.findById(user.getId()).get();
        user.getPreferenceMap().put(menu.getId(), preference);
        calcPreference(user);
        userRepository.save(user);
        return true;
    }

    public User calcPreference(User user) {
        List<Diet> diets = menuService.getDiets();
        for(Diet diet : diets){
            int sum = 0;
            int num = 0;
            Set<Menu> menus = diet.getAllMenus();
            Map<Long, Integer> preferenceMap = userRepository.findById(user.getId()).get().getPreferenceMap();
            Menu[] menuArr = menus.toArray(new Menu[0]);
            for(Menu menu : menuArr){
                if(preferenceMap.containsKey(menu.getId())) { // 선호도 표기한 메뉴인 경우
                    int preference = preferenceMap.get(menu.getId());
                    sum = sum + preference;
                    num++;
                }
            }
            float avg = (float)sum / num;
            user.getMonthAvgPreference().put(diet.getDate(), avg);
        }
        return user;
    }

}
