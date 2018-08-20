package imitori.services.admin;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import imitori.mongodb.entity.ENVIWordEntity;
import imitori.mongodb.entity.JAENWordEntity;
import imitori.neo4j.dto.WordDto;
import imitori.neo4j.entity.OppositeToRelEntity;
import imitori.neo4j.entity.RelatedToRelEntity;
import imitori.neo4j.entity.SimilarToRelEntity;
import imitori.neo4j.entity.TranslateToRelEntity;
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
    private final JaEnDicService jaEnDicService;
    private final EnViDicService enViDicService;

    public WordService(WordRepository wordRepository, JaEnDicService ja, EnViDicService en) {
        this.wordRepository = wordRepository;
        this.jaEnDicService = ja;
        this.enViDicService = en;
    }

    @Transactional(readOnly = true)
    public WordEntity createOneWord(String word, String spell, String lang) {
        return wordRepository.createOneWord(word, spell, lang);
    }

    @Transactional(readOnly = true)
    public WordEntity createOneWord(String word, ArrayList<String> spell, String lang) {
        return wordRepository.createOneWord(word, spell, lang);
    }

    @Transactional(readOnly = true)
    public void deleteOneWord(Long id) {
        wordRepository.deleteOneWord(id);
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

    /* 1. API for SimilarTo Relationship
     *  - findSimilarTo(String word) -> Collection of word:score where given word SimilarTo
     *  - findSimilarFrom(String word) -> Collection of word:score which similar to given word
     *  - createSimilarToRel(Long id1, Long id2, Integer score) -> Create a SimilarTo relation and return new one
     *  - setSimilarRelScore(Long from_id, Long to_id, Integer rel_score) -> Set a score for a relation and create if not existed.
     *  - createPairOfSimilarWord(String w1, String l1, String w2, String l2, Integer sc) -> Create relation between two word
     *  - createPairOfSimilarWord_Full(String w1, String sp1, String l1, String w2, String sp2, String l2, Integer sc)
     *      -> Create relation between word and setting full information
     */

    @Transactional(readOnly = true)
    public Collection<Map<String, Integer>> findSimilarTo(String word) {
        Collection<Map<String, Integer>> res = new ArrayList<Map<String, Integer>>();
        Collection<WordEntity> resword = wordRepository.findWordSimilarTo(word);
        Iterator<WordEntity> wi = resword.iterator();

        while (wi.hasNext()) {
            WordEntity w = wi.next();
            // Check the InComing word
            System.out.println("In come: " + w.word);
            for (SimilarToRelEntity smlt : w.getSimilarTo()) {
                if (smlt.startWord != null && smlt.endWord != null) {
                    System.out.println("\"" + smlt.startWord.word + "\" -> \"" + smlt.endWord.word + "\" | Score: "
                            + smlt.score.toString());
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
            for (SimilarToRelEntity smlf : w.getSimilarFrom()) {
                if (smlf.startWord != null && smlf.endWord != null) {
                    System.out.println("\"" + smlf.startWord.word + "\" -> \"" + smlf.endWord.word + "\" | Score: "
                            + smlf.score.toString());
                    Map<String, Integer> r = new HashMap<>();
                    r.put(smlf.startWord.word, smlf.score);
                    res.add(r);
                }
            }

        }
        return res;
    }

    // Create a SimilarTo relationship beetween two word
    @Transactional(readOnly = true)
    public SimilarToRelEntity createSimilarToRel(Long id1, Long id2, Integer score) {
        SimilarToRelEntity res = new SimilarToRelEntity();
        res = wordRepository.createSimilarToRel(id1, id2, score);
        return res;
    }

    // Set a score for a SimilarRel between 2 word, if the rel is null, create the
    // rel
    @Transactional(readOnly = true)
    public void setSimilarRelScore(Long from_id, Long to_id, Integer rel_score) {
        WordEntity fw = findOneWord(from_id);
        WordEntity tw = findOneWord(to_id);
        if (fw == null || tw == null) {
            return;
        }
        // TODO: Implement
    }

    // Add a pair of word, then set the SIMILAR_TO.score between them
    // If one of them (from Word, Relation, ToWord) existed, only update
    // If not, create full of word
    @Transactional(readOnly = true)
    public SimilarToRelEntity createPairOfSimilarWord_Full(WordDto w1, WordDto w2, Integer sc) {
        SimilarToRelEntity res = new SimilarToRelEntity();
        WordEntity word1 = new WordEntity();
        //Search through the spell to find first match
        for (int i = 0; i < w1.spell.size(); i++) {
            word1 = wordRepository.findOneWord(w1.word, w1.spell.get(i), w1.lang);
            if (word1.word.length() != 0) break;
        }
        WordEntity word2 = new WordEntity();
        //Search through the spell to find first match
        for (int i = 0; i < w2.spell.size(); i++) {
            word2 = wordRepository.findOneWord(w2.word, w2.spell.get(i), w2.lang);
            if (word2.word.length() != 0) break;
        }

        if (word1 != null) {
            word1 = wordRepository.createOneWord(w1.word, w1.spell, w1.lang);
        }

        if (word2 != null) {
            word2 = wordRepository.createOneWord(w2.word, w2.spell, w2.lang);
        }
        
        res = wordRepository.createSimilarToRel(word1.getId(), word2.getId(), sc);
        
        return res;
    }

    /* 2. API for Related Relationship
     *  - findRelatedTo(String word) -> Collection of word:score where given word related to
     *  - findRelatedFrom(String word) -> Collection of word:score which related to given word
     *  - createRelatedToRel(Long id1, Long id2, Integer score) -> Create a RelatedTo relation and return new one
     *  - setRelatedToRelScore(Long from_id, Long to_id, Integer rel_score) -> Set a score for a relation and create if not existed.
     *  - createPairOfRelatedWord_Full(WordDto w1, WordDto w2, Integer sc)
     *      -> Create relation between word and setting full information
     */

    // Find a collection of word:score where given word related to
    @Transactional(readOnly = true)
    public Collection<Map<WordEntity, Integer>> findRelatedTo(String word) {
        Collection<Map<WordEntity, Integer>> res = new ArrayList<>();
        // TODO: Implement
        return res;

    } 

    // Find a collection of word:score which related to given word
    @Transactional(readOnly = true)
    public Collection<Map<WordEntity, Integer>> findRelatedFrom(String word) {
        Collection<Map<WordEntity, Integer>> res = new ArrayList<>();
        // TODO: Implement
        return res;
    }

    // Create a RelatedTo relationship beetween two word and return the new one
    @Transactional(readOnly = true)
    public RelatedToRelEntity createRelatedToRel(Long id1, Long id2, Integer score) {
        RelatedToRelEntity res = new RelatedToRelEntity();
        res = wordRepository.createRelatedToRel(id1, id2, score);
        return res;
    }

    // Set a score for a relation and create if not existed
    @Transactional(readOnly = true)
    public RelatedToRelEntity setRelatedToRel(Long id1, Long id2, Integer score) {
        RelatedToRelEntity res = new RelatedToRelEntity();
        // TODO: Implement
        return res;
    }

    @Transactional(readOnly = true)
    public RelatedToRelEntity createPairOfRelateWord_Full(WordDto w1, WordDto w2, Integer sc) {
        RelatedToRelEntity res = new RelatedToRelEntity();
        // TODO: Implement
        return res;
    }

    /* 3. API for TranslateTo Relationship
     *  - findTranslateTo(String word) -> Collection of word:score where given word translate to
     *  - findTranslateFrom(String word) -> Collection of word:score which translate to given word
     *  - createTranslateToRel(Long id1, Long id2, Integer score) -> Create a TranslateTo relation and return new one
     *  - setTranslateToRel(Long from_id, Long to_id, Integer rel_score) -> Set a score for a relation and create if not existed.
     *  - createPairOfSimilarWord_Full(WordDto w1, WordDto w2, Integer sc)
     *      -> Create relation between word and setting full information
     */

    // Find a collection of word:score where given word translate to
    @Transactional(readOnly = true)
    public Collection<Map<WordEntity, Integer>> findTranslateTo(String word) {
        Collection<Map<WordEntity, Integer>> res = new ArrayList<>();
        // TODO: Implement
        return res;

    } 

    // Find a collection of word:score which translate to given word
    @Transactional(readOnly = true)
    public Collection<Map<WordEntity, Integer>> findTranslateFrom(String word) {
        Collection<Map<WordEntity, Integer>> res = new ArrayList<>();
        // TODO: Implement
        return res;
    }

    // Create a Translate relationship beetween two word and return the new one
    @Transactional(readOnly = true)
    public TranslateToRelEntity createTranslateToRel(Long id1, Long id2, Integer score) {
        TranslateToRelEntity res = new TranslateToRelEntity();
        res = wordRepository.createTranslateToRel(id1, id2, score);
        return res;
    }

    // Set a score for a relation and create if not existed
    @Transactional(readOnly = true)
    public TranslateToRelEntity setTranslateToRel(Long id1, Long id2, Integer score) {
        TranslateToRelEntity res = new TranslateToRelEntity();
        // TODO: Implement
        return res;
    }

    @Transactional(readOnly = true)
    public TranslateToRelEntity createPairOfTranslateWord_Full(WordDto w1, WordDto w2, Integer sc) {
        TranslateToRelEntity res = new TranslateToRelEntity();
        // TODO: Implement
        return res;
    }

    /* 4. API for Opposite Relationship
     *  - findOppositeTo(String word) -> Collection of word:score where given word opposite to
     *  - findOppositeFrom(String word) -> Collection of word:score which opposite to given word
     *  - createOppositeToRel(Long id1, Long id2, Integer score) -> Create a OppositeTo relation and return new one
     *  - setOppositeRelScore(Long from_id, Long to_id, Integer rel_score) -> Set a score for a relation and create if not existed.
     *  - createPairOfOppositeWord_Full(WordDto w1, WordDto w2, Integer sc)
     *      -> Create relation between word and setting full information
     */

    // Find a collection of word:score where given word translate to
    @Transactional(readOnly = true)
    public Collection<Map<WordEntity, Integer>> findOppositeTo(String word) {
        Collection<Map<WordEntity, Integer>> res = new ArrayList<>();
        // TODO: Implement
        return res;

    } 

    // Find a collection of word:score which translate to given word
    @Transactional(readOnly = true)
    public Collection<Map<WordEntity, Integer>> findOppositeFrom(String word) {
        Collection<Map<WordEntity, Integer>> res = new ArrayList<>();
        // TODO: Implement
        return res;
    }

    //Create a Opposite to Relationship between two word
    @Transactional(readOnly = true)
    public OppositeToRelEntity createOppositeToRel(Long id1, Long id2, Integer score) {
        OppositeToRelEntity res = new OppositeToRelEntity();
        res = wordRepository.createOppositeToRel(id1, id2, score);
        return res;
    }

    // Set a score for a relation and create if not existed
    @Transactional(readOnly = true)
    public OppositeToRelEntity setOppositeToRel(Long id1, Long id2, Integer score) {
        OppositeToRelEntity res = new OppositeToRelEntity();
        // TODO: Implement
        return res;
    }

    @Transactional(readOnly = true)
    public OppositeToRelEntity createPairOfOppositeWord_Full(WordDto w1, WordDto w2, Integer sc) {
        OppositeToRelEntity res = new OppositeToRelEntity();
        // TODO: Implement
        return res;
    }

    @Transactional(readOnly = true)
    public Collection<Map<String, Integer>> getMeansScore_debug(Long jid) {
        Collection<Map<String, Integer>> res = new ArrayList<>();

        JAENWordEntity jaw = this.jaEnDicService.getWordById(jid);
        
        ArrayList<String> jres = jaw.getAllEnMeans();
        //System.out.println("------------");
        //System.out.println(jres.toString());
        //System.out.println("------------");

        ArrayList<String> eres = new ArrayList<>();

        for (int i = 0; i < jres.size(); i++) {
            ArrayList<ENVIWordEntity> ew = this.enViDicService.getWordByWord(jres.get(i));
            for (int j = 0; j < ew.size(); j++) {
                ArrayList<String> mjres = ew.get(j).getAllViMeans();
                for (int k = 0; k < mjres.size(); k++) {
                    eres.add(mjres.get(k));
                }
            }
        }

        if (eres.size() != 0) {
           // System.out.println(jaw.getk_ele().get(0).getkeb());
            //System.out.println(eres.toString());
        } else {
            if (jaw.getk_ele() != null) {
                System.out.println("【" + jaw.getk_ele().get(0).getkeb() + "】");
                try(FileWriter fw = new FileWriter("myfile.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
                {
                    out.println("【" + jaw.getk_ele().get(0).getkeb() + "】");
                } catch (IOException e) {
                    //exception handling left as an exercise for the reader
                }
            } else if (jaw.getr_ele() != null) {
                System.out.println("【" + jaw.getr_ele().get(0).getreb() + "】");
                
                try(FileWriter fw = new FileWriter("myfile.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
                {
                    out.println("【" + jaw.getr_ele().get(0).getreb() + "】");
                } catch (IOException e) {
                    //exception handling left as an exercise for the reader
                }
            }
            System.out.println(jaw.getAllEnMeans().toString());
            try(FileWriter fw = new FileWriter("myfile.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
            {
                out.println(jaw.getAllEnMeans().toString());
            } catch (IOException e) {
                //exception handling left as an exercise for the reader
            }
            //System.out.println("nomean");
        }
        
        return res;
    }

    @Transactional(readOnly = true)
    public Collection<Map<String, Integer>> getMeansScore(Long jid) {
        Collection<Map<String, Integer>> res = new ArrayList<>();

        JAENWordEntity jaw = this.jaEnDicService.getWordById(jid);
        
        ArrayList<String> jres = jaw.getAllEnMeans();

        ArrayList<String> eres = new ArrayList<>();

        for (int i = 0; i < jres.size(); i++) {
            ArrayList<ENVIWordEntity> ew = this.enViDicService.getWordByWord(jres.get(i));
            for (int j = 0; j < ew.size(); j++) {
                ArrayList<String> mjres = ew.get(j).getAllViMeans();
                for (int k = 0; k < mjres.size(); k++) {
                    eres.add(mjres.get(k));
                }
            }
        }

        if (eres.size() != 0) {
            System.out.println(jaw.getk_ele().get(0).getkeb());
            System.out.println(eres.toString());
        } else {
            System.out.println("nomean");
        }
        
        return res;
    }
}