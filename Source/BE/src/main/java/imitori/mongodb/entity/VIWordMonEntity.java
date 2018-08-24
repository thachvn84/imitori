package imitori.mongodb.entity;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.neo4j.ogm.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import imitori.mongodb.entity.ENWordMonEntity.WordFormClass;

@Document(collection = "ViWords")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VIWordMonEntity {
    @Id
    public Integer id;

    @Field("word")
    public String word;

    @Field("tl")
    public String tl;

    @Field("ChuyenNganh")
    public String chuyennganh;

    @Field("LinhVuc")
    public String linhvuc;
    
    @Field("sentences")
    public ArrayList<Integer> sens_id;

    @Field("similarword")
    public ArrayList<Integer> similar_id;

    @Field("jatrans")
    public ArrayList<TransRelMonEntity> ja_id;

    @Field("entrans")
    public ArrayList<TransRelMonEntity> en_id;

    @Field("oppositeword")
    public ArrayList<Integer> opposite_id;

    @Field("relatedword")
    public ArrayList<Integer> related_id;

    @Field("subinfo")
    public ArrayList<String> subinfo;

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

    public VIWordMonEntity() {
        
    }


}