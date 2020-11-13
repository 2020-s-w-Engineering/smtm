package cse.swengineering.smtm.users;

import cse.swengineering.smtm.menus.Menu;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Basic(optional = false)
    private String userId;
    @Basic(optional = false)
    private String password;
    private boolean isKorean; // rest api로 날라갈 때는 korean으로 날라간다
    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, Integer> preference = new HashMap<>();

    public User() {
    }

    public User(String userId, String password, boolean isKorean) {
        this.userId = userId;
        this.password = password;
        this.isKorean = isKorean;
    }

    public User(String userId, String password) {
        this(userId, password, false);
    }

    public Map<String, Integer> getPreference() {
        return preference;
    }

    public void setPreference(Map<String, Integer> preference) {
        this.preference = preference;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isKorean() {
        return isKorean;
    }

    public void setKorean(boolean korean) {
        isKorean = korean;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isKorean == user.isKorean &&
                Objects.equals(userId, user.userId) &&
                Objects.equals(password, user.password);
    }
}
