package imitori.neo4j.controller;

import java.util.Collection;
import java.util.Map;

import imitori.neo4j.entity.WordEntity;
import imitori.neo4j.dto.WordDto;
import imitori.neo4j.services.WordService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public WordEntity findOneByWord(@RequestParam String word) {
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
        return result;
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
}