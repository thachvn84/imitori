package imitori.neo4j.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WordDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("word")
    public String word;

    @JsonProperty("spell")
    public ArrayList<String> spell;

    @JsonProperty("lang")
    public String lang;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WordDto(Long id, String word, ArrayList<String> spell, String lang) {
        this.id = id;
        this.word = word;
        this.spell = spell;
        this.lang = lang;
    }
}