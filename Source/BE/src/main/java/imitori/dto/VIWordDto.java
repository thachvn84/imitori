package imitori.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VIWordDto {
    @JsonProperty("id")
    public Long id;

    @JsonProperty("word")
    public String word;

    @JsonProperty("tl")
    public String tl;
}