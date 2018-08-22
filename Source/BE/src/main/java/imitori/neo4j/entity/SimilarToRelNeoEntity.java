package imitori.neo4j.entity;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "SIMILAR_TO")
public class SimilarToRelNeoEntity {
    @Id
    @GeneratedValue
    private Integer id;

    public Integer score;

    @StartNode
    public WordEntity startWord;

    @EndNode
    public WordEntity endWord;

    public Integer getId() {
        return id;
    }
}