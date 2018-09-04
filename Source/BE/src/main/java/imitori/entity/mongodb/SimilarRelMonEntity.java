package imitori.entity.mongodb;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "SimilarRel")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimilarRelMonEntity {
    @Id
    public Integer id;

    @Field("from")
    public Integer from;

    @Field("to")
    public Integer to;

    @Field("score")
    public Integer score;
}