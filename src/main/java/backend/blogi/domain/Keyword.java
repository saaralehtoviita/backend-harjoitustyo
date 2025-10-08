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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

//entiteettiluokka; @Entity annotaation avulla luodaan olio automaattisesti tietokantaan
//@Id + @GeneratedValue annotaatioiden avulla tietokanta generoi automaattiset id-arvot eikä niitä siksi konstruktoreissa käytetä

//yhdellä postauksella voi olla useita keywordeja, mutta sama keyword voi myös esiintyä useassa postauksessa ->
//tämä luokka tulee siis mahdollisesti olemaan manyToMany luokka

@Entity
@Table(name = "KEYWORDS") //yhteinäisyyden säilymiseksi vaihdetaan taulut suomenkieliksi
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long keywordId;

    private String strKeyword;

    @ManyToMany(mappedBy = "keywords", fetch = FetchType.LAZY) //mappedBy täytyy olla saman, kun parentluokan (post), eli keywords lista
    @JsonIgnore
    private Set<Post> posts = new HashSet<>();


    //@ManyToOne
    //@JoinColumn(name = "postId")
    //private Post postaus;

    public Keyword () {

    }

    public Keyword(String strKeyword) {
        this.strKeyword = strKeyword;
    }

    

    public Keyword(Long keywordId, String strKeyword, Set<Post> posts) {
        this.keywordId = keywordId;
        this.strKeyword = strKeyword;
        this.posts = posts;
    }

    public Long getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(Long keywordId) {
        this.keywordId = keywordId;
    }

    public String getStrKeyword() {
        return strKeyword;
    }

    public void setStrKeyword(String strKeyword) {
        this.strKeyword = strKeyword;
    }

/*     public Post getPostaus() {
        return postaus;
    }

    public void setPostaus(Post postaus) {
        this.postaus = postaus;
    } */

    @Override
    public String toString() {
        return "Keyword [keywordId=" + keywordId + ", strKeyword=" + strKeyword + "]";
    }

    @Override
    public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Keyword)) return false;
    return strKeyword != null && strKeyword.equals(((Keyword) o).getStrKeyword());
}

    @Override
    public int hashCode() {
    return strKeyword != null ? strKeyword.hashCode() : 0;
    }

}
