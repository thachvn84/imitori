package imitori.neo4j.entity;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label="FullWord")
public class FullWordEntity {
    @Id
    @GeneratedValue
    private Long id;

    public String word;
    public String spell;
    public String lang;

    @Relationship(type="SIMILAR_TO", direction = Relationship.OUTGOING)
    private List<SimilarToRelEntity> similarTo = new ArrayList<>();

    @Relationship(type="SIMILAR_TO", direction = Relationship.INCOMING)
    private List<SimilarToRelEntity> similarFrom = new ArrayList<>();
    
    public FullWordEntity() {
    }

    public FullWordEntity(Long id, String word, String spell, String lang) {
        this.id = id;
        this.word = word;
        this.spell = spell;
        this.lang = lang;
    }

    public Long getId() {
        return id;
    }

    public List<SimilarToRelEntity> getSimilarTo() {
        return similarTo;
    }

    public List<SimilarToRelEntity> getSimilarFrom() {
        return similarFrom;
    }
}