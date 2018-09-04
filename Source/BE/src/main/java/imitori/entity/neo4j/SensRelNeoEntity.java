package imitori.entity.neo4j;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "SensRel")
public class SensRelNeoEntity {
    @Id
    @GeneratedValue
    private Integer id;

    public Integer score;
}