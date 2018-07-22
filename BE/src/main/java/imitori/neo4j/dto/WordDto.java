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

    @JsonProperty("mean")
    public String mean;

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public WordDto(Long id, String word, String kana, String romaji, String mean) {
        this.id = id;
        this.word = word;
        this.kana = kana;
        this.romaji = romaji;
        this.mean = mean;
    }
}