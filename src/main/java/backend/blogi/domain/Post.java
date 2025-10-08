package backend.blogi.domain;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

//entiteettiluokka; @Entity annotaation avulla luodaan olio automaattisesti tietokantaan
//@Id + @GeneratedValue annotaatioiden avulla tietokanta generoi automaattiset id-arvot eikä niitä siksi konstruktoreissa käytetä


@Entity
@Table(name = "POSTS")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    @NotEmpty(message = "Name of post cannot be empty")
    @Size(min= 1, max= 250)
    private String title;

    @NotEmpty(message = "Text field cannot be empty")
    @Size(min= 1, max= 1000)
    private String text;

    private String postDate;

    //yhdellä postauksella on yksi kirjoittaja, mutta yhdellä kirjoittajalla voi olla monta postausta
    //@ManyToOne annotaatio Post-luokassa vaaditaan, jotta yhteys toteutuu
    //tietokannassa userId post-taulun fk:na
    @ManyToOne
    @JoinColumn(name = "userId")
    private BlogUser kirjoittaja;

    //yhdellä postauksella voi olla monta avainsanaa
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "postaus")
    @ManyToMany
    @JoinTable(name = "POST_KEYWORDS",
    joinColumns = @JoinColumn(name = "post_id"),
    inverseJoinColumns = @JoinColumn(name = "keyword_id")    
    )
    

    @JsonIgnore
    private Set<Keyword> keywords;//tämä rakenne muuuttunee, jos opin tekemään manytomany suhteen

    //konstruktori ilman paramametrejä
    public Post() {
        
    }

    //konstruktori ilman kirjoittajaa
    public Post(String title, String text, String postDate) {
        this.title = title;
        this.text = text;
        this.postDate = postDate;
    }

    //konstruktori kirjoittajalla
    public Post(String title, String text, String postDate, BlogUser kirjoittaja) {
        this.title = title;
        this.text = text;
        this.postDate = postDate;
        this.kirjoittaja = kirjoittaja;
    }


    //kosntruktori kirjoittajan ja kwywordsien kanssa
        public Post(String title, String text, String postDate, BlogUser kirjoittaja, Set<Keyword> keywords ) {
        this.title = title;
        this.text = text;
        this.postDate = postDate;
        this.kirjoittaja = kirjoittaja;
        this.keywords = keywords;
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

        public BlogUser getKirjoittaja() {
            return kirjoittaja;
        }

        public void setKirjoittaja(BlogUser kirjoittaja) {
            this.kirjoittaja = kirjoittaja;
        }

        public Set<Keyword> getKeywords() {
            return keywords;
        }

        public void setKeywords(Set<Keyword> keywords) {
            if (keywords != null) {
                this.keywords = keywords;
            } else {
                this.keywords = new HashSet<>();
            }
            }

        @Override
        public String toString() {
            return "Post [postId=" + postId + ", title=" + title + ", text=" + text + ", postDate=" + postDate
                    + ", kirjoittaja=" + kirjoittaja + "]";
        }

}
