package imitori.neo4j.services;

import java.util.*;

import imitori.neo4j.entity.WordEntity;
import imitori.neo4j.dto.WordDto;
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
    public WordEntity findOneByWord(String word) {
        WordEntity result = wordRepository.findOneByWord(word);
        return result;
    }

    @Transactional(readOnly = true)
    public WordEntity findOneByKana(String word) {
        WordEntity result = wordRepository.findOneByKana(word);
        return result;
    }

    @Transactional(readOnly = true)
    public WordEntity findOneByRomaji(String word) {
        WordEntity result = wordRepository.findOneByRomaji(word);
        return result;
    }
    

    @Transactional(readOnly = true)
    public Collection<WordEntity> findAllByWord(String word) {
        LOG.debug("findByWordLike" + word);
        Collection<WordEntity> result = wordRepository.findAllByWord(word);
        return result;
    }

    @Transactional(readOnly = true)
    public WordEntity addOneWord(WordDto word) {
        WordEntity result;
        result = wordRepository.addOneWord(word.word, word.kana, word.romaji);
        return result;
    }
}