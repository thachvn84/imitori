package imitori.entity.neo4j.rel;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.StartNode;

import imitori.entity.neo4j.SensNeoEntity;
import imitori.entity.neo4j.WordNeoEntity;

@NodeEntity(label = "SensRel")
public class SensRelNeoEntity {
    @Id
    @GeneratedValue
    private Integer id;

    public Integer score;

    @StartNode
    public WordNeoEntity startWord;

    @EndNode
    public SensNeoEntity endSens;

}