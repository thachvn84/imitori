package imitori.neo4j.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SimilarToRelDto {

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

    public SimilarToRelDto(Long id) {
        this.id = id;
    }
}