package imitori.neo4j.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.annotation.TypeAlias;

@NodeEntity
@TypeAlias("Sentence")
public class SentenceEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String sentence;

    public SentenceEntity() {
        
    }

    public SentenceEntity(String s) {
        this.sentence = s;
    }

    public Long getId() {
        return id;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String s) {
        this.sentence = s;
    }

}