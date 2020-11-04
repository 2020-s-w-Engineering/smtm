package cse.swengineering.smtm.menus;

import java.util.HashSet;
import java.util.Set;

public class Main {

    private String type;
    private String calories;
    private Set<Menu> menusA = new HashSet<>();
    private Set<Menu> menusC = new HashSet<>();

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

    public Set<Menu> getMenusA() {
        return menusA;
    }

    public void setMenusA(Set<Menu> menusA) {
        this.menusA = menusA;
    }

    public Set<Menu> getMenusC() {
        return menusC;
    }

    public void setMenusC(Set<Menu> menusC) {
        this.menusC = menusC;
    }
}
