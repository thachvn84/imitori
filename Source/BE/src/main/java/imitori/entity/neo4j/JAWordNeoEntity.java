package imitori.entity.neo4j;

import java.util.ArrayList;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import imitori.utils.JapaneseCharacter;

@NodeEntity(label = "JAWord")
public class JAWordNeoEntity {
    @Id
    @GeneratedValue
    private Integer id;
    public String word;
    public String furigana;
    public String romaji;
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

    @Relationship(type = "HAS_EXAMPLE", direction = Relationship.OUTGOING)
    private ArrayList<HasExampleRelNeoEntity> hasExample = new ArrayList<>();

    public JAWordNeoEntity() {
    }

    String toRomaji(String furi) {
        String res = "";
        for (int i = 0; i < furi.length(); i++)
        {
            res += JapaneseCharacter.toRomaji(furi.charAt(i));
        } 
        return res;

    }

    public JAWordNeoEntity(Integer id, String word, String furi, String tl) {
        this.id = id;
        this.word = word;
        this.furigana = furi;
        this.romaji = toRomaji(furi);
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

    public ArrayList<HasExampleRelNeoEntity> getHasExample() {
        return this.hasExample;
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