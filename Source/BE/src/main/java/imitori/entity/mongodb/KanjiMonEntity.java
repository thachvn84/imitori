package imitori.entity.mongodb;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;


import org.neo4j.ogm.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Kanji")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KanjiMonEntity {
    @Id
    public Integer id;

    @Field("kanji")
    public String kanji;

    @Field("klass")
    public Integer klass;

    @Field("hanviet")
    public String hanviet;

    @Field("onyomi")
    public ArrayList<String> onyomi;

    @Field("kunyomi")
    public ArrayList<Kunyomi_Class> kunyomi;

    public class Kunyomi_Class {
        @Field("kun")
        public String kun;
        
        @Field("okuri")
        public String okuri;
    }

    @Field("jpltlevel")
    public String jlptlevel;
    
    @Field("noStroke")
    public Integer noStroke;

    @Field("mean")
    public String mean;

    @Field("relatedTo")
    ArrayList<Integer> related_to;

    @Field("oppositeTo")
    ArrayList<Integer> opposite_to;

    public KanjiMonEntity() {
    }

    public void updateFrom(KanjiMonEntity w) {
        this.kanji = w.kanji != null ? w.kanji : this.kanji;
        this.klass = w.klass != 0 ? w.klass : this.klass;
        this.hanviet = w.hanviet != null ? w.hanviet : this.hanviet;
        this.jlptlevel = w.jlptlevel != null ? w.jlptlevel : this.jlptlevel;
        this.noStroke = w.noStroke != 0 ? w.noStroke : this.noStroke;
        this.mean = w.mean != null ? w.mean : this.mean;
        
        if (w.onyomi != null) {
            if (this.onyomi != null) {
                for (int i = 0; i < w.onyomi.size(); i++) {
                    if (!this.onyomi.contains(w.onyomi.get(i))) {
                        this.onyomi.add(w.onyomi.get(i));
                    }
                }
            } else {
                this.onyomi = new ArrayList<>();
                this.onyomi = w.onyomi;
            }
        }
        if (w.kunyomi != null) {
            if (this.kunyomi != null) {
                for (int i = 0; i < w.kunyomi.size(); i++) {
                    boolean found_kun = false;
                    for (int j = 0; j < this.kunyomi.size(); j++) {
                        if (this.kunyomi.get(j).kun.equals(w.kunyomi.get(i).kun)) {
                            found_kun = true;
                            this.kunyomi.get(j).okuri = w.kunyomi.get(i).okuri;
                            break;
                        }
                    }
                    if (!found_kun) {
                        this.kunyomi.add(w.kunyomi.get(i));
                    }
                }
            } else {
                this.kunyomi = new ArrayList<>();
                this.kunyomi = w.kunyomi;
            }
        }
        if (w.related_to != null) {
            if (this.related_to != null) {
                for (int i = 0; i < w.related_to.size(); i++) {
                    if (!this.related_to.contains(w.related_to.get(i))) {
                        this.related_to.add(w.related_to.get(i));
                    }
                }
            } else {
                this.related_to = w.related_to;
            }
        }
        if (w.opposite_to != null) {
            if (this.opposite_to != null) {
                for (int i = 0; i < w.opposite_to.size(); i++) {
                    if (!this.opposite_to.contains(w.opposite_to.get(i))) {
                        this.opposite_to.add(w.opposite_to.get(i));
                    }
                }
            } else {
                this.opposite_to = w.opposite_to;
            }
        }
    }


}