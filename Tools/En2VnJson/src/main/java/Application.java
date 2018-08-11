import java.util.ArrayList;

import javax.sound.midi.Patch;
import javax.sound.sampled.Line;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.omg.CORBA.CurrentHolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;

public class Application {

    public class Phrase {
        public String phrase;
        public String mean;

        public Phrase() {
            this.phrase = new String();
            this.mean = new String();
        }
    }

    public class TlSubMean {
        public String mean;
        public ArrayList<Phrase> phrases;

        public TlSubMean() {
            this.mean = new String();
            this.phrases = new ArrayList<Phrase>();
            // this.phrases.add(new Phrase());
        }
    }

    public class SideWord {
        public String word;
        public ArrayList<TlSubMean> means;

        public SideWord() {
            this.word = new String();
            this.means = new ArrayList<TlSubMean>();
            // this.means.add(new TlSubMean());
        }
    }

    public class TlMean {
        public String tl;
        public ArrayList<TlSubMean> means;
        public ArrayList<SideWord> sidewords;

        public TlMean() {
            this.tl = new String();
            this.means = new ArrayList<TlSubMean>();
            // this.means.add(new TlSubMean());
            this.sidewords = new ArrayList<SideWord>();
            // this.sidewords.add(new SideWord());
        }
    }

    public class LinhVuc {
        public String name;
        public ArrayList<String> means;

        public LinhVuc() {
            this.name = new String();
            this.means = new ArrayList<String>();
            // this.means.add(new String());
        }
    }

    public class ChuyenNganh {
        public String name;
        public ArrayList<String> means;
        public ArrayList<LinhVuc> lv;

        public ChuyenNganh() {
            this.name = new String();
            this.means = new ArrayList<String>();
            // this.means.add(new String());
            this.lv = new ArrayList<LinhVuc>();
            // this.lv.add(new LinhVuc());
        }
    }

    public class WRecord {
        public String word;
        public String spell;
        public ArrayList<TlMean> means;
        public ArrayList<ChuyenNganh> fieldmeans;

        public WRecord() {
            this.word = new String();
            this.spell = new String();
            this.means = new ArrayList<TlMean>();
            // this.means.add(new TlMean());
            this.fieldmeans = new ArrayList<ChuyenNganh>();
            // this.fieldmeans.add(new ChuyenNganh());

        }
    }

    public class WordBlock {
        public String word;
        public String spell;
        public ArrayList<TlMean> means;

        public WordBlock() {
            this.word = new String();
            this.spell = new String();
            this.means = new ArrayList<TlMean>();
        }
    }

    public class FieldBlock {
        public ArrayList<ChuyenNganh> fieldmeans;

        public FieldBlock() {
            this.fieldmeans = new ArrayList<ChuyenNganh>();
        }
    }

    // WordBlock: 0
    // FieldBlock: 1
    public static int detectDataBlock(String word, String data) {
        if (data.indexOf(word) == 0) {
            return 0;
        }
        return 1;
    }

    public Phrase getPhrase(String input) {
        Phrase res = new Phrase();
        String[] parts = input.split("\\+");
        if (parts.length < 2) {
            return res;
        }
        res.phrase = parts[0].trim();
        res.mean = input.substring(input.indexOf("+") + 1).trim();

        // System.out.println(res.mean);
        return res;
    }

    public TlSubMean getTlSubMean(String input) {
        TlSubMean res = new TlSubMean();
        res.mean = input.split("\\\\n")[0].trim();
        String[] phrases = input.split("\\\\n=");
        if (phrases.length < 2) {
            return res;
        }
        for (int i = 1; i < phrases.length; i++) {
            res.phrases.add(getPhrase(phrases[i]));
        }
        return res;
    }

    public SideWord getSideWord(String input) {
        SideWord res = new SideWord();
        res.word = input.split("\\\\n")[0].trim();
        String[] means = input.split("\\\\n-");
        if (means.length < 2) {
            return res;
        }
        for (int i = 1; i < means.length; i++) {
            res.means.add(getTlSubMean(means[i]));
        }

        return res;
    }

    public TlMean getTlMean(String input) {
        TlMean res = new TlMean();
        res.tl = input.split("\\\\n")[0].trim();
        String inputmain = new String();
        // String[] inputsub = new String[]();
        if (input.indexOf("!") != -1) {
            inputmain = input.split("\\\\n!")[0];
            for (int i = 1; i < input.split("\\\\n!").length; i++) {
                res.sidewords.add(getSideWord(input.split("\\\\n!")[i]));
            }
        } else {
            inputmain = input;
        }
        String[] means = inputmain.split("\\\\n-");
        if (means.length < 2) {
            return res;
        }
        // Get submean
        for (int i = 1; i < means.length; i++) {
            res.means.add(getTlSubMean(means[i]));
        }
        // get sideword

        return res;
    }

