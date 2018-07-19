package imitori.neo4j.entity;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "IN_LANG")
public class InLangRelEntity {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private WordEntity startWord;

    @EndNode
    private LanguageEntity endLanguage;

    public Long getId() {
        return id;
    }

    public WordEntity getStartWord() {
        return startWord;
    }

    public void setStartWord(WordEntity w) {
        this.startWord = w;
    }

    public LanguageEntity getEndLanguage() {
        return endLanguage;
    }

    public void setEndLanguage(LanguageEntity l) {
        this.endLanguage = l;
    }
}