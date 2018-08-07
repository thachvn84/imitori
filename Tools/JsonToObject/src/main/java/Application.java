import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class Application {

    public static class k_ele_Class {
        String keb; // Kanji cua tu
        List<String> ke_inf; // Thong tin bo sung cho keb trong nhom
        List<String> ke_pri; // Thong tin bo sung
    }

    // Reading element
    public static class r_ele_Class {
        String reb; // Furigana, neu reb = katakana, co nghia la ko co kanji
        String re_nokanji; // Neu khong co kanji, thi gia tri nay khac null (la blank)
        List<String> re_restr; // Kanji tuong ung cho reb trong nhom
                               // Neu trong nhom khong co re_restr, thi reb la furigana
                               // cua mainword
        List<String> re_inf; // Thong tin cho reb trong nhom
        List<String> re_pri; // Thong tin bo sung
    }

    // Loan word
    public class lsource_Class {
        @SerializedName("xml:lang")
        String xml_lang; // Borrow from Language
        String content; // Content
        String ls_type; // Always "y"
        String ls_wasei; // Alwasy "part"
    }

    // Meaning and related info
    public static class sense_Class {
        List<String> stagk; // Lexical meaning of Keb
        List<String> stagr; // Lexical meaning of Reb
        List<String> pos; // Tu loai
        List<String> xref; // Tu co lien quan
        List<String> ant; // Tu trai nghia
        List<String> field; // Chuyen nganh
        List<String> misc; // Thong tin them
        String s_inf; // Thong tin ghi chu them
        List<lsource_Class> lsource; // Tu muon tu nuoc ngoai
        List<String> dial; // Tu dia phuong
        List<Object> gloss; // Nghia cua tu
    }

    public static class entry_Class {
        int ent_seq; // Entry ID
        List<k_ele_Class> k_ele; // Kanji Element
        private List<r_ele_Class> r_ele; // Reading Element
        List<sense_Class> sense; // Translation and related information
    }

    public class SingletonListTypeAdapterFactory implements TypeAdapterFactory {
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {

            Type type = typeToken.getType();
            if (typeToken.getRawType() != List.class || !(type instanceof ParameterizedType)) {
                return null;
            }
            Type elementType = ((ParameterizedType) type).getActualTypeArguments()[0];
            TypeAdapter<?> elementAdapter = gson.getAdapter(TypeToken.get(elementType));
            TypeAdapter<T> arrayAdapter = gson.getDelegateAdapter(this, typeToken);
            return (TypeAdapter<T>) newSingtonListAdapter((TypeAdapter<Object>) elementAdapter,
                    (TypeAdapter<List<Object>>) arrayAdapter);
        }

        private <E> TypeAdapter<List<E>> newSingtonListAdapter(final TypeAdapter<E> elementAdapter,
                final TypeAdapter<List<E>> arrayAdapter) {
            return new TypeAdapter<List<E>>() {

                public void write(JsonWriter out, List<E> value) throws IOException {
                    if (value == null || value.isEmpty()) {
                        out.nullValue();
                    } else if (value.size() == 1) {
                        elementAdapter.write(out, value.get(0));
                    } else {
                        arrayAdapter.write(out, value);
                    }
                }

                public List<E> read(JsonReader in) throws IOException {
                    if (in.peek() != JsonToken.BEGIN_ARRAY) {
                        E obj = elementAdapter.read(in);
                        return Collections.singletonList(obj);
                    }
                    return arrayAdapter.read(in);
                }
            };
        }
    }

    public static class gloss_Class {
        String g_type;
        String content;
    }

    // Return 0: String
    // Return 1: gloss_Class
    private static int checkGloss(Object gloss) {
        String res = gloss.toString();
        if (res.contains("{g_type=")) {
            return 1;
        } else {
            return 0;
        }
    }

    private static gloss_Class getGloss(Object gloss) {
        String gtype = gloss.toString().split(",")[0].split("=")[1];
        String content = gloss.toString().split(",")[1].split("=")[1].split("}")[0];

        gloss_Class res = new gloss_Class();
        res.g_type = gtype;
        res.content = content;

        return res;
    }

    public static void main(String[] args) throws FileNotFoundException {

        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new Application().new SingletonListTypeAdapterFactory()).create();

        entry_Class[] we = gson.fromJson(new FileReader("filename.txt"), entry_Class[].class);

        for (int i = 0; i < we.length; i++) {
            if (we[i].ent_seq == 1057250) {
                // Access ent_seq
                System.out.println(we[i].ent_seq);

                // Access we[i].k_ele
                if (we[i].k_ele != null) {
                    for (int ik_ele = 0; ik_ele < we[i].k_ele.size(); ik_ele++) {
                        k_ele_Class K_ele = we[i].k_ele.get(ik_ele);

                        // Get keb
                        System.out.println("keb: " + K_ele.keb);

                        // Get ke_inf
                        if (K_ele.ke_inf != null) {
                            for (int ike_inf = 0; ike_inf < K_ele.ke_inf.size(); ike_inf++) {
                                System.out.println("ke_inf: " + K_ele.ke_inf.get(ike_inf));
                            }
                        }

                        // Get ke_pri
                        if (K_ele.ke_pri != null) {
                            for (int ike_pri = 0; ike_pri < K_ele.ke_pri.size(); ike_pri++) {
                                System.out.println("ke_pri: " + K_ele.ke_pri.get(ike_pri));
                            }
                        }
                    }
                }

                // Access we[i].r_ele
                if (we[i].r_ele != null) {
                    for (int ir_ele = 0; ir_ele < we[i].r_ele.size(); ir_ele++) {
                        r_ele_Class R_ele = we[i].r_ele.get(ir_ele);

                        // get reb
                        System.out.println("reb: " + R_ele.reb);

                        // get re_nokanji
                        System.out.println("re_nokanji: " + R_ele.re_nokanji);

                        // get re_restr
                        if (R_ele.re_restr != null) {
                            for (int ire_restr = 0; ire_restr < R_ele.re_restr.size(); ire_restr++) {
                                System.out.println("re_restr: " + R_ele.re_restr.get(ire_restr));
                            }
                        }

                        // get re_inf
                        if (R_ele.re_inf != null) {
                            for (int ire_inf = 0; ire_inf < R_ele.re_inf.size(); ire_inf++) {
                                System.out.println("re_inf: " + R_ele.re_inf.get(ire_inf));
                            }
                        }

                        // get re_pri
                        if (R_ele.re_pri != null) {
                            for (int ire_pri = 0; ire_pri < R_ele.re_pri.size(); ire_pri++) {
                                System.out.println("re_pri: " + R_ele.re_pri.get(ire_pri));
                            }
                        }
                    }
                }
                // Access we[i].sense
                if (we[i].sense != null) {
                    for (int isense = 0; isense < we[i].sense.size(); isense++) {
                        sense_Class Sense = we[i].sense.get(isense);
                        // Get stagk
                        if (Sense.stagk != null) {
                            for (int istagk = 0; istagk < Sense.stagk.size(); istagk++) {
                                System.out.println("stagk: " + Sense.stagk.get(istagk));
                            }
                        }

                        // Get stagr
                        if (Sense.stagr != null) {
                            for (int istagr = 0; istagr < Sense.stagk.size(); istagr++) {
                                System.out.println("stagr: " + Sense.stagr.get(istagr));
                            }
                        }

                        // Get pos
                        if (Sense.pos != null) {
                            for (int ipos = 0; ipos < Sense.pos.size(); ipos++) {
                                System.out.println("pos: " + Sense.pos.get(ipos));
                            }
                        }

                        // Get xref
                        if (Sense.xref != null) {
                            for (int ixref = 0; ixref < Sense.xref.size(); ixref++) {
                                System.out.println("xref: " + Sense.xref.get(ixref));
                            }
                        }

                        // Get ant
                        if (Sense.ant != null) {
                            for (int iant = 0; iant < Sense.ant.size(); iant++) {
                                System.out.println("ant: " + Sense.ant.get(iant));
                            }
                        }

                        // Get field
                        if (Sense.field != null) {
                            for (int ifield = 0; ifield < Sense.field.size(); ifield++) {
                                System.out.println("field: " + Sense.field.get(ifield));
                            }
                        }

                        // Get misc
                        if (Sense.misc != null) {
                            for (int imisc = 0; imisc < Sense.misc.size(); imisc++) {
                                System.out.println("misc: " + Sense.misc.get(imisc));
                            }
                        }

                        // Get dial
                        if (Sense.dial != null) {
                            for (int idial = 0; idial < Sense.dial.size(); idial++) {
                                System.out.println("dial: " + Sense.dial.get(idial));
                            }
                        }

                        // Get s_inf
                        System.out.println("s_inf: " + Sense.s_inf);

                        // Get lsource
                        if (Sense.lsource != null) {
                            for (int ilsource = 0; ilsource < Sense.lsource.size(); ilsource++) {
                                System.out.println("lsource.xml_lang: " + Sense.lsource.get(ilsource).xml_lang);
                                System.out.println("lsource.ls_type: " + Sense.lsource.get(ilsource).ls_type);
                                System.out.println("lsource.ls_wasei: " + Sense.lsource.get(ilsource).ls_wasei);
                                System.out.println("lsource.content: " + Sense.lsource.get(ilsource).content);
                            }
                        }

                        // Get gloss
                        if (Sense.gloss != null) {
                            for (int igloss = 0; igloss < Sense.gloss.size(); igloss++) {
                                Object gloss = (Sense.gloss.get(igloss));
                                if (checkGloss(gloss) == 1) {
                                    System.out.println("Gloss Object:");
                                    gloss_Class res = getGloss(gloss);
                                    System.out.println("\tg_type: " + res.g_type);
                                    System.out.println("\tcontent: " + res.content);
                                } else {
                                    System.out.println("gloss: " + gloss.toString());
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}
