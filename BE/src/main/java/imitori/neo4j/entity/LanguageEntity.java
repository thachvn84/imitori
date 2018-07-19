package imitori.neo4j.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.annotation.TypeAlias;

@NodeEntity
@TypeAlias("Language")
public class LanguageEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String language;

    public LanguageEntity() {
        
    }

    public LanguageEntity(String l) {
        this.language = l;
    }

    public Long getId() {
        return id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String name) {
        this.language = name;
    }
}