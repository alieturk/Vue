package app.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NamedQueries(
        @NamedQuery(name = "User_find_By_Query", query = "select u from User u where u.email = ?1")
)
@Entity
@Table(name = "`Users`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "`id`", nullable = false)
    private Long id;

    private String name = "Willam Alexender";

    private String email = "willamalexender@live.nl";

    private String hashedPassword = "hashedPassword";

    private String role = "Registered user";

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Bids> bids = new ArrayList<Bids>();


    public User(Long id, String name, String email, String hashedPassword, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.role = role;
    }

    public User() {

    }
    public boolean isPasswoordSame(String hashedPassword){
        String result = this.getEmail().split("@")[0];
        System.out.println("Result: " + result);
        return Objects.equals(hashedPassword, result);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
