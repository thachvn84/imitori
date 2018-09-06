package imitori.entity.neo4j.rel;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import imitori.entity.neo4j.WordNeoEntity;

@RelationshipEntity(type = "OPPOSITE_TO")
public class OppositeToRelNeoEntity {
    @Id
    @GeneratedValue
    private Integer id;

    public Integer score;

    @StartNode
    public WordNeoEntity startWord;

    @EndNode
    public WordNeoEntity endWord;

    public Integer getId() {
        return id;
    }
}