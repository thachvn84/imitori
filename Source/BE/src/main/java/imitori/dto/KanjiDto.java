package imitori.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KanjiDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("kanji")
    private Long kanji;

    @JsonProperty("hanviet")
    private ArrayList<String> hanviet;

    @JsonProperty("mean")
    private String mean;

    @JsonProperty("onyomi")
    private ArrayList<String> onyomi;

    @JsonProperty("kunyomi")
    private ArrayList<String> kunyomi;

    @JsonProperty("klass")
    private String klass;

    @JsonProperty("stroke")
    private Long stroke;

    @JsonProperty("wordlist")
    private ArrayList<JAWordDto> wordlist;
}