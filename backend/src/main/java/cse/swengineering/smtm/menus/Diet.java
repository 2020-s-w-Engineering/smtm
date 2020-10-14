package cse.swengineering.smtm.menus;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

public class Diet {

    private int day; // 요일
    private LocalDate date;
    private float avgOfPreference;
    private Set<Menu> mainA = new LinkedHashSet<>();
    private Set<Menu> mainC = new LinkedHashSet<>();
    private String calories;

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

    public Set<Menu> getMainA() {
        return mainA;
    }

    public void setMainA(Set<Menu> mainA) {
        this.mainA = mainA;
    }

    public Set<Menu> getMainC() {
        return mainC;
    }

    public void setMainC(Set<Menu> mainC) {
        this.mainC = mainC;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Diet{" +
                "date=" + date +
                ", mainA=" + mainA +
                ", mainB=" + mainC +
                ", calories=" + calories +
                '}';
    }
}
