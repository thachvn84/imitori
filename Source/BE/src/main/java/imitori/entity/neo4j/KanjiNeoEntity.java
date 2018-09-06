package imitori.entity.neo4j;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Kanji")
public class KanjiNeoEntity {
    @Id
    @GeneratedValue
    private Integer id;
}