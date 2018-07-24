package imitori.neo4j.services;

import java.util.*;

import imitori.neo4j.entity.SimilarToRelEntity;
import imitori.neo4j.entity.WordEntity;
import imitori.neo4j.repositories.WordRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WordService {
    private final static Logger LOG = LoggerFactory.getLogger(WordService.class);

    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Transactional(readOnly = true)
    public WordEntity creatOneWord(String word, String spell, String lang) {
        return wordRepository.createOneWord(word, spell, lang);
    }

    @Transactional(readOnly = true)
    public WordEntity findOneWord(String word) {
        WordEntity result = wordRepository.findOneWord(word);
        return result;
    }

    @Transactional(readOnly = true)
    public WordEntity findOneWord(Long id) {
        WordEntity result = wordRepository.findOneWord(id);
        return result;
    }

    @Transactional(readOnly = true)
    public Collection<Map<String, Integer>> findSimilarTo(String word) {
        Collection<Map<String, Integer>> res = new ArrayList<Map<String, Integer>>();
        Collection<WordEntity> resword = wordRepository.findWordSimilarTo(word);
        Iterator<WordEntity> wi = resword.iterator();

        while (wi.hasNext()) {
            WordEntity w = wi.next();
            //Check the InComing word
            System.out.println("In come: " + w.word);
            for (SimilarToRelEntity smlt: w.getSimilarTo()) {
                if (smlt.startWord != null && smlt.endWord != null) {
                    System.out.println("\"" + smlt.startWord.word +
                    "\" -> \"" + smlt.endWord.word + "\" | Score: " + smlt.score.toString());
                    Map<String, Integer> r = new HashMap<>();
                    r.put(smlt.endWord.word, smlt.score);
                    res.add(r);
                }
            }
        }
        return res;
    }

    @Transactional(readOnly = true)
    public Collection<Map<String, Integer>> findSimilarFrom(String word) {
        Collection<Map<String, Integer>> res = new ArrayList<Map<String, Integer>>();
        Collection<WordEntity> resword = wordRepository.findWordSimilarFrom(word);
        Iterator<WordEntity> wi = resword.iterator();

        while (wi.hasNext()) {
            WordEntity w = wi.next();
            System.out.println("Out come: " + w.word);
            for (SimilarToRelEntity smlf: w.getSimilarFrom()) {
                if (smlf.startWord != null && smlf.endWord != null) {
                    System.out.println("\"" + smlf.startWord.word +
                    "\" -> \"" + smlf.endWord.word + "\" | Score: " + smlf.score.toString());
                    Map<String, Integer> r = new HashMap<>();
                    r.put(smlf.startWord.word, smlf.score);
                    res.add(r);
                }
            }
            
        }
        return res;
    }

    //Set a score for a SimilarRel between 2 word, if the rel is null, create the rel
    @Transactional(readOnly = true)
    public void setSimilarRelScore(Long from_id, Long to_id, Integer rel_score) {
        WordEntity fw = findOneWord(from_id);
        WordEntity tw = findOneWord(to_id);
        if (fw == null || tw == null) {
            return;
        }
        
    }

    //Add a pair of word, then set the SIMILAR_TO.score between them
    //If one of them (from Word, Relation, ToWord) existed, only update
    @Transactional(readOnly = true)
    public void createPairOfWord(String w1, String l1, String w2, String l2, Integer sc) {
        WordEntity word1 = wordRepository.findOneWord(w1, l1);
        WordEntity word2 = wordRepository.findOneWord(w2, l2);

        if (word1 == null) {
            word1 = wordRepository.createOneWord(w1, "", l1);
        } 

        if (word2 == null) {
            word2 = wordRepository.createOneWord(w2, "", l2);
        }

        wordRepository.createSimilarToRel(word1.getId(), word2.getId(), sc);
    }
}