import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static class entryClass {
        int ent_seq;
    }
    public static class JMdict {
        public static entryClass[] entry;
        public JMdict() {
            entry = new entryClass[1];
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new Gson();
        entryClass[] we = gson.fromJson(new FileReader("filename.json"),entryClass[].class);

        System.out.println(we.length);
        System.out.println(we[10000].ent_seq);
    }
}
