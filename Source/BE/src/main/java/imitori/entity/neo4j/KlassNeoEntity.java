package imitori.entity.neo4j;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Klass")
public class KlassNeoEntity {
    @Id
    @GeneratedValue
    private Integer Ii;
}