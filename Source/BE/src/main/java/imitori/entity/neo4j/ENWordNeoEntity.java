package imitori.entity.neo4j;

import java.util.ArrayList;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import imitori.utils.JapaneseCharacter;

@NodeEntity(label = "ENWord")
public class ENWordNeoEntity {
    @Id
    @GeneratedValue
    private Integer id;
    public String word;
    public String spell;
    public String tl;
    
    //Depend on "lang", the id should query from JA/EN/ or even VN mongoDict
    public Integer mongoId;

    @Relationship(type = "SIMILAR_TO", direction = Relationship.OUTGOING)
    private ArrayList<SimilarToRelNeoEntity> similarTo = new ArrayList<>();

    @Relationship(type = "SIMILAR_TO", direction = Relationship.INCOMING)
    private ArrayList<SimilarToRelNeoEntity> similarFrom = new ArrayList<>();

    @Relationship(type = "TRANSLATE_TO", direction = Relationship.OUTGOING)
    private ArrayList<TranslateToRelNeoEntity> translateTo = new ArrayList<>();

    @Relationship(type = "TRANSLATE_TO", direction = Relationship.INCOMING)
    private ArrayList<TranslateToRelNeoEntity> translateFrom = new ArrayList<>();

    @Relationship(type = "RELATED_TO", direction = Relationship.OUTGOING)
    private ArrayList<RelatedToRelNeoEntity> relatedTo = new ArrayList<>();

    @Relationship(type = "RELATED_TO", direction = Relationship.INCOMING)
    private ArrayList<RelatedToRelNeoEntity> relatedFrom = new ArrayList<>();

    @Relationship(type = "OPPOSITE_TO", direction = Relationship.OUTGOING)
    private ArrayList<OppositeToRelNeoEntity> oppositeTo = new ArrayList<>();

    @Relationship(type = "OPPOSITE_TO", direction = Relationship.INCOMING)
    private ArrayList<OppositeToRelNeoEntity> oppisteFrom = new ArrayList<>();

    @Relationship(type = "HAS_SENTENCES", direction = Relationship.OUTGOING)
    private ArrayList<SensRelNeoEntity> sentences = new ArrayList<>();

    public ENWordNeoEntity() {
    }

    public Integer getId() {
        return id;
    }

    public ArrayList<SimilarToRelNeoEntity> getSimilarTo() {
        return this.similarTo;
    }

    public ArrayList<SimilarToRelNeoEntity> getSimilarFrom() {
        return this.similarFrom;
    }

    public ArrayList<TranslateToRelNeoEntity> getTranslateTo() {
        return this.translateTo;
    }

    public ArrayList<TranslateToRelNeoEntity> getTranslateFrom() {
        return this.translateFrom;
    }

    public ArrayList<RelatedToRelNeoEntity> getRelatedTo() {
        return this.relatedTo;
    }

    public ArrayList<RelatedToRelNeoEntity> getRelatedFrom() {
        return this.relatedFrom;
    }

    public ArrayList<OppositeToRelNeoEntity> getOppositeTo() {
        return this.oppositeTo;
    }

    public ArrayList<OppositeToRelNeoEntity> getOppositeFrom() {
        return this.oppisteFrom;
    }

    public ArrayList<SensRelNeoEntity> getSentences() {
        return this.sentences;
    }
    @Override
    public String toString() {
        String res = new String();

        return res;
    }
}