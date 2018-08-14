package imitori.neo4j.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OppositeToRelDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("id1")
    public Long id1;

    @JsonProperty("id2")
    public Long id2;

    @JsonProperty("score")
    public Integer score;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OppositeToRelDto(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Opposite {id: " + id + ", fromId: " + id1 + ", toId: " + id2 + "}";
    }
}