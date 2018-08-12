package imitori.mongodb.entity;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.neo4j.ogm.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "EnWords")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ENWordEntity {
    @Id
    private Long id;

    @Field("word")
    public String word;

    @Field("spell")
    public String spell;

    @Field("means")
    public ArrayList<TlMean> means;

    @Field("fieldmeans")
    public ArrayList<ChuyenNganh> fieldmeans;

    public Long getid() {
        return this.id;
    }

    public void setid(Long id) {
        this.id = id;
    }

    public String getword() {
        return this.word;
    }

    public void setword(String word) {
        this.word = word;
    }

    public String getspell() {
        return this.spell;
    }

    public void setspell(String s) {
        this.spell = s;
    }

    public ArrayList<TlMean> getmeans() {
        return this.means;
    }

    public void setmeans(ArrayList<TlMean> s) {
        this.means = s;
    }

    public ArrayList<ChuyenNganh> getfieldmeans() {
        return this.fieldmeans;
    }

    public void setfieldmeans(ArrayList<ChuyenNganh> s) {
        this.fieldmeans = s;
    }

    public static class ChuyenNganh {
        @Field("name")
        public String name;

        @Field("means")
        public ArrayList<String> means;

        @Field("lv")
        public ArrayList<LinhVuc> lv;

        public String getname() {
            return this.name;
        }

        public void setname(String s) {
            this.name = s;
        }

        public ArrayList<String> getmeans() {
            return this.means;
        }

        public void setmeans(ArrayList<String> s) {
            this.means = s;
        }

        public ArrayList<LinhVuc> getlv() {
            return this.lv;
        }

        public void setlv(ArrayList<LinhVuc> s) {
            this.lv = s;
        }
    }

    public static class TlMean {
        @Field("tl")
        public String tl;

        @Field("means")
        public ArrayList<TlSubMean> means;

        @Field("sidewords")
        public ArrayList<SideWord> sidewords;

        public String gettl() {
            return tl;
        }

        public void settl(String tl) {
            this.tl = tl;
        }

        public ArrayList<TlSubMean> getmeans() {
            return this.means;
        }

        public void setmeans(ArrayList<TlSubMean> s) {
            this.means = s;
        }

        public ArrayList<SideWord> getsidewords() {
            return this.sidewords;
        }

        public void setsidewords(ArrayList<SideWord> s) {
            this.sidewords = s;
        }
    }

    public static class LinhVuc {
        @Field("name")
        public String name;

        @Field("means")
        public ArrayList<String> means;

        public String getname() {
            return this.name;
        }

        public void setname(String s) {
            this.name = s;
        }

        public ArrayList<String> getmeans() {
            return this.means;
        }

        public void setmeans(ArrayList<String> s) {
            this.means = s;
        }
    }

    public static class SideWord {
        @Field("word")
        public String word;

        @Field("means")
        public ArrayList<TlSubMean> means;

        public String getword() {
            return this.word;
        }

        public void setword(String w) {
            this.word = w;
        }

        public ArrayList<TlSubMean> getmeans() {
            return this.means;
        }

        public void setmeans(ArrayList<TlSubMean> s) {
            this.means = s;
        }
    }

    public static class TlSubMean {
        @Field("mean")
        public String mean;

        @Field("phrase")
        public ArrayList<Phrase> phrases;

        public String getmean() {
            return this.mean;
        }

        public void setmean(String m) {
            this.mean = m;
        }

        public ArrayList<Phrase> getphrases() {
            return this.phrases;
        }

        public void setphrases(ArrayList<Phrase> s) {
            this.phrases = s;
        }
    }

    public static class Phrase {
        @Field("phrase")
        public String phrase;

        @Field("mean")
        public String mean;

        public String getphrase() {
            return this.phrase;
        }

        public void setphrase(String s) {
            this.phrase = s;
        }

        public String getmean() {
            return this.mean;
        }

        public void setmean(String m) {
            this.mean = m;
        }
    }
}