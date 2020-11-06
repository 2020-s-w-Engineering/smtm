package cse.swengineering.smtm.menus;

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

    /*public void printKOR() {
        System.out.println("******** " + getDate() + " 식단 ********");
        for (Main main : mainsKOR) {
            if (main.getType().equals("A"))
                System.out.println("======== Main A ========");
            else
                System.out.println("======== Main C ========");
            System.out.println("======== " + main.getCalories() + "kcal ========");
            Arrays.stream(main.getMenus().toArray()).forEach(System.out::println);
        }
    }

    public void printENG() {
        System.out.println("******** " + getDate() + " 식단 ********");
        for (Main main : mainsENG) {
            if (main.getType().equals("A"))
                System.out.println("======== Main A ========");
            else
                System.out.println("======== Main C ========");
            System.out.println("======== " + main.getCalories() + "kcal ========");
            Arrays.stream(main.getMenus().toArray()).forEach(System.out::println);
        }
    }*/
}
