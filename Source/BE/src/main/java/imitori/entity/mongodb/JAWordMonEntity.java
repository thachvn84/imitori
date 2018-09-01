package imitori.entity.mongodb;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.neo4j.ogm.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
    public ArrayList<Integer> similar_id;

    @Field("entrans")
    public ArrayList<TransRelMonEntity> en_id;

    @Field("vitrans")
    public ArrayList<TransRelMonEntity> vi_id;

    @Field("oppositeoword")
    public ArrayList<Integer> opposite_id;

    @Field("relatedword")
    public ArrayList<Integer> related_id;

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
            if (this.similar_id != null) {
                for (int i = 0; i < w.similar_id.size(); i++) {
                    if (!this.similar_id.contains(w.similar_id.get(i))) {
                        this.similar_id.add(w.similar_id.get(i));
                    }
                }
            } else {
                this.similar_id = new ArrayList<>();
                this.similar_id = w.similar_id;
            }
        }

        /* Opposite word*/
        if (w.opposite_id != null) {
            if (this.opposite_id != null) {
                for (int i = 0; i < w.opposite_id.size(); i++) {
                    if (!this.opposite_id.contains(w.opposite_id.get(i))) {
                        this.opposite_id.add(w.opposite_id.get(i));
                    }
                }
            } else {
                this.opposite_id = new ArrayList<>();
                this.opposite_id = w.opposite_id;
            }
        }

        /* Related word */
        if (w.related_id != null) {
            if (this.related_id != null) {
                for (int i = 0; i < w.related_id.size(); i++) {
                    if (!this.related_id.contains(w.related_id.get(i))) {
                        this.related_id.add(w.related_id.get(i));
                    }
                }
            } else {
                this.related_id = new ArrayList<>();
                this.related_id = w.related_id;
            }
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
            if (this.en_id != null) {
                for (int i = 0; i < w.en_id.size(); i++) {
                    // Check if current en_id list is contain w.to or not
                    boolean trans_found = false;
                    for (int j = 0; j < this.en_id.size(); j++) {
                        if (w.en_id.get(i).to == this.en_id.get(j).to) {
                            this.en_id.get(j).score = w.en_id.get(i).score;
                            trans_found = true;
                            break;
                        }
                    }
                    if (!trans_found) {
                        this.en_id.add(w.en_id.get(i));
                    }
                }
            } else {
                this.en_id = new ArrayList<>();
                this.en_id = w.en_id;
            }
        }
        /* Vi Trans */
        if (w.vi_id != null) {
            if (this.vi_id != null) {
                for (int i = 0; i < w.vi_id.size(); i++) {
                    //Check if current vi_id list is contain w.to or not
                    boolean trans_found = false;
                    for (int j = 0; j < this.vi_id.size(); j++) {
                        if (w.vi_id.get(i).to == this.vi_id.get(j).to) {
                            this.vi_id.get(j).score = w.en_id.get(i).score;
                            trans_found = true;
                            break;
                        }
                    }
                    if (!trans_found) {
                        this.vi_id.add(w.vi_id.get(i));
                    }
                }
            }
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