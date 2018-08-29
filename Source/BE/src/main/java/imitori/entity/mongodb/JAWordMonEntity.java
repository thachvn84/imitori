package imitori.entity.mongodb;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.neo4j.ogm.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "JaWords")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JAWordMonEntity {
    @Id
    public Integer id;

    @Field("word")
    public String word;

    @Field("furi")
    public String furi;

    @Field("romaji")
    public String romaji;

    @Field("tl")
    public String tl;

    @Field("chuyennganh")
    public String chuyennganh;

    @Field("linhvuc")
    public String linhvuc;

    @Field("accent")    
    public String accent;

    @Field("jlptlevel")
    public String jlptlevel;

    @Field("sentences")
    public ArrayList<Integer> sens_id;

    @Field("similarword")
    public ArrayList<Integer> similar_id;

    @Field("entrans")
    public ArrayList<TransRelMonEntity> en_id;

    @Field("vitrans")
    public ArrayList<TransRelMonEntity> vi_id;

    @Field("oppositeoword")
    public ArrayList<Integer> opposite_id;

    @Field("relatedword")
    public ArrayList<Integer> related_id;

    @Field("subinfo")
    public ArrayList<String> subInfo;

    @Field("form")
    public ArrayList<WordFormClass> form;

    public static class WordFormClass {
        @Field("name")
        public String name;

        @Field("word")
        public String word;

        @Field("sentences")
        public ArrayList<Integer> snes_id;
    }

    public JAWordMonEntity() {

    }


}