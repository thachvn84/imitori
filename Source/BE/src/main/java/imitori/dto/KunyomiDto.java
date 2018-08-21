package imitori.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KunyomiDto {
    @JsonProperty("kun")
    public String kun;

    @JsonProperty("okuri")
    public String okuri;

    public KunyomiDto(String k, String o) {
        this.kun = k;
        this.okuri = o;
    }
}