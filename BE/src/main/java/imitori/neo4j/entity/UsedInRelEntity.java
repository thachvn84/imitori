package imitori.neo4j.entity;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "USED_IN")
public class UsedInRelEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String role;

    @StartNode
    private WordEntity startWord;

    @EndNode
    private SentenceEntity endSentence;

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public WordEntity getStartWord() {
        return startWord;
    }

    public void setStartWord(WordEntity w) {
        startWord = w;
    }

    public SentenceEntity getEndSentence() {
        return endSentence;
    }

    public void setEndSentence(SentenceEntity s) {
        this.endSentence = s;
    }
}