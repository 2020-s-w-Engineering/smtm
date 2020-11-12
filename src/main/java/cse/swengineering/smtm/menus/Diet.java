package cse.swengineering.smtm.menus;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.*;

public class Diet {

    private LocalDate date;
    private float avgOfPreference;
    private Map<String, Main> breakfastMains = new HashMap<>();
    private Map<String, Main> lunchMains = new HashMap<>();
    private Map<String, Main> dinnerMains = new HashMap<>();

    public float getAvgOfPreference() {
        return avgOfPreference;
    }

    public void setAvgOfPreference(float avgOfPreference) {
        this.avgOfPreference = avgOfPreference;
    }

    public Map<String, Main> getBreakfastMains() {
        return breakfastMains;
    }

    public void setBreakfastMains(Map<String, Main> breakfastMains) {
        this.breakfastMains = breakfastMains;
    }

    public Map<String, Main> getLunchMains() {
        return lunchMains;
    }

    public void setLunchMains(Map<String, Main> lunchMains) {
        this.lunchMains = lunchMains;
    }

    public Map<String, Main> getDinnerMains() {
        return dinnerMains;
    }

    public void setDinnerMains(Map<String, Main> dinnerMains) {
        this.dinnerMains = dinnerMains;
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
                '}';
    }

    // debug용
    public void print() {
        System.out.println("*********" + this.date + "*********");
        System.out.println("아침 메뉴");
        Main mainA = this.getBreakfastMains().get("A");
//        System.out.println(mainA.getType() + "[" + mainA.getCalories() + "]");
        mainA.getMenus().forEach(System.out::println);
        Main mainC = this.getBreakfastMains().get("C");
//        System.out.println(mainC.getType() + "[" + mainC.getCalories() + "]");
        mainC.getMenus().forEach(System.out::println);

        System.out.println("점심 메뉴");
        mainA = this.getLunchMains().get("A");
//        System.out.println(mainA.getType() + "[" + mainA.getCalories() + "]");
        mainA.getMenus().forEach(System.out::println);
        mainC = this.getLunchMains().get("C");
//        System.out.println(mainC.getType() + "[" + mainC.getCalories() + "]");
        mainC.getMenus().forEach(System.out::println);

        System.out.println("저녁 메뉴");
        mainA = this.getDinnerMains().get("A");
//        System.out.println(mainA.getType() + "[" + mainA.getCalories() + "]");
        mainA.getMenus().forEach(System.out::println);
        mainC = this.getDinnerMains().get("C");
//        System.out.println(mainC.getType() + "[" + mainC.getCalories() + "]");
        mainC.getMenus().forEach(System.out::println);
    }
}
