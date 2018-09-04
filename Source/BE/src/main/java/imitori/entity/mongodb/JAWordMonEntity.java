package imitori.entity.mongodb;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.neo4j.ogm.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import imitori.utils.MongoUtils;

@Document(collection = "JaWords")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JAWordMonEntity {
    @Id
    public Integer id;

    @Field("word")
    public String word;

    @Field("furi")
    public String furi;

    @Field("romaji")
    public String romaji;

    @Field("tl")
    public String tl;

    @Field("chuyennganh")
    public String chuyennganh;

    @Field("linhvuc")
    public String linhvuc;

    @Field("accent")    
    public String accent;

    @Field("jlptlevel")
    public String jlptlevel;

    @Field("sentences")
    public ArrayList<Integer> sens_id;

    @Field("similarword")
    public ArrayList<SimilarRelMonEntity> similar_id;

    @Field("entrans")
    public ArrayList<TransRelMonEntity> en_id;

    @Field("vitrans")
    public ArrayList<TransRelMonEntity> vi_id;

    @Field("oppositeoword")
    public ArrayList<OppositeRelMonEntity> opposite_id;

    @Field("relatedword")
    public ArrayList<RelatedRelMonEntity> related_id;

    @Field("subinfo")
    public ArrayList<String> subInfo;

    @Field("form")
    public ArrayList<WordFormClass> form;

    public static class WordFormClass {
        @Field("name")
        public String name;

        @Field("word")
        public String word;

        @Field("sentences")
        public ArrayList<Integer> sens_id;
    }

    public JAWordMonEntity() {

    }

    public void updateFrom(JAWordMonEntity w) {
        this.word = w.word != null ? w.word : this.word;
        this.furi = w.furi != null ? w.furi : this.furi;
        this.romaji = w.romaji != null ? w.romaji : this.romaji;
        this.tl = w.tl != null ? w.tl : this.tl;
        this.chuyennganh = w.chuyennganh != null ? w.chuyennganh : this.chuyennganh;
        this.linhvuc = w.linhvuc != null ? w.linhvuc : this.linhvuc;
        this.accent = w.accent != null ? w.accent : this.accent;
        this.jlptlevel = w.jlptlevel != null ? w.jlptlevel : this.jlptlevel;

        /* Sentence */
        if (w.sens_id != null) {
            if (this.sens_id != null) {
                for (int i = 0; i < w.sens_id.size(); i++) {
                    if (!this.sens_id.contains(w.sens_id.get(i))) {
                        this.sens_id.add(w.sens_id.get(i));
                    }
                }
            } else {
                this.sens_id = new ArrayList<>();
                this.sens_id = w.sens_id;
            }
        }

        /* Similar Word */
        if (w.similar_id != null) {
            this.similar_id = MongoUtils.mergeSimRel(this.similar_id, w.similar_id);
        }

        /* Opposite word*/
        if (w.opposite_id != null) {
            this.opposite_id = MongoUtils.mergeOppRel(this.opposite_id, w.opposite_id);
        }

        /* Related word */
        if (w.related_id != null) {
            this.related_id = MongoUtils.mergeRelRel(this.related_id, w.related_id);
        }

        /* Sub info */
        if (w.subInfo != null) {
            if (this.subInfo != null) {
                for (int i = 0; i < w.subInfo.size(); i++) {
                    if (!this.subInfo.contains(w.subInfo.get(i))) {
                        this.subInfo.add(w.subInfo.get(i));
                    }
                }
            } else {
                this.subInfo = new ArrayList<>();
                this.subInfo = w.subInfo;
            }
        }

        /* En Trans */
        if (w.en_id != null) {
            this.en_id = MongoUtils.mergeTransRel(this.en_id, w.en_id);
        }
        /* Vi Trans */
        if (w.vi_id != null) {
            this.vi_id = MongoUtils.mergeTransRel(this.vi_id, w.vi_id);
        }

        /* Word Form */
        if (w.form != null) {
            if (this.form != null) {
                for (int i = 0; i < w.form.size(); i++) {
                    /*
                      Check if current form is contain form.name or not
                      The algorithm is when name is duplicate, replace the word
                      Then add the sentence (if not duplicate)
                    */
                    boolean form_found = false;
                    for (int j = 0; j < this.form.size(); j++) {
                        if (this.form.get(j).name.equals(w.form.get(i).name)) {
                            form_found = true;
                            //Found! then check the word and sentences
                            this.form.get(j).word = w.form.get(i).word;
                            if (w.form.get(i).sens_id != null) {
                                for (int k = 0; k < w.form.get(i).sens_id.size(); k++) {
                                    // Check if there is a duplication.
                                    if (!this.form.get(j).sens_id.contains(w.form.get(i).sens_id.get(k))) {
                                        this.form.get(j).sens_id.add(w.form.get(i).sens_id.get(k));
                                    }
                                }
                            }
                        }
                    }
                    if (!form_found) {
                        this.form.add(w.form.get(i));
                    }
                }
            } else {
                this.form = new ArrayList<>();
                this.form = w.form;
            }
        }

    }


}