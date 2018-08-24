package imitori.mongodb.entity;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "ViJaDic")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VIJADicMonEntity {
    @Id
    private Integer id;

    @Field("word")
    public String word;

    @Field("means")
    public ArrayList<TlMean> means;

    @Field("fieldmeans")
    public ArrayList<ChuyenNganh> fieldmean;

    public Integer getid() {
        return this.id;
    }

    public void setid(Integer id) {
        this.id = id;
    }

    public String getword() {
        return this.word;
    }

    public void setword(String w) {
        this.word = w;
    }

    public ArrayList<TlMean> getmeans() {
        return this.means;
    }

    public void setmeans(ArrayList<TlMean> m) {
        this.means = m;
    }

    public ArrayList<ChuyenNganh> getfieldmeans() {
        return this.fieldmean;
    }

    public void setfieldmeans(ArrayList<ChuyenNganh> s) {
        this.fieldmean = s;
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

    public ArrayList<String> getAllJaMeans() {
        ArrayList<String> res = new ArrayList<>();
        return res;
    }
}