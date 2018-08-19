package imitori.mongodb.entity;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.neo4j.ogm.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import imitori.utils.StringUtils;

@Document(collection = "ViJaWords")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VIJAWordEntity {
}