package imitori.mongodb.entity;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.neo4j.ogm.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import imitori.utils.StringUtils;

@Document(collection = "EnViDic")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ENVIDicMonEntity {
    @Id
    private Integer id;

    @Field("word")
    public String word;

    @Field("spell")
    public String spell;

    @Field("means")
    public ArrayList<TlMean> means;

    @Field("fieldmeans")
    public ArrayList<ChuyenNganh> fieldmeans;

    public Integer getid() {
        return this.id;
    }

    public void setid(Integer id) {
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

    public ArrayList<String> getAllViMeans() {
        ArrayList<String> res = new ArrayList<>();
        //Get mean from Word
        for (int i = 0; i < this.means.size(); i++) {
            TlMean tlmean = this.means.get(i);
            for (int j = 0; j < tlmean.means.size(); j++) {
                TlSubMean sub = tlmean.means.get(j);
                String rawmean = sub.mean;
                while (rawmean.indexOf("(") != -1) {
                    int bpos = rawmean.indexOf("(") > 0 ? rawmean.indexOf("(") : 0;
                    int epos = rawmean.indexOf(")") > 0 ? rawmean.indexOf(")") : rawmean.length() - 1;
                    String rem = new String();
                    if (bpos > epos) {
                        rem = rawmean.substring(0, epos + 1);    
                    } else {
                        rem = rawmean.substring(bpos, epos + 1);
                    }
                    rawmean = rawmean.replace(rem, "");
                }
                String[] srm = rawmean.split(",");
                for (int k = 0; k < srm.length; k++) {
                    res.add(srm[k].trim());
                }
            }
        }
        
        //Get mean from Chuyen Nganh
        for (int i = 0; i < this.fieldmeans.size(); i++) {
            ChuyenNganh cn = this.fieldmeans.get(i);
            // Process Chuyen nganh means
            for(int j = 0; j < cn.means.size(); j++) {
                String rawmean = cn.means.get(j);
                while (rawmean.indexOf("(") != -1) {
                    int bpos = rawmean.indexOf("(") > 0 ? rawmean.indexOf("(") : 0;
                    int epos = rawmean.indexOf(")") > 0 ? rawmean.indexOf(")") : rawmean.length() - 1;
                    String rem = rawmean.substring(bpos, epos + 1);
                    rawmean = rawmean.replace(rem, "");
                }
                String[] srm = rawmean.split(",");
                for (int k = 0; k < srm.length; k++) {
                    res.add(srm[k].trim());
                }
            }

            //Process Linh vuc mean
            for (int j = 0; j < cn.lv.size(); j++) {
                LinhVuc lv = cn.lv.get(j);
                for (int p = 0; p < lv.means.size(); p++) {
                    String rawmean = lv.means.get(p);
                    while (rawmean.indexOf("(") != -1) {
                        int bpos = rawmean.indexOf("(") > 0 ? rawmean.indexOf("(") : 0;
                        int epos = rawmean.indexOf(")") > 0 ? rawmean.indexOf(")") : rawmean.length() - 1;
                        String rem = rawmean.substring(bpos, epos + 1);
                        rawmean = rawmean.replace(rem, "");
                    }
                    String[] srm = rawmean.split(",");
                    for (int k = 0; k < srm.length; k++) {
                        res.add(srm[k].trim());
                    }
                }
            }
        }
        StringUtils f = new StringUtils();

        return f.removeDuplicate(res);
    }
}