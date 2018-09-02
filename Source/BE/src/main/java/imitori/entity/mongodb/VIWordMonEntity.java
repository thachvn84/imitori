package imitori.entity.mongodb;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.neo4j.ogm.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "ViWords")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VIWordMonEntity {
    @Id
    public Integer id;

    @Field("word")
    public String word;

    @Field("tl")
    public String tl;

    @Field("ChuyenNganh")
    public String chuyennganh;

    @Field("LinhVuc")
    public String linhvuc;
    
    @Field("sentences")
    public ArrayList<Integer> sens_id;

    @Field("similarword")
    public ArrayList<Integer> similar_id;

    @Field("jatrans")
    public ArrayList<TransRelMonEntity> ja_id;

    @Field("entrans")
    public ArrayList<TransRelMonEntity> en_id;

    @Field("oppositeword")
    public ArrayList<Integer> opposite_id;

    @Field("relatedword")
    public ArrayList<Integer> related_id;

    @Field("subinfo")
    public ArrayList<String> subinfo;

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

    public VIWordMonEntity() {
        
    }

    public void updateFrom(VIWordMonEntity w) {
        this.word = w.word == null ? w.word : this.word;
        this.tl = w.tl == null ? w.tl : this.tl;
        this.chuyennganh = w.chuyennganh == null ? w.chuyennganh : this.chuyennganh;
        this.linhvuc = w.linhvuc == null ? w.linhvuc : this.linhvuc;
        
        if (w.sens_id != null) {
            if (this.sens_id != null) {
                for (int i = 0; i < w.sens_id.size(); i++) {
                    if(!this.sens_id.contains(w.sens_id.get(i))) {
                        this.sens_id.add(w.sens_id.get(i));
                    }
                }
            } else {
                this.sens_id = new ArrayList<>();
                this.sens_id = w.sens_id;
            }
        }

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

        if (w.subinfo != null) {
            if (this.subinfo != null) {
                for (int i = 0; i < w.subinfo.size(); i++) {
                    if (!this.subinfo.contains(w.subinfo.get(i))) {
                        this.subinfo.add(w.subinfo.get(i));
                    }
                }
            } else {
                this.subinfo = new ArrayList<>();
                this.subinfo = w.subinfo;
            }
        }

        //Transrel ja_id
        if (w.ja_id != null) {
            if (this.ja_id != null) {
                for (int i = 0; i < w.ja_id.size(); i++) {
                    // Check if To is same or not, if not, add. 
                    boolean found_to = false;
                    for (int j = 0; j < this.ja_id.size(); j++) {
                        if (this.ja_id.get(j).to == w.ja_id.get(i).to) {
                            found_to = true;
                            this.ja_id.get(j).score = w.ja_id.get(i).score;
                            break;
                        }
                    }
                    if (!found_to) {
                        this.ja_id.add(w.ja_id.get(i));
                    }
                }
            } else {
                this.ja_id = new ArrayList<>();
                this.ja_id = w.ja_id;
            }
        }

        //Transrel en_id
        if (w.en_id != null) {
            if (this.en_id != null) {
                for (int i = 0; i < w.en_id.size(); i++) {
                    // Check if To is same or not, if not, add.
                    boolean found_to = false;
                    for (int j = 0; j < this.en_id.size(); j++) {
                        found_to = false;
                        if (this.en_id.get(j).to == w.en_id.get(i).to) {
                            found_to = true;
                            this.en_id.get(j).score = w.en_id.get(i).score;
                            break;
                        }
                    }
                    if (!found_to) {
                        this.en_id.add(w.en_id.get(i));
                    }
                }
            } else {
                this.en_id = new ArrayList<>();
                this.en_id = w.en_id;
            }
        }

        //form
        if (w.form != null) {
            if (this.form != null) {
                for (int i = 0; i < w.form.size(); i++) {
                    boolean found_name = false;
                    for (int j = 0; j < this.form.size(); j++) {
                        if (this.form.get(j).name.equals(w.form.get(i).name)) {
                            found_name = true;
                            this.form.get(j).word = w.form.get(i).word;
                            if (w.form.get(i).sens_id != null) {
                                if (this.form.get(j).sens_id != null) {
                                    for (int k = 0; k < w.form.get(i).sens_id.size(); k++) {
                                        if (!this.form.get(j).sens_id.contains(w.form.get(i).sens_id.get(k))) {
                                            this.form.get(j).sens_id.add(w.form.get(i).sens_id.get(k));
                                        }
                                    }
                                } else {
                                    this.form.get(j).sens_id = w.form.get(i).sens_id;
                                }
                            }
                        }
                    }
                    if (!found_name) {
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