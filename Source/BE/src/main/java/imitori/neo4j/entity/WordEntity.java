package imitori.neo4j.entity;

import java.util.ArrayList;

import imitori.neo4j.entity.SimilarToRelEntity;
import imitori.neo4j.entity.TranslateToRelEntity;
import imitori.neo4j.entity.RelatedToRelEntity;
import imitori.neo4j.entity.OppositeToRelEntity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "Word")
public class WordEntity {
    @Id
    @GeneratedValue
    private Long id;

    public String word;
    public ArrayList<String> spell;
    public String lang;
    
    //Depend on "lang", the id should query from JA/EN/ or even VN mongoDict
    public Long mongoId;

    @Relationship(type = "SIMILAR_TO", direction = Relationship.OUTGOING)
    private ArrayList<SimilarToRelEntity> similarTo = new ArrayList<>();

    @Relationship(type = "SIMILAR_TO", direction = Relationship.INCOMING)
    private ArrayList<SimilarToRelEntity> similarFrom = new ArrayList<>();

    @Relationship(type = "TRANSLATE_TO", direction = Relationship.OUTGOING)
    private ArrayList<TranslateToRelEntity> translateTo = new ArrayList<>();

    @Relationship(type = "TRANSLATE_TO", direction = Relationship.INCOMING)
    private ArrayList<TranslateToRelEntity> translateFrom = new ArrayList<>();

    @Relationship(type = "RELATED_TO", direction = Relationship.OUTGOING)
    private ArrayList<RelatedToRelEntity> relatedTo = new ArrayList<>();

    @Relationship(type = "RELATED_TO", direction = Relationship.INCOMING)
    private ArrayList<RelatedToRelEntity> relatedFrom = new ArrayList<>();

    @Relationship(type = "OPPOSITE_TO", direction = Relationship.OUTGOING)
    private ArrayList<OppositeToRelEntity> oppositeTo = new ArrayList<>();

    @Relationship(type = "OPPOSITE_TO", direction = Relationship.INCOMING)
    private ArrayList<OppositeToRelEntity> oppisteFrom = new ArrayList<>();

    public WordEntity() {
    }

    public WordEntity(Long id, String word, ArrayList<String> spell, String lang) {
        this.id = id;
        this.word = word;
        this.spell = spell;
        this.lang = lang;
    }

    public WordEntity(Long id, String word, String spell, String lang) {
        this.id = id;
        this.word = word;
        this.spell = new ArrayList<String>();
        this.spell.add(spell);
        this.lang = lang;
    }

    public Long getId() {
        return id;
    }

    public ArrayList<SimilarToRelEntity> getSimilarTo() {
        return this.similarTo;
    }

    public ArrayList<SimilarToRelEntity> getSimilarFrom() {
        return this.similarFrom;
    }

    public ArrayList<TranslateToRelEntity> getTranslateTo() {
        return this.translateTo;
    }

    public ArrayList<TranslateToRelEntity> getTranslateFrom() {
        return this.translateFrom;
    }

    public ArrayList<RelatedToRelEntity> getRelatedTo() {
        return this.relatedTo;
    }

    public ArrayList<RelatedToRelEntity> getRelatedFrom() {
        return this.relatedFrom;
    }

    public ArrayList<OppositeToRelEntity> getOppositeTo() {
        return this.oppositeTo;
    }

    public ArrayList<OppositeToRelEntity> getOppositeFrom() {
        return this.oppisteFrom;
    }

    @Override
    public String toString() {
        String res = new String();
        /*
            WordEntity {
                id: 00000
                word: A
                spell: [A1, A2],
                lang : EN
                similarTo: [Id1, Id2],
                similarFrom: [Id3, Id4],
                TranslateTo: [Id5, Id6],
                TranslateFrom: [Id7, Id8],
                RelatedTo: [Id9],
                RelatedFrom: [Id10],
                OppositeTo: [Id11],
                OppositeFrom: [Id12]
            }
        */

        return res;
    }
}