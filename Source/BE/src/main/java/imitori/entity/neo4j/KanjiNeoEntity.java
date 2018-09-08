package imitori.entity.neo4j;

import java.util.ArrayList;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import imitori.entity.neo4j.rel.DivideToRelNeoEntity;

@NodeEntity(label = "Kanji")
public class KanjiNeoEntity {
    @Id
    @GeneratedValue
    private Integer id;

    /* ID of KanjiMon Collection */
    public Integer mongoId;

    public String kanji;
    public String hanviet;
    public String mean;

    @Relationship(type = "DIVIDE_TO", direction = Relationship.OUTGOING)
    public ArrayList<DivideToRelNeoEntity> klass = new ArrayList<>();
    
}