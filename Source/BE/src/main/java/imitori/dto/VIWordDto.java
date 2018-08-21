package imitori.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VIWordDto {
    @JsonProperty("id")
    public Integer id;

    @JsonProperty("word")
    public String word;

    @JsonProperty("tl")
    public String tl;

    public VIWordDto() {
        
    }

    public VIWordDto(Integer id, String w, String tl) {
        this.id = id;
        this.word = w;
        this.tl = tl;
    }
}