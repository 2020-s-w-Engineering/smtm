package cse.swengineering.smtm.menus;

import java.util.HashSet;
import java.util.Set;

public class Main {

    private String type;
    private String calories;
    private Set<Menu> menus = new HashSet<>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }
}
