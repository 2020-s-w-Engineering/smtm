package cse.swengineering.smtm.menus;

import java.time.LocalDate;
import java.util.*;

public class Diet {

    private int day; // 요일
    private LocalDate date;
    private float avgOfPreference;
    private List<Main> mainsKOR = new ArrayList<>();
    private List<Main> mainsENG = new ArrayList<>();

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

    public List<Main> getMainsKOR() {
        return mainsKOR;
    }

    public void setMainsKOR(List<Main> mainsKOR) {
        this.mainsKOR = mainsKOR;
    }

    public List<Main> getMainsENG() {
        return mainsENG;
    }

    public void setMainsENG(List<Main> mainsENG) {
        this.mainsENG = mainsENG;
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
                ", mains=" + mainsKOR +
                '}';
    }

    public void printKOR() {
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
    }
}
