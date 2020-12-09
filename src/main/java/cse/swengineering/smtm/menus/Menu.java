package cse.swengineering.smtm.menus;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
public class Menu {

    @Id
    private Long id;

    @Basic(optional = false)
    private String korName;
    @Basic(optional = false)
    private String engName;

    @Transient
    @JsonIgnore
    private List<byte[]> img = new ArrayList<>();

    public Menu() {
    }

    public Menu(String korName, String engName) {
        this.korName = korName;
        this.engName = engName;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getKorName() {
        return korName;
    }

    public void setKorName(String korName) {
        this.korName = korName;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public List<byte[]> getImg() {
        return img;
    }

    public void setImg(List<byte[]> img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "korName='" + korName + '\'' +
                ", engName='" + engName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return  Objects.equals(korName, menu.korName) &&
                Objects.equals(engName, menu.engName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(korName, engName);
    }
}
