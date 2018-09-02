package imitori.entity.mongodb;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "KanjiClass")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KlassMonEntity {
    @Id
    public Integer id;

    @Field("klass")
    public String klass;

    @Field("hanviet")
    public String hanviet;

    @Field("mean")
    public String mean;

    public KlassMonEntity() {

    }

    public void updateFrom(KlassMonEntity w) {
        this.klass = w.klass;
        this.hanviet = w.hanviet;
        this.mean = w.mean;
    }


}