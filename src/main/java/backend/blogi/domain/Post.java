package backend.blogi.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

//entiteettiluokka; @Entity annotaation avulla luodaan olio automaattisesti tietokantaan
//@Id + @GeneratedValue annotaatioiden avulla tietokanta generoi automaattiset id-arvot eikä niitä siksi konstruktoreissa käytetä


@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    @NotEmpty(message = "Name of post cannot be empty")
    @Size(min= 1, max= 250)
    private String title;

    @NotEmpty(message = "Text filed cannot be empty")
    @Size(min= 1)
    private String text;

    private String postDate;

    //yhdellä postauksella on yksi kirjoittaja, mutta yhdellä kirjoittajalla voi olla monta postausta
    //@ManyToOne annotaatio Post-luokassa vaaditaan, jotta yhteys toteutuu
    //tietokannassa userId post-taulun fk:na
    @ManyToOne
    @JoinColumn(name = "userId")
    public User kirjoittaja;


    //private List<Keyword> keywords; lisätään tämä, jos opin tekemään manyToMany suhteen

    //konstruktori ilman paramametrejä
    public Post() {
        
    }

    //konstruktori ilman kirjoittajaa
    public Post(String title, String text, String postDate) {
        this.title = title;
        this.text = text;
        this.postDate = postDate;
    }

    //kosntruktori kirjoittajan kanssa
        public Post(String title, String text, String postDate, User kirjoittaja) {
        this.title = title;
        this.text = text;
        this.postDate = postDate;
        this.kirjoittaja = kirjoittaja;
    }

        public Long getPostId() {
            return postId;
        }

        public void setPostId(Long postId) {
            this.postId = postId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getPostDate() {
            return postDate;
        }

        public void setPostDate(String postDate) {
            this.postDate = postDate;
        }

        public User getKirjoittaja() {
            return kirjoittaja;
        }

        public void setKirjoittaja(User kirjoittaja) {
            this.kirjoittaja = kirjoittaja;
        }

/*         public List<Keyword> getKeywords() {
            return keywords;
        }

        public void setKeywords(List<Keyword> keywords) {
            this.keywords = keywords;
        } */

        @Override
        public String toString() {
            return "Post [postId=" + postId + ", title=" + title + ", text=" + text + ", postDate=" + postDate
                    + ", kirjoittaja=" + kirjoittaja + "]";
        }

        

    





    

    


}
