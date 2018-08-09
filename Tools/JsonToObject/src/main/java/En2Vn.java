import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class En2Vn {
    public static class WRecord {
        public String word;
        public String spell;
        public ArrayList<TlMean> means;
        public ArrayList<ChuyenNganh> fieldmeans;
    }

    public static class ChuyenNganh {
        public String name;
        public ArrayList<String> means;
    }

    public static class TlMean {
        public String tl;
        public ArrayList<TlSubMean> means;
        public ArrayList<SideWord> sidewords;
    }

    public static class SideWord {
        public String word;
        public ArrayList<TlSubMean> means;
    }

    public static class TlSubMean {
        public String mean;
        public ArrayList<Phrase> phrases;
    }

    public static class Phrase {
        public String phrase;
        public String mean;
    }

    public void test() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        WRecord[] wr = gson.fromJson(new FileReader("WRSave.json"), WRecord[].class);

        System.out.println(wr.length);
        Gson gson1 = new Gson();
        for (int i = 0; i < wr.length; i++) {

        }
    }
}