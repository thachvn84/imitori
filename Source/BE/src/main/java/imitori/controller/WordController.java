package imitori.controller;

import java.util.Collection;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import imitori.neo4j.dto.SimilarToRelDto;
import imitori.neo4j.dto.WordDto;
import imitori.neo4j.entity.SimilarToRelEntity;
import imitori.neo4j.entity.WordEntity;
import imitori.services.WordService;

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

    @GetMapping("/searchrange")
    public void findWord(@RequestParam Long id1, @RequestParam Long id2) {
        System.out.println("Range: " + id1.toString() + " ~ "  + id2.toString() + ": ");
        for (Long i = id1; i < id2; i++) {
            if (i % 100 == 0) {
                System.out.println("--------- [" + i.toString() + "/" + id2.toString() + "] ------------");
            }
            //System.out.println("search2 " + i); 
            wordService.getMeansScore(i);
        }
    }

    @GetMapping("/search2")
    public void findWord(@RequestParam Long id) {
        wordService.getMeansScore(id);
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
    // If not existed, create full element
    public class createPairOfFullWordParam {
        WordDto w1;
        WordDto w2;
    }
    @PostMapping("/createPairFull")
    public String createPairOfFullWord(@RequestBody createPairOfFullWordParam w, Integer sc) {
        String result;
        wordService.createPairOfSimilarWord_Full(w.w1, w.w2, sc);
        result = findSimilarToByWord(w.w1.word);
        return result;
    }

    // Create a similar to relation ship between two word
    // If one of them (from Word, Relation, ToWord) existed, only update
    // If not existed, create full element
    @PostMapping("/rel/create/similarto")
    public SimilarToRelEntity createSimilarToRelEntity(@RequestBody SimilarToRelDto input) {
        SimilarToRelEntity res = new SimilarToRelEntity();
        res = wordService.createSimilarToRel(input.id1, input.id2, input.score);
        return res;
    }
}