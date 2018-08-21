package imitori.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KanjiDto {
    @JsonProperty("id")
    public Long id;

    @JsonProperty("kanji")
    public String kanji;

    @JsonProperty("hanviet")
    public ArrayList<String> hanviet;

    @JsonProperty("mean")
    public String mean;

    @JsonProperty("onyomi")
    public ArrayList<String> onyomi;

    @JsonProperty("kunyomi")
    public ArrayList<String> kunyomi;

    @JsonProperty("klass")
    public String klass;

    @JsonProperty("stroke")
    public Integer stroke;

    @JsonProperty("wordlist")
    public ArrayList<JAWordDto> wordlist;

    public KanjiDto() {
        this.hanviet = new ArrayList<>();
        this.onyomi = new ArrayList<>();
        this.kunyomi = new ArrayList<>();
        this.wordlist = new ArrayList<>();
    }
}