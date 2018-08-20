package imitori.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SentenceDto {
    @JsonProperty("id")
    public Long id;

    @JsonProperty("ja")
    public String ja;

    @JsonProperty("vi")
    public String vi;
}