    public WordBlock ProcessWordDataBlock(String data) {
        WordBlock w = new WordBlock();
        String[] lines = data.split("\\\\n");
        if (lines.length < 1)
            return w;

        // Check lines[0] to detect the spell
        // System.out.println("lines[0] " + lines[0]);
        if (lines[0].indexOf("/") != -1 && lines[0].lastIndexOf("/") != lines[0].indexOf("/")) {
            // Has spell
            w.spell = lines[0].substring(lines[0].indexOf("/") + 1, lines[0].lastIndexOf("/"));
            // System.out.println("WordSpell: " + w.spell);
            // System.out.println();
        }

        String[] tuvungs = data.split("\\*");
        // System.out.println(tuvungs.length);
        if (tuvungs.length < 2) {
            TlMean tlmean = getTlMean(data.trim());
            tlmean.tl = "General";
            w.means.add(tlmean);
        }

        for (int i = 1; i < tuvungs.length; i++) {
            w.means.add(getTlMean(tuvungs[i].trim()));
        }

        // Check next line to get others
        return w;
    }

    static FieldBlock curFieldBlock = new Application().new FieldBlock();
    static int curFieldMeanIndex = 0;

    public FieldBlock ProcessFieldDataBlock(String data) {
        FieldBlock f = new FieldBlock();
        // System.out.println("FieldBlock: " + data);
        String[] lines = data.split("\\\\n");
        if (lines.length < 1) {
            return f;
        }
        if (lines[0].indexOf("Lĩnh vực") != -1) {

        } else {
            // System.out.println(curChuyenNganh);
            // curChuyenNganh = lines[0];
        }

        return f;
    }

    // 0 : Chuyen Nganh
    // 1 : Linh Vuc
    public int detectChuyenNganhBlock(String input) {
        int res = 0;
        // System.out.println("Chuyen Nganh Block: " + input);
        if (input.split("\\\\n")[0].indexOf("Lĩnh vực") != -1) {
            res = 1;
        } else {
            res = 0;
        }
        return res;
    }

    public ChuyenNganh getChuyenNganhBlock(String input) {
        // System.out.println("Chuyen Nganh: " + input);
        ChuyenNganh res = new ChuyenNganh();
        String[] lines = input.split("\\\\n");
        res.name = lines[0].trim();
        if (lines.length < 2) {
            return res;
        }
        String[] means = input.split("\\\\n-");
        if (means.length < 2) {
            return res;
        }
        for (int i = 1; i < means.length; i++) {
            res.means.add(means[i].trim());
        }
        return res;
    }

    public LinhVuc getLinhVucBlock(String input) {
        // System.out.println("Linh Vuc: " + input);
        LinhVuc res = new LinhVuc();
        res.name = input.split("\\\\n")[0].trim();
        String[] means = input.split("\\\\n-");
        if (means.length < 2) {
            return res;
        }
        for (int i = 1; i < means.length; i++) {
            res.means.add(means[i].trim());
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<WRecord> wrl = new ArrayList<WRecord>();

        File f = new File("envi.txt");
        BufferedReader b = new BufferedReader(new FileReader(f));

        String readLine = "";
        int count = 0;
        while ((readLine = b.readLine()) != null) {
            if (count < 10)
                count++;
            else
                break;
            if (count > 0) {
                System.out.println(count);
                FieldBlock field_block = new Application().new FieldBlock();
                WordBlock word_block = new Application().new WordBlock();
                int curChuyenNganhIndex = 0;
                String[] elem = readLine.split("\t");
                String word = elem[0].toString();
                word_block.word = word;
                String[] data_blocks = elem[1].split("@");
                for (int i = 1; i < data_blocks.length; i++) {
                    int block_type = detectDataBlock(word, data_blocks[i]);

                    if (block_type == 0) {
                        // Word block
                        // System.out.println("Found Word Block: " + data_blocks[i]);
                        word_block = new Application().ProcessWordDataBlock(data_blocks[i]);
                        word_block.word = word;
                    } else if (block_type == 1) {
                        // System.out.println("Found Field Block: " + data_blocks[i]);
                        // Check this block is ChuyenNganh or LinhVuc
                        int sub_type = new Application().detectChuyenNganhBlock(data_blocks[i]);
                        if (sub_type == 0) {
                            // Chuyen nganh block
                            field_block.fieldmeans.add(new Application().getChuyenNganhBlock(data_blocks[i]));
                            curChuyenNganhIndex = field_block.fieldmeans.size();

                        } else if (sub_type == 1) {
                            // Linh vuc block
                            ChuyenNganh tmp = field_block.fieldmeans.get(curChuyenNganhIndex - 1);
                            tmp.lv.add(new Application().getLinhVucBlock(data_blocks[i]));
                            field_block.fieldmeans.set(curChuyenNganhIndex - 1, tmp);
                        }
                    } else {
                        // Unknown block
                    }
                }
                // System.out.println(new
                // GsonBuilder().setPrettyPrinting().create().toJson(word_block));
                // System.out.println(new
                // GsonBuilder().setPrettyPrinting().create().toJson(field_block));
                WRecord wr = new Application().new WRecord();
                wr.word = word_block.word;
                wr.spell = word_block.spell;
                wr.means = word_block.means;
                wr.fieldmeans = field_block.fieldmeans;
                wrl.add(wr);
            }
        }

        b.close();

        Gson gs = new GsonBuilder().setPrettyPrinting().create();
        PrintWriter writer = new PrintWriter("En2Vn.json", "UTF-8");
        writer.println(gs.toJson(wrl));
        writer.close();
        // System.out.println(gs.toJson(wrl));

    }
}