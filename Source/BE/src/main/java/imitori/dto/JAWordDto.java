package imitori.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JAWordDto {
    @JsonProperty("id")
    public Integer id;

    @JsonProperty("word")
    public String word;

    @JsonProperty("kanji")
    public ArrayList<KanjiDto> kanji;

    @JsonProperty("furigana")
    public String furigana;

    @JsonProperty("romaji")
    public String romaji;

    @JsonProperty("tl")
    public String tl;

    @JsonProperty("sentence")
    public ArrayList<SentenceDto> example;

    @JsonProperty("similarword")
    public ArrayList<JAWordDto> similarword;

    @JsonProperty("transword")
    public ArrayList<VIWordDto> transword;

    @JsonProperty("oppositeword")
    public ArrayList<JAWordDto> oppositeword;

    @JsonProperty("relatedword")
    public  ArrayList<JAWordDto> relatedword;

    public JAWordDto() {
        this.kanji = new ArrayList<>();
        this.example = new ArrayList<>();
        this.similarword = new ArrayList<>();
        this.transword = new ArrayList<>();
        this.oppositeword = new ArrayList<>();
        this.relatedword = new ArrayList<>();
    }

    public JAWordDto(String w) {
        this.word = w;
        this.kanji = new ArrayList<>();
        this.example = new ArrayList<>();
        this.similarword = new ArrayList<>();
        this.transword = new ArrayList<>();
        this.oppositeword = new ArrayList<>();
        this.relatedword = new ArrayList<>();
    }
}