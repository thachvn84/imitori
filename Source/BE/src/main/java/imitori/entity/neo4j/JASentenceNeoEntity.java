package imitori.entity.neo4j;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "JASentence")
public class JASentenceNeoEntity {
    @JsonProperty("id")
    public Integer id;

    @JsonProperty("ja")
    public String ja;

    @JsonProperty("vi")
    public String vi;
}