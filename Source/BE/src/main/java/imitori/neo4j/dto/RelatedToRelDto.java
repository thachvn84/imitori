package imitori.neo4j.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RelatedToRelDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("id1")
    public Integer id1;

    @JsonProperty("id2")
    public Integer id2;

    @JsonProperty("score")
    public Integer score;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RelatedToRelDto(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Related {id: " + id + ", fromId: " + id1 + ", toId: " + id2 + "}";
    }
}