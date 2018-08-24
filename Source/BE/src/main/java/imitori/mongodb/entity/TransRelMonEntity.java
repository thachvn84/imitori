package imitori.mongodb.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="TransRel")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransRelMonEntity {
    @Id
    public Integer id;

    @Field("To")
    public Integer to;

    @Field("score")
    public Integer score;
}