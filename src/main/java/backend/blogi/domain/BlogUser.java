package backend.blogi.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//entiteettiluokka; @Entity annotaation avulla luodaan olio automaattisesti tietokantaan
//@Id + @GeneratedValue annotaatioiden avulla tietokanta generoi automaattiset id-arvot eikä niitä siksi konstruktoreissa käytetä

@Entity
@Table(name ="Users")
public class BlogUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String firstName;

    private String lastName;

    private String userName;

    //lisätään password myöhemmin jos opin käyttämään spring securitya!

    //private Role rooli; lisätään tämä, jos opin antamaan oikeuksia eri tasoisille käyttäjille

    private String email;

    //yhdellä käyttäjällä voi olla useita postauksia
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kirjoittaja")
    @JsonIgnore
    private List<Post> postaukset;

    public BlogUser () {

    }

    public BlogUser(String firstName, String lastName, String userName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Post> getPostaukset() {
        return postaukset;
    }

    public void setPostaukset(List<Post> postaukset) {
        this.postaukset = postaukset;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
                + userName + ", email=" + email + "]";
    }

    





}
