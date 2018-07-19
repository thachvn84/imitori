package imitori.neo4j.entity;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.annotation.TypeAlias;

@NodeEntity
@TypeAlias("Word")
public class WordEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String word;
    private String kana;
    private String romaji;
    /*
    @Relationship(type="SIMILAR_TO", direction = Relationship.OUTGOING)
    private List<WordEntity> similarWords = new ArrayList<>();

    @Relationship(type="USED_IN", direction = Relationship.OUTGOING)
    private List<SentenceEntity> usedInSens = new ArrayList<>();

    @Relationship(type="CAN_TRANSLATE", direction = Relationship.OUTGOING)
    private List<SentenceEntity> canTransSens = new ArrayList<>();

    @Relationship(type="IN_LANG", direction = Relationship.OUTGOING)
    private List<LanguageEntity> inLangs = new ArrayList<>();
    */
    public WordEntity() {
    }

    public WordEntity(String word, String kana, String romaji) {
        this.word = word;
        this.kana = kana;
        this.romaji = romaji;
    }

    public Long getId() {
        return id;
    }

    public String getWord() {
        return this.word;
    }

    public void setWord(String w) {
        this.word = w;
    }

    public String getKana() {
        return this.kana;
    }

    public void setKana(String k) {
        this.kana = k;
    }

    public String getRomaji() {
        return this.romaji;
    }

    public void setRomaji(String r) {
        this.romaji = r;
    }


}