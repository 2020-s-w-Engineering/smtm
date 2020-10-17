package cse.swengineering.smtm.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id; // db에서 식별할 id

    private String userId;
    private String password;
    private boolean isKorean; // rest api로 날라갈 때는 korean으로 날라간다

    public User() {
    }

    public User(String userId, String password, boolean isKorean) {
        this.userId = userId;
        this.password = password;
        this.isKorean = isKorean;
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
}
