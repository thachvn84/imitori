package imitori.neo4j.controller;

import java.util.Collection;
import java.util.Map;

import imitori.neo4j.entity.WordEntity;
import imitori.neo4j.services.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WordController {
    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/word")
    public WordEntity findByWord(@RequestParam String word) {
        return wordService.findByWord(word);
    }

    @GetMapping("/words")
    public Collection<WordEntity> findByWordLike(@RequestParam String word) {
        return wordService.findByWordLike(word);
    }

}