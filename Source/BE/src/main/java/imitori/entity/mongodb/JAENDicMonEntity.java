package imitori.entity.mongodb;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import imitori.utils.StringUtils;

@Document(collection = "JaEnDic")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JAENDicMonEntity {
    @Id
    public Integer id;

    @Field("ent_seq")
    public Integer ent_seq;
    
    @Field("k_ele")
    public List<k_ele_Class> k_ele;

    @Field("r_ele")
    public List<r_ele_Class> r_ele;

    @Field("sense")
    public List<sense_Class> sense;

    public Integer getid() {
        return this.id;
    }

    public void setid(Integer id) {
        this.id  = id;
    }

    public Integer getent_seq() {
        return this.ent_seq;
    }

    public void setent_seq(Integer s) {
        this.ent_seq = s;
    }

    public List<k_ele_Class> getk_ele() {
        return this.k_ele;
    }

    public void setk_ele(List<k_ele_Class> s) {
        this.k_ele = s;
    }

    public List<r_ele_Class> getr_ele() {
        return this.r_ele;
    }

    public void setr_ele(List<r_ele_Class> s) {
        this.r_ele = s;
    }

    public List<sense_Class> getsense() {
        return this.sense;
    }

    public void setsense(List<sense_Class> s) {
        this.sense = s;
    }

    public static class k_ele_Class {
        @Field("keb")
        public String keb;

        @Field("ke_inf")
        public List<String> ke_inf;

        @Field("ke_pri")
        public List<String> ke_pri;

        public k_ele_Class(String k) {
            this.keb = k;
            this.ke_inf = new ArrayList<>();
            this.ke_pri = new ArrayList<>();
        }
        public String getkeb() {
            return this.keb;
        }

        public void setkeb(String s) {
            this.keb = s;
        }

        public List<String> getke_inf() {
            return this.ke_inf;
        }

        public void setke_inf(List<String> s) {
            this.ke_inf = s;
        }

        public List<String> getke_pri() {
            return this.ke_pri;
        }

        public void setke_pri(List<String> s) {
            this.ke_pri = s;
        }
    }

    public static class r_ele_Class {
        @Field("reb")
        public String reb;

        @Field("re_nokanji")
        public String re_nokanji;

        @Field("re_restr")
        public List<String> re_restr;

        @Field("re_inf")
        public List<String> re_inf;

        @Field("re_pri")
        public List<String> re_pri;

        public r_ele_Class(String furi) {
            this.reb = furi;
            this.re_restr = new ArrayList<>();
            this.re_inf = new ArrayList<>();
            this.re_pri = new ArrayList<>();
        }

        public String getreb() {
            return this.reb;
        }

        public void setreb(String s) {
            this.reb = s;
        }

        public String getre_nokanji() {
            return this.re_nokanji;
        }

        public void setre_nokanji(String s) {
            this.re_nokanji = s;
        }

        public List<String> getre_restr() {
            return this.re_restr;
        }

        public void setre_restr(List<String> s) {
            this.re_restr = s;
        }

        public List<String> getre_inf() {
            return this.re_inf;
        }

        public void setre_inf(List<String> s) {
            this.re_inf = s;
        }

        public List<String> getre_pri() {
            return this.re_pri;
        }

        public void setre_pri(List<String> s) {
            this.re_pri = s;
        }

    }

    public static class sense_Class {
        @Field("stagk")
        public List<String> stagk;

        @Field("stagr")
        public List<String> stagr;

        @Field("pos")
        public List<String> pos;

        @Field("xref")
        public List<String> xref;

        @Field("ant")
        public List<String> ant;

        @Field("field")
        public List<String> field;

        @Field("misc")
        public List<String> misc;

        @Field("s_inf")
        public String s_inf;

        @Field("lsource")
        public List<lsource_Class> lsource;

        @Field("dial")
        public List<String> dial;

        @Field("gloss_mean")
        public List<String> gloss_mean;

        @Field("gloss_phrase")
        public List<gloss_phrase_Class> gloss_phrase;

        public List<String> getstagk() {
            return this.stagk;
        }

        public void setstagk(List<String> s) {
            this.stagk = s;
        }

        public List<String> getstagr() {
            return this.stagr;
        }

        public void setstagr(List<String> s) {
            this.stagr = s;
        }

        public List<String> getpos() {
            return this.pos;
        }

        public void setpos(List<String> s) {
            this.pos = s;
        }

        public List<String> getxref() {
            return this.xref;
        }

        public void setxref(List<String> s) {
            this.xref = s;
        }

        public List<String> getant() {
            return this.ant;
        }

        public void setant(List<String> s) {
            this.ant = s;
        }

        public List<String> getfield() {
            return this.field;
        }

        public void setfield(List<String> s) {
            this.field = s;
        }

        public List<String> getmisc() {
            return this.misc;
        }

        public void setmisc(List<String> s) {
            this.misc = s;
        }

        public String gets_inf() {
            return this.s_inf;
        }

        public void sets_inf(String s) {
            this.s_inf = s;
        }

        public List<lsource_Class> getlsource() {
            return this.lsource;
        }

        public void setlsource(List<lsource_Class> s) { 
            this.lsource = s;
        }

        public List<String> getdial() {
            return this.dial;
        }

        public void setdial(List<String> s) {
            this.dial = s;
        }

        public List<String> getgloss_mean() {
            return this.gloss_mean;
        }

        public void setgloss_mean(List<String> s) {
            this.gloss_mean = s;
        }

        public List<gloss_phrase_Class> getgloss_phrase() {
            return this.gloss_phrase;
        }

        public void setgloss_phrase(List<gloss_phrase_Class> s) {
            this.gloss_phrase = s;
        }

    }

    public static class lsource_Class {
        @Field("xml_lang")
        public String xml_lang;

        @Field("content")
        public String content;

        @Field("ls_type")
        public String ls_type;

        @Field("ls_wasei")
        public String ls_wasei;

        public String getxml_lang() {
            return this.xml_lang;
        }

        public void setxml_lang(String s) {
            this.xml_lang = s;
        }

        public String getcontent() {
            return this.content;
        }

        public void setcontent(String s) {
            this.content = s;
        }

        public String getls_type() {
            return this.ls_type;
        }

        public void setls_type(String s) {
            this.ls_type = s;
        }

        public String getls_wasei() {
            return this.ls_wasei;
        }

        public void setls_wasei(String s) {
            this.ls_wasei = s;
        }
    }

    public static class gloss_phrase_Class {
        @Field("g_type")
        public String g_type;

        @Field("content")
        public String content;

        public String getg_type() {
            return this.g_type;
        }

        public void setg_type(String s) {
            this.g_type = s;
        }

        public String getcontent() {
            return this.content;
        }

        public void setcontent(String s) {
            this.content = s;
        }
    }

    public ArrayList<String> getAllEnMeans() {
        ArrayList<String> res = new ArrayList<>();
        if (this.sense != null) {
            for (int i = 0; i < this.sense.size(); i++) {
                sense_Class curS = this.sense.get(i);
                if (curS.gloss_mean != null) {
                    for (int j = 0; j < curS.gloss_mean.size(); j++) {
                        String rawmean = curS.gloss_mean.get(j);
                        while (rawmean.indexOf("(") != -1) {
                            int bpos = rawmean.indexOf("(") > 0 ? rawmean.indexOf("(") : 0;
                            int epos = rawmean.indexOf(")") > 0 ? rawmean.indexOf(")") : rawmean.length() - 1;
                            String rem = rawmean.substring(bpos, epos + 1);
                            rawmean = rawmean.replace(rem, "");
                        }
                        String[] srm = rawmean.split(",");
                        for (int k = 0; k < srm.length; k++) {
                            String mg = srm[k].trim();
                            String aw = new String();
                            if (mg.indexOf("to ") == 0) {
                                aw = mg.substring(3, mg.length());
                            } else {
                                aw = mg;
                            }
                            res.add(aw);
                        }
                    }
                }
            }
        }
        StringUtils f = new StringUtils();

        return f.removeDuplicate(res);
    }

    public void updateFrom(JAENDicMonEntity w) {
        this.ent_seq = w.ent_seq != 0 ? w.ent_seq : this.ent_seq;
        if (w.k_ele != null) {
            if (this.k_ele != null) {
                this.k_ele = w.k_ele;
            } else {
                this.k_ele = new ArrayList<>();
                this.k_ele = w.k_ele;
            }
        }
        if (w.r_ele != null) {
            if (this.r_ele != null) {
                this.r_ele = w.r_ele;
            } else {
                this.r_ele = new ArrayList<>();
                this.r_ele = w.r_ele;
            }
        }
        if (w.sense != null) {
            if (this.sense != null) {
                this.sense = new ArrayList<>();
            } else {
                this.sense = new ArrayList<>();
                this.sense = w.sense;
            }
        }
    }

    public ArrayList<String> getKeb() {
        ArrayList<String> res = new ArrayList<>();
        return res;
    }

    public ArrayList<String> getReb() {
        ArrayList<String> res = new ArrayList<>();
        return res;
    }

}