import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

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

    public void test() throws JsonSyntaxException, JsonIOException, FileNotFoundException, IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        WRecord[] wr = gson.fromJson(new FileReader("WRSave.json"), WRecord[].class);

        System.out.println(wr.length);
        Gson gson1 = new Gson();
        for (int i = 0; i < wr.length; i++) {
            System.out.println(i + "/" + wr.length);

            String payload = gson1.toJson(wr[i]);
            StringEntity entity = new StringEntity(payload, ContentType.APPLICATION_JSON);
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost("http://localhost:9001/addEn");
            request.setEntity(entity);

            HttpResponse response = httpClient.execute(request);
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }
}