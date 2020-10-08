package cse.swengineering.smtm.menus;

import java.util.HashSet;
import java.util.Set;

public class Diet {

    private int day; // 요일
    private float avgOfPreference;
    private Set<Menu> menus = new HashSet<>();

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public float getAvgOfPreference() {
        return avgOfPreference;
    }

    public void setAvgOfPreference(float avgOfPreference) {
        this.avgOfPreference = avgOfPreference;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Diet{" +
                "day=" + day +
                ", menus=" + menus +
                '}';
    }
}
