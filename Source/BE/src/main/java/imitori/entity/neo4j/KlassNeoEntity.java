package imitori.entity.neo4j;

import java.util.ArrayList;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import imitori.entity.neo4j.rel.DivideToRelNeoEntity;

@NodeEntity(label = "Klass")
public class KlassNeoEntity {
    @Id
    @GeneratedValue
    private Integer id;

    /* ID of KlassMon Collection*/
    public Integer mongoId;

    public String klass;

    public String hanviet;

    public String mean;

    @Relationship(type = "DEVIDE_TO", direction = Relationship.INCOMING)
    public ArrayList<DivideToRelNeoEntity> kanji = new ArrayList<>();

}