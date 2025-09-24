package backend.blogi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//entiteettiluokka; @Entity annotaation avulla luodaan olio automaattisesti tietokantaan
//@Id + @GeneratedValue annotaatioiden avulla tietokanta generoi automaattiset id-arvot eikä niitä siksi konstruktoreissa käytetä

//yhdellä postauksella voi olla useita keywordeja, mutta sama keyword voi myös esiintyä useassa postauksessa ->
//tämä luokka tulee siis mahdollisesti olemaan manyToMany luokka

@Entity
@Table(name = "avainsanat") //yhteinäisyyden säilymiseksi vaihdetaan taulut suomenkieliksi
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long keywordId;

    private String strKeyword;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post postaus;

    public Keyword () {

    }

    public Keyword(String strKeyword) {
        this.strKeyword = strKeyword;
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

    public Post getPostaus() {
        return postaus;
    }

    public void setPostaus(Post postaus) {
        this.postaus = postaus;
    }

    @Override
    public String toString() {
        return "Keyword [keywordId=" + keywordId + ", strKeyword=" + strKeyword + "]";
    }



    







    

}
