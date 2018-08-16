import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Application {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("crawled.txt"));
        ArrayList<String> sb = new ArrayList<>();
        try {
            
            String line = br.readLine();
            while (line != null) {
                sb.add(line);
                line = br.readLine();
            }
        } finally {
            br.close();
        }

        System.out.println(sb.size());

        br = new BufferedReader(new FileReader("untrans.txt"));
        ArrayList<String> uts = new ArrayList<>();

        try {
            String line = br.readLine();
            Integer count = 0;
            boolean found = false;
            while (line != null) {
                count++;
                found = false;
                for (Integer i = 0; i < sb.size(); i++) {
                    if (sb.get(i).equals(count.toString())) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    uts.add("--OK--");

                } else {
                    uts.add(line);
                }
                found = false;
                line = br.readLine();
            }
        } finally {

        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("retrans.txt"));
        try {
            for (String line : uts) {
                bw.write(line + "\n");
            }
            bw.close();
        } finally {

        }
    }
}