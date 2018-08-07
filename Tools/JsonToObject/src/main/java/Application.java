import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Application {

    // ===========================================================================================
    private static class r_ele_ClassTypeAdapter implements JsonDeserializer<List<r_ele_Class>> {
        public List<r_ele_Class> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext ctx) {
            List<r_ele_Class> vals = new ArrayList<r_ele_Class>();
            if (json.isJsonArray()) {
                System.out.println("re_ele array");
                for (JsonElement e : json.getAsJsonArray()) {
                    vals.add((r_ele_Class) ctx.deserialize(e, r_ele_Class.class));
                }
            } else if (json.isJsonObject()) {
                System.out.println("re_ele single");
                vals.add((r_ele_Class) ctx.deserialize(json, r_ele_Class.class));
            } else {
                throw new RuntimeException("Unexpected JSON type: " + json.getClass());
            }
            return vals;
        }
    }

    private static class r_String_ClassTypeAdapter implements JsonDeserializer<List<String>> {
        public List<String> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext ctx) {
            List<String> vals = new ArrayList<String>();
            if (json.isJsonArray()) {
                System.out.println("String array");
                for (JsonElement e : json.getAsJsonArray()) {
                    vals.add((String) ctx.deserialize(e, String.class));
                }
            } else if (json.isJsonObject()) {
                System.out.println("String single");
                vals.add((String) ctx.deserialize(json, String.class));
            } else {
                throw new RuntimeException("Unexpected JSON type: " + json.getClass());
            }
            return vals;
        }
    }

    public abstract class CustomizedTypeAdapterFactory<C> implements TypeAdapterFactory {

        private final Class<C> customizedClass;

        public CustomizedTypeAdapterFactory(Class<C> customizedClass) {
            this.customizedClass = customizedClass;
        }

        @SuppressWarnings("unchecked") // we use a runtime check to guarantee that 'C' and 'T' are equal
        public final <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            return type.getRawType() == customizedClass
                    ? (TypeAdapter<T>) customizeMyClassAdapter(gson, (TypeToken<C>) type)
                    : null;
        }

        private TypeAdapter<C> customizeMyClassAdapter(Gson gson, TypeToken<C> type) {
            final TypeAdapter<C> delegate = gson.getDelegateAdapter(this, type);
            final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);

            return new TypeAdapter<C>() {

                @Override
                public void write(JsonWriter out, C value) throws IOException {
                    JsonElement tree = delegate.toJsonTree(value);
                    beforeWrite(value, tree);
                    elementAdapter.write(out, tree);
                }

                @Override
                public C read(JsonReader in) throws IOException {
                    JsonElement tree = elementAdapter.read(in);
                    afterRead(tree);
                    return delegate.fromJsonTree(tree);
                }
            };
        }

        /**
         * Override this to muck with {@code toSerialize} before it is written to the
         * outgoing JSON stream.
         */
        protected void beforeWrite(C source, JsonElement toSerialize) {
        }

        /**
         * Override this to muck with {@code deserialized} before it parsed into the
         * application type.
         */
        protected void afterRead(JsonElement deserialized) {
        }
    }

    private class r_ele_TypeAdapterFactory extends CustomizedTypeAdapterFactory<r_ele_Class> {
        private r_ele_TypeAdapterFactory() {
            super(r_ele_Class.class);
        }

        @Override
        protected void beforeWrite(r_ele_Class source, JsonElement toSerialize) {

        }

        @Override
        protected void afterRead(JsonElement json) {
            System.out.println(json.toString());
            if (json.isJsonArray()) {
                System.out.println("re_ele array");
                for (JsonElement e : json.getAsJsonArray()) {
                    r_ele_Class tmp = new r_ele_Class();
                    tmp.reb = e.getAsJsonObject().get("reb").getAsString();
                }
            } else if (json.isJsonObject()) {
                System.out.println("re_ele single");
            }
        }
    }

    // ============================================================================

    public static class sample_d {
        String d1;
        String d2;
    }

    public static class sample {
        int a;
        List<String> b;
        int c;
        sample_d d;
    }

    public static class k_ele_Class {
        String keb;
        List<String> ke_inf;
        List<String> ke_pri;
    }

    public static class r_ele_Class {
        String reb;
        String re_nokoanji;
        List<String> re_restr;
        List<String> re_inf;
        List<String> re_pri;
    }

    public class lsource_Class {
        String xml_lang;
        String content;
        String ls_type;
        String ls_wasei;
    }

    public static class sense_Class {
        List<String> stagk;
        List<String> stagr;
        List<String> pos;
        List<String> xref;
        List<String> ant;
        List<String> field;
        List<String> misc;
        String s_inf;
        List<lsource_Class> lsource;
        List<String> dial;
        List<Object> gloss;
    }

    public static class entry_Class {
        int ent_seq;
        List<k_ele_Class> k_ele;
        private List<r_ele_Class> r_ele;
        List<sense_Class> sense;
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

        // Gson gson = new Gson();
        entry_Class[] we = gson.fromJson(new FileReader("filename.json"), entry_Class[].class);

        //sample[] smp = gson.fromJson(new FileReader("vidu.json"), sample[].class);
        //System.out.println(smp[0].d.d1);

        System.out.println(we.length);
        for (int i = 0; i < we.length; i++) {
            if (we[i].ent_seq == 2745380) {
                for (int j = 0; j < we[i].sense.get(0).gloss.size(); j++) {
                    Object t = (we[i].sense.get(0).gloss.get(j));
                    System.out.println(t.toString());
                    if (checkGloss(t) == 1 ) {
                        System.out.println("Ok to cast");
                        gloss_Class res = getGloss(t);
                        System.out.println(res.g_type);
                        System.out.println(res.content);
                    }
                }
            }
        }
    }
}
