package imitori.neo4j.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.Map;

import imitori.neo4j.entity.WordEntity;
import imitori.neo4j.dto.WordDto;
import imitori.neo4j.services.WordService;

import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.Gson;

@RestController
@RequestMapping("/dic/word/")
public class WordController {
    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/search")
    public ResponseEntity<WordDto> findOneByWord(@RequestParam String word) {
        WordEntity result;
        result = wordService.findOneByWord(word);
        Gson gson = new Gson();
        System.out.println(gson.toJson(result));
        if (result == null) {
            result = wordService.findOneByKana(word);
            System.out.println(gson.toJson(result));
        }
        if (result == null) {
            result = wordService.findOneByRomaji(word);
            System.out.println(gson.toJson(result));
        }
        if (result == null) {
            result = new WordEntity(0L, "", "", "");
        }
        System.out.println(excutePost(""));
        return ResponseEntity.status(HttpStatus.CREATED).header("Access-Control-Allow-Origin", "*")
                .body(new WordDto(result.getId(), result.word, result.kana, result.romaji, result.mean));
    }

    @GetMapping("/searchAll")
    public Collection<WordEntity> findAllByWord(@RequestParam String word) {
        return wordService.findAllByWord(word);
    }

    @PostMapping("/add")
    public void addOneWord(@RequestBody WordDto word) {
        Gson gson = new Gson();
        System.out.println(gson.toJson(word));
        WordEntity result = wordService.addOneWord(word);
        System.out.println(gson.toJson(result));
    }

    public static String excutePost(String urlParameters) {
        URL url;
        HttpURLConnection connection = null;
        try {
            // Create connection
            url = new URL("https://dictionary.cambridge.org/dictionary/english-vietnamese/attract");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            // Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            // Get Response
            InputStream is = connection.getInputStream();
            //InputStreamReader a;
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}