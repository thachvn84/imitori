package imitori.mongodb.entity;

import java.util.ArrayList;

import org.neo4j.ogm.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class JAVIDicMonEntity {
    @Id
    private Integer id;

    @Field("word")
    public String word;

    @Field("means")
    public ArrayList<ViWordClass> means;

    @Field("sentences")
    public ArrayList<SentenceClass> sentences;

    public static class ViWordClass {

    }

    public static class SentenceClass {
        
    }

}