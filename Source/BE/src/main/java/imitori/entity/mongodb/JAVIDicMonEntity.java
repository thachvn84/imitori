package imitori.entity.mongodb;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.neo4j.ogm.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "JaViDic")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JAVIDicMonEntity {
    @Id
    public Integer id;

    @Field("word")
    public String word;

    @Field("furigana")
    public String furigana;

    @Field("tl")
    public String tl;

    @Field("means")
    public ArrayList<ViWordClass> means;

    public static class ViWordClass {
        @Field("word")
        public String word;

        @Field("tl")
        public String tl;

        @Field("chuyennganh")
        public String chuyennganh;

        @Field("linhvuc")
        public String linhvuc;

        @Field("sentences")
        public ArrayList<SentenceClass> sentences;

        @Field("similarto")
        public ArrayList<String> similarword;

        @Field("oppositeto")
        public ArrayList<String> oppositeword;

        @Field("relatedto")
        public ArrayList<String> relatedword;
    }

    public static class SentenceClass {
        @Field("ja")
        public String ja;

        @Field("vi")
        public String vi;
    }

    public void updateFrom(JAVIDicMonEntity w) {
        this.word = w.word != null ? w.word : this.word;
        this.furigana = w.furigana != null ? w.furigana : this.furigana;
        this.tl = w.tl != null ? w.tl : this.tl;
        if (w.means != null) {
            for (int i = 0; i < w.means.size(); i++) {
                boolean mean_existed = false;
                int existed_id = 0;
                //search if this word is existed in means or not
                if (this.means != null) {
                    for (int j = 0; j < this.means.size(); j++) {
                        if (this.means.get(j).word.equals(w.means.get(i).word)) {
                            mean_existed = true;
                            existed_id = j;
                        }
                    }
                } else {
                    mean_existed = false;
                }

                //If mean existed update mean
                if (mean_existed) {
                    this.means.get(existed_id).tl = w.means.get(i).tl != null ? 
                                                    w.means.get(i).tl : 
                                                    this.means.get(existed_id).tl;
                    this.means.get(existed_id).chuyennganh = w.means.get(i).chuyennganh != null ? 
                                                             w.means.get(i).chuyennganh : 
                                                             this.means.get(existed_id).chuyennganh;
                    this.means.get(existed_id).linhvuc = w.means.get(i).linhvuc != null ? 
                                                         w.means.get(i).linhvuc : 
                                                         this.means.get(existed_id).linhvuc;
                    if (w.means.get(i).sentences != null) {
                        for (int l = 0; l < w.means.get(i).sentences.size(); l++) {
                            boolean sens_existed = false;
                            int sens_existed_id = 0;
                            // Check if this word.setence is existed or not
                            if (this.means.get(existed_id).sentences != null) {
                                for (int k = 0; k < this.means.get(existed_id).sentences.size(); k++) {
                                    if (this.means.get(existed_id).sentences.get(k).ja.equals(w.means.get(i).sentences.get(l).ja)) {
                                        sens_existed = true;
                                        sens_existed_id = k;
                                    }
                                }
                            } else {
                                sens_existed = false;
                            }
                            if (sens_existed) {
                                this.means.get(existed_id).sentences.get(sens_existed_id).vi = w.means.get(i).sentences.get(l).vi;
                            } else {
                                this.means.get(existed_id).sentences.add(w.means.get(i).sentences.get(l));
                            }
                        }
                    }
                    if (w.means.get(i).similarword != null) {
                        if (this.means.get(existed_id).similarword != null) {
                            for (int a = 0; a < w.means.get(i).similarword.size(); a++) {
                                if (this.means.get(existed_id).similarword.contains(w.means.get(i).similarword.get(a))) {
                                    //ignore
                                } else {
                                    this.means.get(existed_id).similarword.add(w.means.get(i).similarword.get(a));
                                }
                            }
                        } else {
                            this.means.get(existed_id).similarword = new ArrayList<>();
                            this.means.get(existed_id).similarword = w.means.get(i).similarword;
                        }
                    }
                    if (w.means.get(i).oppositeword != null) {
                        if (this.means.get(existed_id).oppositeword != null) {
                            for (int a = 0; a < w.means.get(i).oppositeword.size(); a++) {
                                if (this.means.get(existed_id).oppositeword.contains(w.means.get(i).oppositeword.get(a))) {
                                    //ignore
                                } else {
                                    this.means.get(existed_id).oppositeword.add(w.means.get(i).oppositeword.get(a));
                                }
                            }
                        } else {
                            this.means.get(existed_id).oppositeword = new ArrayList<>();
                            this.means.get(existed_id).oppositeword = w.means.get(i).oppositeword;
                        }
                    }
                    if (w.means.get(i).relatedword != null) {
                        if (this.means.get(existed_id).relatedword != null) {
                            for (int a = 0; a < w.means.get(i).relatedword.size(); a++) {
                                if (this.means.get(existed_id).relatedword.contains(w.means.get(i).relatedword.get(a))) {
                                    //ignore
                                } else {
                                    this.means.get(existed_id).relatedword.add(w.means.get(i).relatedword.get(a));
                                }
                            }
                        } else {
                            this.means.get(existed_id).relatedword = new ArrayList<>();
                            this.means.get(existed_id).relatedword = w.means.get(i).relatedword;
                        }
                    }
                } else {
                    this.means.add(w.means.get(i));
                }
            }
        }

    }

}