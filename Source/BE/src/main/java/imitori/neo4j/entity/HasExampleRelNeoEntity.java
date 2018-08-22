package imitori.neo4j.entity;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "OPPOSITE_TO")
public class HasExampleRelNeoEntity {
    @Id
    @GeneratedValue
    private Long id;

    public Integer score;

    @StartNode
    public WordEntity startWord;

    @EndNode
    public JASentenceNeoEntity endSens;

    public Long getId() {
        return id;
    }
}