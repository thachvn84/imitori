package imitori.dto.admin;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JAWordDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("word")
    private String word;

    @JsonProperty("kanji")
    private ArrayList<KanjiDto> kanji;

    @JsonProperty("furigana")
    private String furigana;

    @JsonProperty("romaji")
    private String romaji;

    @JsonProperty("tl")
    private String tl;

    @JsonProperty("sentence")
    ArrayList<SentenceDto> example;

    @JsonProperty("similarword")
    ArrayList<JAWordDto> similarword;

    @JsonProperty("transword")
    ArrayList<VIWordDto> transword;

    @JsonProperty("oppositeword")
    ArrayList<JAWordDto> oppositeword;

    @JsonProperty("relatedword")
    ArrayList<JAWordDto> relatedword;


}