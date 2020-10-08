package cse.swengineering.smtm.menus;

import java.util.Set;

public class Diet {

    private String day; // 요일
    private float avgOfPreference;
    private Set menus;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public float getAvgOfPreference() {
        return avgOfPreference;
    }

    public void setAvgOfPreference(float avgOfPreference) {
        this.avgOfPreference = avgOfPreference;
    }

    public Set getMenus() {
        return menus;
    }

    public void setMenus(Set menus) {
        this.menus = menus;
    }
}
