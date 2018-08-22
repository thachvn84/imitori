package imitori.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import imitori.dto.JAWordDto;
import imitori.dto.KanjiClassDto;
import imitori.dto.KanjiDto;
import imitori.dto.KunyomiDto;
import imitori.dto.SentenceDto;
import imitori.dto.VIWordDto;
import imitori.mongodb.entity.ENVIDicMonEntity;
import imitori.mongodb.entity.JAENDicMonEntity;
import imitori.neo4j.dto.WordDto;
import imitori.neo4j.entity.OppositeToRelNeoEntity;
import imitori.neo4j.entity.RelatedToRelNeoEntity;
import imitori.neo4j.entity.SimilarToRelNeoEntity;
import imitori.neo4j.entity.TranslateToRelNeoEntity;
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
    public void deleteOneWord(Integer id) {
        wordRepository.deleteOneWord(id);
    }

    @Transactional(readOnly = true)
    public WordEntity findOneWord(String word) {
        WordEntity result = wordRepository.findOneWord(word);
        return result;
    }

    @Transactional(readOnly = true)
    public WordEntity findOneWord(Integer id) {
        WordEntity result = wordRepository.findOneWord(id);
        return result;
    }

    /* 1. API for SimilarTo Relationship
     *  - findSimilarTo(String word) -> Collection of word:score where given word SimilarTo
     *  - findSimilarFrom(String word) -> Collection of word:score which similar to given word
     *  - createSimilarToRel(Integer id1, Integer id2, Integer score) -> Create a SimilarTo relation and return new one
     *  - setSimilarRelScore(Integer from_id, Integer to_id, Integer rel_score) -> Set a score for a relation and create if not existed.
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
            for (SimilarToRelNeoEntity smlt : w.getSimilarTo()) {
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
            for (SimilarToRelNeoEntity smlf : w.getSimilarFrom()) {
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
    public SimilarToRelNeoEntity createSimilarToRel(Integer id1, Integer id2, Integer score) {
        SimilarToRelNeoEntity res = new SimilarToRelNeoEntity();
        res = wordRepository.createSimilarToRel(id1, id2, score);
        return res;
    }

    // Set a score for a SimilarRel between 2 word, if the rel is null, create the
    // rel
    @Transactional(readOnly = true)
    public void setSimilarRelScore(Integer from_id, Integer to_id, Integer rel_score) {
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
    public SimilarToRelNeoEntity createPairOfSimilarWord_Full(WordDto w1, WordDto w2, Integer sc) {
        SimilarToRelNeoEntity res = new SimilarToRelNeoEntity();
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
     *  - createRelatedToRel(Integer id1, Integer id2, Integer score) -> Create a RelatedTo relation and return new one
     *  - setRelatedToRelScore(Integer from_id, Integer to_id, Integer rel_score) -> Set a score for a relation and create if not existed.
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
    public RelatedToRelNeoEntity createRelatedToRel(Integer id1, Integer id2, Integer score) {
        RelatedToRelNeoEntity res = new RelatedToRelNeoEntity();
        res = wordRepository.createRelatedToRel(id1, id2, score);
        return res;
    }

    // Set a score for a relation and create if not existed
    @Transactional(readOnly = true)
    public RelatedToRelNeoEntity setRelatedToRel(Integer id1, Integer id2, Integer score) {
        RelatedToRelNeoEntity res = new RelatedToRelNeoEntity();
        // TODO: Implement
        return res;
    }

    @Transactional(readOnly = true)
    public RelatedToRelNeoEntity createPairOfRelateWord_Full(WordDto w1, WordDto w2, Integer sc) {
        RelatedToRelNeoEntity res = new RelatedToRelNeoEntity();
        // TODO: Implement
        return res;
    }

    /* 3. API for TranslateTo Relationship
     *  - findTranslateTo(String word) -> Collection of word:score where given word translate to
     *  - findTranslateFrom(String word) -> Collection of word:score which translate to given word
     *  - createTranslateToRel(Integer id1, Integer id2, Integer score) -> Create a TranslateTo relation and return new one
     *  - setTranslateToRel(Integer from_id, Integer to_id, Integer rel_score) -> Set a score for a relation and create if not existed.
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
    public TranslateToRelNeoEntity createTranslateToRel(Integer id1, Integer id2, Integer score) {
        TranslateToRelNeoEntity res = new TranslateToRelNeoEntity();
        res = wordRepository.createTranslateToRel(id1, id2, score);
        return res;
    }

    // Set a score for a relation and create if not existed
    @Transactional(readOnly = true)
    public TranslateToRelNeoEntity setTranslateToRel(Integer id1, Integer id2, Integer score) {
        TranslateToRelNeoEntity res = new TranslateToRelNeoEntity();
        // TODO: Implement
        return res;
    }

    @Transactional(readOnly = true)
    public TranslateToRelNeoEntity createPairOfTranslateWord_Full(WordDto w1, WordDto w2, Integer sc) {
        TranslateToRelNeoEntity res = new TranslateToRelNeoEntity();
        // TODO: Implement
        return res;
    }

    /* 4. API for Opposite Relationship
     *  - findOppositeTo(String word) -> Collection of word:score where given word opposite to
     *  - findOppositeFrom(String word) -> Collection of word:score which opposite to given word
     *  - createOppositeToRel(Integer id1, Integer id2, Integer score) -> Create a OppositeTo relation and return new one
     *  - setOppositeRelScore(Integer from_id, Integer to_id, Integer rel_score) -> Set a score for a relation and create if not existed.
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
    public OppositeToRelNeoEntity createOppositeToRel(Integer id1, Integer id2, Integer score) {
        OppositeToRelNeoEntity res = new OppositeToRelNeoEntity();
        res = wordRepository.createOppositeToRel(id1, id2, score);
        return res;
    }

    // Set a score for a relation and create if not existed
    @Transactional(readOnly = true)
    public OppositeToRelNeoEntity setOppositeToRel(Integer id1, Integer id2, Integer score) {
        OppositeToRelNeoEntity res = new OppositeToRelNeoEntity();
        // TODO: Implement
        return res;
    }

    @Transactional(readOnly = true)
    public OppositeToRelNeoEntity createPairOfOppositeWord_Full(WordDto w1, WordDto w2, Integer sc) {
        OppositeToRelNeoEntity res = new OppositeToRelNeoEntity();
        // TODO: Implement
        return res;
    }

    @Transactional(readOnly = true)
    public Collection<Map<String, Integer>> getMeansScore_debug(Integer jid) {
        Collection<Map<String, Integer>> res = new ArrayList<>();

        JAENDicMonEntity jaw = this.jaEnDicService.getWordById(jid);
        
        ArrayList<String> jres = jaw.getAllEnMeans();
        //System.out.println("------------");
        //System.out.println(jres.toString());
        //System.out.println("------------");

        ArrayList<String> eres = new ArrayList<>();

        for (int i = 0; i < jres.size(); i++) {
            ArrayList<ENVIDicMonEntity> ew = this.enViDicService.getWordByWord(jres.get(i));
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
    public Collection<Map<String, Integer>> getMeansScore(Integer jid) {
        Collection<Map<String, Integer>> res = new ArrayList<>();

        JAENDicMonEntity jaw = this.jaEnDicService.getWordById(jid);
        
        ArrayList<String> jres = jaw.getAllEnMeans();

        ArrayList<String> eres = new ArrayList<>();

        for (int i = 0; i < jres.size(); i++) {
            ArrayList<ENVIDicMonEntity> ew = this.enViDicService.getWordByWord(jres.get(i));
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

    @Transactional(readOnly = true)
    public JAWordDto searchJAWord(String word) {
        JAWordDto res = new JAWordDto();
        
        return res;
    }


    @Transactional(readOnly = true)
    public JAWordDto getDummyWord() {
        JAWordDto res = new JAWordDto();
        VIWordDto vi = new VIWordDto();
        res.id = 100;
        res.word = "日本語";
        KanjiDto kj = new KanjiDto();
        // 日
        kj.id = 0;
        kj.kanji = "日";
        kj.hanviet.add("Nhật");
        kj.hanviet.add("Nhựt");
        kj.mean.add(new VIWordDto(2, "Ngày", "n"));
        kj.mean.add(new VIWordDto(3, "Mặt trời", "n"));
        kj.onyomi.add("二");
        kj.onyomi.add("ニチ");
        kj.onyomi.add("ジツ");
        kj.kunyomi.add(new KunyomiDto("ひ", ""));
        kj.kunyomi.add(new KunyomiDto("か", ""));
        kj.stroke = 4;
        kj.klass.add(new KanjiClassDto(2, "Nhật", "Mặt trời"));//Bộ thủ
        kj.wordlist.add(new JAWordDto("日本"));
        kj.wordlist.add(new JAWordDto("二十日"));
        kj.wordlist.add(new JAWordDto("本日"));
        kj.wordlist.add(new JAWordDto("明日"));
        res.kanji.add(kj);
        // 本
        kj = new KanjiDto();
        kj.id = 1;
        kj.kanji = "本";
        kj.hanviet.add("Bản");
        kj.hanviet.add("Bổn");
        kj.mean.add(new VIWordDto(4, "Bản thể", "n"));
        kj.mean.add(new VIWordDto(5, "Bản chất", "n"));
        kj.onyomi.add("ホン");
        kj.kunyomi.add(new KunyomiDto("もと", ""));
        kj.klass.add(new KanjiClassDto(2, "Mộc", "Cây cối"));//Bộ thủ
        kj.stroke = 5;
        kj.wordlist.add(new JAWordDto("日本"));
        kj.wordlist.add(new JAWordDto("本来"));
        kj.wordlist.add(new JAWordDto("本当"));
        kj.wordlist.add(new JAWordDto("本社"));
        res.kanji.add(kj);
        //語
        kj = new KanjiDto();
        kj.id = 2;
        kj.kanji = "語";
        kj.hanviet.add("Ngữ");
        kj.mean.add(new VIWordDto(6, "Ngôn ngữ", "n"));
        kj.mean.add(new VIWordDto(7, "Từ ngữ", "n"));
        kj.onyomi.add("ゴ");
        kj.kunyomi.add( new KunyomiDto("かた", "らう"));
        kj.klass.add(new KanjiClassDto(2, "Ngôn", "Lời nói, ngôn ngữ"));//Bộ thủ
        kj.stroke = 14;
        kj.wordlist.add(new JAWordDto("国語"));
        kj.wordlist.add(new JAWordDto("外国語"));
        kj.wordlist.add(new JAWordDto("語彙"));
        kj.wordlist.add(new JAWordDto("言語"));
        res.kanji.add(kj);

        res.furigana = "にほんご";
        res.romaji = "nihongo";
        res.tl = "N";
        
        res.example.add(new SentenceDto(0, "日本語は難しいです","Tiếng Nhật khó"));
        res.example.add(new SentenceDto(1, "私は日本語で話せます","Tôi có thể nói chuyện bằng tiếng Nhật"));
        res.example.add(new SentenceDto(2, "日本語の漢字はとても難しいです","Kanji của tiếng Nhật rất là khó"));

        res.similarword.add(new JAWordDto("ニッポン語"));
        res.similarword.add(new JAWordDto("扶桑語"));
        res.similarword.add(new JAWordDto("ジャパン語"));

        res.transword.add(new VIWordDto(0, "Tiếng Nhật", "n"));
        res.transword.add(new VIWordDto(1, "Ngôn ngữ Nhật", "n"));

        res.relatedword.add(new JAWordDto("ベトナム語"));
        res.relatedword.add(new JAWordDto("中国語"));
        JAWordDto eigo = new JAWordDto("英語");
        eigo.relatedword.add(new JAWordDto("韓国語"));
        res.relatedword.add(eigo);
        
        return res;
    }
}