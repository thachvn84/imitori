package imitori.entity.neo4j.rel;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import imitori.entity.neo4j.KanjiNeoEntity;
import imitori.entity.neo4j.KlassNeoEntity;

@RelationshipEntity(type = "DIVIDE_TO")
public class DivideToRelNeoEntity {
    @Id
    @GeneratedValue
    private Integer id;

    public Integer score;

    @StartNode
    public KanjiNeoEntity startKanji;
    
    @EndNode
    public KlassNeoEntity endKlass;

    public Integer getId() {
        return id;
    }
}