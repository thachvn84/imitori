package imitori.entity.neo4j.rel;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.StartNode;

import imitori.entity.neo4j.DicNeoEntity;
import imitori.entity.neo4j.WordNeoEntity;

@NodeEntity(label = "SourceOf")
public class SourceOfRelNeoEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @StartNode
    public DicNeoEntity startDic;

    @EndNode
    public WordNeoEntity endWord;
}