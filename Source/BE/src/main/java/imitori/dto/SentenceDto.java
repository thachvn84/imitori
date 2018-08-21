package imitori.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SentenceDto {
    @JsonProperty("id")
    public Integer id;

    @JsonProperty("ja")
    public String ja;

    @JsonProperty("vi")
    public String vi;

    public SentenceDto() {

    }
    
    public SentenceDto(Integer id, String ja, String vi) {
        this.id = id;
        this.ja = ja;
        this.vi = vi;
    }
 
}