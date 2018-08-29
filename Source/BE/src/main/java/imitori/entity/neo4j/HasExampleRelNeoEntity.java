package imitori.entity.neo4j;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import imitori.entity.neo4j.WordEntity;

@RelationshipEntity(type = "OPPOSITE_TO")
public class HasExampleRelNeoEntity {
    @Id
    @GeneratedValue
    private Integer id;

    public Integer score;

    @StartNode
    public WordEntity startWord;

    @EndNode
    public JASentenceNeoEntity endSens;

    public Integer getId() {
        return id;
    }
}