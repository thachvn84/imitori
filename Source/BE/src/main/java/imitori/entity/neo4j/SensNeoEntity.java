package imitori.entity.neo4j;

import java.util.ArrayList;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import imitori.entity.neo4j.rel.SensRelNeoEntity;

@NodeEntity(label = "Sentences")
public class SensNeoEntity {
    @Id
    @GeneratedValue
    private Integer id;

    public String ja;
    public String en;
    public String vi;

    @Relationship(type = "HAS_SENTENCES", direction = Relationship.INCOMING)
    ArrayList<SensRelNeoEntity> referFrom = new ArrayList<>();

}