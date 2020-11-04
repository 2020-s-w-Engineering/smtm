package cse.swengineering.smtm.menus;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;

@Entity
public class Menu {

    @Id
    @GeneratedValue
    private Long id;

    @Basic(optional = false)
    private String korName;
    @Basic(optional = false)
    private String engName;

    private String nutrition;

    private byte[] img;

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

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", korName='" + korName + '\'' +
                ", engName='" + engName + '\'' +
                '}';
    }
}
