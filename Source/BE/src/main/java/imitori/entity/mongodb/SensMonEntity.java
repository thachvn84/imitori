package imitori.entity.mongodb;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Sentences")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SensMonEntity {
    @Id
    public Integer id;

    @Field("ja")
    public String ja;

    @Field("en")
    public String en;

    @Field("vi")
    public String vi;

}