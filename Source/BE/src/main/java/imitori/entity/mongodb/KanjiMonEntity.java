package imitori.entity.mongodb;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;


import org.neo4j.ogm.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Kanji")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KanjiMonEntity {
    @Id
    public Integer id;

    @Field("kanji")
    public String kanji;

    @Field("klass")
    public Integer klass;

    @Field("hanviet")
    public String hanviet;

    @Field("onyomi")
    public ArrayList<String> onyomi;

    @Field("kunyomi")
    public ArrayList<String> kunyomi;

    @Field("jpltlevel")
    public String jlptlevel;
    
    @Field("noStroke")
    public Integer noStroke;

    @Field("mean")
    public String mean;

    @Field("relatedTo")
    ArrayList<Integer> related_to;

    @Field("oppositeTo")
    ArrayList<Integer> opposite_to;

}