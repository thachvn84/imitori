package imitori.neo4j.entity;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type="SIMILAR_TO")
public class SimilarToRelEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Integer score;

    @StartNode
    private WordEntity startWord;

    @EndNode
    private WordEntity endWord;

    public Long getId() {
        return id;
    }

    public WordEntity getStartWord() {
        return startWord;
    }

    public void setStartWord(WordEntity w) {
        this.startWord = w;
    }

    public WordEntity getEndWord() {
        return endWord;
    }

    public void setEndWord(WordEntity w) {
        this.endWord = w;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer s) {
        this.score = s;
    }
}