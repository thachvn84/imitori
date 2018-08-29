package imitori.entity.mongodb;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.neo4j.ogm.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "JaViDic")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JAVIDicMonEntity {
    @Id
    public Integer id;

    @Field("word")
    public String word;

    @Field("furigana")
    public String furigana;

    @Field("tl")
    public String tl;

    @Field("means")
    public ArrayList<ViWordClass> means;

    public static class ViWordClass {
        @Field("word")
        public String word;

        @Field("tl")
        public String tl;

        @Field("chuyennganh")
        public String chuyennganh;

        @Field("linhvuc")
        public String linhvuc;

        @Field("sentences")
        public ArrayList<SentenceClass> sentences;

        @Field("similarto")
        public ArrayList<String> similarword;

        @Field("oppositeto")
        public ArrayList<String> oppositeword;

        @Field("relatedto")
        public ArrayList<String> relatedword;
    }

    public static class SentenceClass {
        @Field("ja")
        public String ja;

        @Field("vi")
        public String vi;
    }

}