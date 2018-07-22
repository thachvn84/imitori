package imitori.neo4j.entity;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.annotation.TypeAlias;

@NodeEntity(label="Word")
public class WordEntity {
    @Id
    @GeneratedValue
    private Long id;

    public String word;
    public String kana;
    public String romaji;
    public String mean;
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

    public WordEntity(Long id, String word, String kana, String romaji) {
        this.id = id;
        this.word = word;
        this.kana = kana;
        this.romaji = romaji;
    }

    public WordEntity(Long id, String word, String kana, String romaji, String mean) {
        this.id = id;
        this.word = word;
        this.kana = kana;
        this.romaji = romaji;
        this.mean = mean;
    }

    public Long getId() {
        return id;
    }
}