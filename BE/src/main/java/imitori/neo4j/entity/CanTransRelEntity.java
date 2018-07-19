package imitori.neo4j.entity;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type="CAN_TRANSLATE")
public class CanTransRelEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Integer score;

    @StartNode
    private WordEntity startWord;

    @EndNode
    private SentenceEntity endSentence;

    public Long getId() {
        return id;
    }
    
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer s) {
        this.score = s;
    }

    public WordEntity getStartWord() {
        return startWord;
    }

    public void setStartWord(WordEntity sw) {
        this.startWord = sw;
    }

    public SentenceEntity getEndSentence() {
        return endSentence;
    }

    public void setEndSentence(SentenceEntity es) {
        this.endSentence = es;
    }
}