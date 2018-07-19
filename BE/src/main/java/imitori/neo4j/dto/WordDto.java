package imitori.neo4j.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WordDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("word")
    public String word;

    @JsonProperty("kana")
    public String kana;

    @JsonProperty("romaji")
    public String romaji;

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}