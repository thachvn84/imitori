package imitori.neo4j.dto;

import java.util.Dictionary;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class FullWordDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("word")
    public String word;

    @JsonProperty("spell")
    public String spell;

    @JsonProperty("means")
    public List<TlMean> means;

    @JsonProperty("fieldmeans")
    public List<FieldMean> fieldMeans;

    
    public static class TlMean {
        @JsonProperty("tl")
        public String tl;

        @JsonProperty("means")
        public List<TlSubMean> means;

        @JsonProperty("sidewords")
        public List<SideWord> sidewords;
    }

    public static class FieldMean {
        @JsonProperty("name")
        public String name;

        @JsonProperty("means")
        public List<String> means;

    }

    public static class TlSubMean {
        @JsonProperty("mean")
        public String mean;

        @JsonProperty("phrases")
        public List<Phrase> phrases;
    }

    public static class Phrase {
        @JsonProperty("phrase")
        public String phrase;

        @JsonProperty("mean")
        public String mean;
    }
    
    public static class SideWord {
        @JsonProperty("word")
        public String word;

        @JsonProperty("means")
        public List<TlSubMean> means;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return this.id;
    }
    public FullWordDto() {

    }
}