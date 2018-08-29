package imitori.entity.neo4j;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "RELATED_TO")
public class RelatedToRelNeoEntity {
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