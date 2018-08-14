package imitori.services;

import java.util.*;

import imitori.neo4j.entity.SimilarToRelEntity;
import imitori.neo4j.entity.WordEntity;
import imitori.neo4j.repositories.WordRepository;
import imitori.mongodb.repository.JAWordRepository;
import imitori.mongodb.entity.ENWordEntity;
import imitori.mongodb.entity.ENWordEntity;
import imitori.mongodb.repository.ENWordCrudRepository;
import imitori.mongodb.repository.ENWordRepository;
import imitori.mongodb.repository.JAWordCrudRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnViDicService {
    private final static Logger LOG = LoggerFactory.getLogger(JaEnDicService.class);

    private final ENWordCrudRepository enWordCrudRepository;
    private final ENWordRepository enWordRepository;

    public EnViDicService(ENWordCrudRepository crud, ENWordRepository repo) {
        this.enWordCrudRepository = crud;
        this.enWordRepository = repo;
    }

    @Transactional(readOnly = true)
    public Long addOneWord(ENWordEntity word) {
        long id = this.enWordRepository.getMaxWordId() + 1;
        word.setid(id);
        this.enWordCrudRepository.insert(word);
        return id;
    }

    @Transactional(readOnly = true)
    public ENWordEntity getWordById(long id) {
        Optional<ENWordEntity> resq = this.enWordCrudRepository.findById(id);
        if (resq.isPresent()) {
            return resq.get();
        } else {
            return new ENWordEntity();
        }
    }

    @Transactional(readOnly = true)
    public Long getMaxId() {
        return this.enWordRepository.getMaxWordId();
    }

    @Transactional(readOnly = true)
    public ArrayList<String> getAllMeansById(Long id) {
        // Return the colletion of <Word: Id>
        ArrayList<String> res = new ArrayList<>();
        Optional<ENWordEntity> resq = this.enWordCrudRepository.findById(id);
        ENWordEntity word = new ENWordEntity();
        if (resq.isPresent()) {
            word = resq.get();
            ArrayList<String> mean = new ArrayList<>();
            mean = word.getAllMeans();
            for (int i = 0; i < mean.size(); i++) {
                res.add(mean.get(i));
            }
        } else {
            return res;
        }
        return res;
    }

    @Transactional(readOnly = true)
    public ArrayList<ENWordEntity> getWordByWord(String w) {
        ArrayList<ENWordEntity> res = new ArrayList<>();
        return this.enWordRepository.findByWord(w);
    }

}