package imitori.mongodb.entity;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.neo4j.ogm.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "EnWords")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ENWordMonEntity {
    @Id
    public Integer id;

    @Field("word")
    public String word;

    @Field("spell")
    public String spell;

    @Field("tl")
    public String tl;

    @Field("sentences")
    public ArrayList<Integer> sens_id;

    @Field("similarword")
    public ArrayList<Integer> similar_id;

    @Field("jatrans")
    public ArrayList<TransRelMonEntity> ja_id;

    @Field("vitrans")
    public ArrayList<TransRelMonEntity> vi_id;

    @Field("oppositeword")
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
        public ArrayList<Integer> sens_id;
        
    }

    public ENWordMonEntity() {
        
    }


}