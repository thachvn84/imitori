package imitori.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KanjiClassDto {
    @JsonProperty("id")
    public Integer id;

    @JsonProperty("klass")
    public String klass;

    @JsonProperty("mean")
    public String mean;

    public KanjiClassDto(Integer id, String k, String m) {
        this.id = id;
        this.klass = k;
        this.mean = m;
    }
}