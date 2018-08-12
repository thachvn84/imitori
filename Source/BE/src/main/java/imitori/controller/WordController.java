package imitori.controller;

import imitori.neo4j.entity.SimilarToRelEntity;
import imitori.neo4j.entity.WordEntity;
import imitori.neo4j.dto.SimilarToRelDto;
import imitori.neo4j.dto.WordDto;
import imitori.services.WordService;
import imitori.mongodb.entity.Employee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dic/word/")
public class WordController {
    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/add")
    public Long createOneWord(@RequestParam String word, @RequestParam String spell, @RequestParam String lang) {
        Long id = 0L;
        WordEntity wi = wordService.createOneWord(word, spell, lang);
        id = wi.getId();
        return id;
    }

    @PostMapping("/add")
    public Long createOneWord(@RequestBody WordDto input) {
        Long id = 0L;
        WordEntity wi = wordService.createOneWord(input.word, input.spell, input.lang);
        id = wi.getId();
        return id;
    }

    @GetMapping("/delete")
    public void deleteOneWord(@RequestParam Long id) {
        wordService.deleteOneWord(id);
    }

    @GetMapping("/search")
    public ResponseEntity<WordDto> findOneByWord(@RequestParam String word) {

        WordEntity result;
        result = wordService.findOneWord(word);
        if (result == null) {
            result = new WordEntity(0L, "", "", "");
        }
        // System.out.println(excutePost(""));
        return ResponseEntity.status(HttpStatus.CREATED).header("Access-Control-Allow-Origin", "*")
                .body(new WordDto(result.getId(), result.word, result.spell, result.lang));
    }

    // Return the list of [Word.word, r.score] that [Input
    // param]-[r:SIMILAR_TO]->(Word)
    @GetMapping("/similarTo")
    public String findSimilarToByWord(@RequestParam String word) {
        Collection<Map<String, Integer>> result;
        result = wordService.findSimilarTo(word);

        return result.toString();
    }

    // Return the list of [Word.word, r.score] that [Word]-[r:SIMILAR_TO]->(Input
    // param)
    @GetMapping("/similarFrom")
    public String findSimilarFromByWord(@RequestParam String word) {
        Collection<Map<String, Integer>> result;
        result = wordService.findSimilarFrom(word);

        return result.toString();
    }

    // Add a pair of word, then set the SIMILAR_TO.score between them
    // If one of them (from Word, Relation, ToWord) existed, only update
    @GetMapping("/createPair")
    public String createPairOfWord(@RequestParam String w1, @RequestParam String l1, @RequestParam String w2,
            @RequestParam String l2, @RequestParam Integer sc) {
        String result;
        wordService.createPairOfWord(w1, l1, w2, l2, sc);
        result = findSimilarToByWord(w1);
        return result;
    }

    // Add a pair of word, then set the SIMILAR_TO.score between them
    // If one of them (from Word, Relation, ToWord) existed, only update
    // If not existed, create full element
    @GetMapping("/createPairFull")
    public String createPairOfFullWord(@RequestParam String w1, @RequestParam String sp1, @RequestParam String l1,
            @RequestParam String w2, @RequestParam String sp2, @RequestParam String l2, @RequestParam Integer sc) {
        String result;
        wordService.createPairOfFullWord(w1, sp1, l1, w2, sp2, l2, sc);
        result = findSimilarToByWord(w1);
        return result;
    }

    @PostMapping("/rel/create/similarto")
    public SimilarToRelEntity createSimilarToRelEntity(@RequestBody SimilarToRelDto input) {
        SimilarToRelEntity res = new SimilarToRelEntity();
        res = wordService.createSimilarToRel(input.id1, input.id2, input.score);
        return res;
    }
}