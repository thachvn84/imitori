package imitori.entity.neo4j;

import java.util.ArrayList;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label="Word")
public class WordNeoEntity {
    @Id
    @GeneratedValue
    private Integer id;

    public String word;
    public String spell;
    public Integer tl;

    /* wordtype: to detect which type of this word:
        NEO_JA_TYPE: JAWord
        NEO_EN_TYPE: ENWord
        NEO_VI_TYPE: VIWord
        Depend on type, will query corresponding MongoDB collection
        to get word information
    */
    public Integer wordtype; 

    /* The ID of Mongo Collection */
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

    public WordNeoEntity() {
    }

    public WordNeoEntity(Integer id, String word, String spell, Integer tl) {
        this.id = id;
        this.word = word;
        this.spell = spell;
        this.tl = tl;
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

    public ArrayList<SensRelNeoEntity> getHasExample() {
        return this.sentences;
    }
    @Override
    public String toString() {
        String res = new String();

        return res;
    }
}