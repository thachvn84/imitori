package imitori.mongodb.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ViJaWords")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VIJAWordEntity {
}