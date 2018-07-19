package imitori.neo4j.services;

import java.util.*;

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
    public WordEntity findByWord(String word) {
        LOG.debug("findByWord" + word);
        WordEntity result = wordRepository.findByWord(word);
        return result;
    }

    @Transactional(readOnly = true)
    public Collection<WordEntity> findByWordLike(String word) {
        LOG.debug("findByWordLike" + word);
        Collection<WordEntity> result = wordRepository.findByWordLike(word);
        return result;
    }
}