package imitori.mongodb.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Document(collection = "JAWords")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JAWordEntity {
    @Id
    private String id;

    @Field("ent_seq")
    private long ent_seq;
    
    @Field("k_ele")
    private List<k_ele_Class> k_ele;

    @Field("r_ele")
    private List<r_ele_Class> r_ele;

    @Field("sense")
    private List<sense_Class> sense;

    public static class k_ele_Class {
        @Field("keb")
        private String keb;

        @Field("ke_inf")
        private List<String> ke_inf;

        @Field("ke_pri")
        private List<String> ke_pri;
    }

    public static class r_ele_Class {
        @Field("reb")
        private String reb;

        @Field("re_nokanji")
        private String re_nokanji;

        @Field("re_restr")
        private List<String> re_restr;

        @Field("re_inf")
        private List<String> re_inf;

        @Field("re_pri")
        private List<String> re_pri;

    }

    public static class sense_Class {
        @Field("stagk")
        private List<String> stagk;

        @Field("stagr")
        private List<String> stagr;

        @Field("pos")
        private List<String> pos;

        @Field("xref")
        private List<String> xref;

        @Field("ant")
        private List<String> ant;

        @Field("field")
        private List<String> field;

        @Field("misc")
        private List<String> misc;

        @Field("s_inf")
        private String s_inf;

        @Field("lsource")
        private List<lsource_Class> lsource;

        @Field("dial")
        private List<String> dial;

        @Field("gloss_mean")
        private List<String> gloss_mean;

        @Field("gloss_phrase")
        private List<gloss_phrase_Class> gloss_phrase;

    }

    public static class lsource_Class {
        @Field("xml_lang")
        private String xml_lang;

        @Field("content")
        private String content;

        @Field("ls_type")
        private String ls_type;

        @Field("ls_wasei")
        private String ls_wasei;
    }

    public static class gloss_phrase_Class {
        @Field("g_type")
        private String g_type;

        @Field("content")
        private String content;
    }

}