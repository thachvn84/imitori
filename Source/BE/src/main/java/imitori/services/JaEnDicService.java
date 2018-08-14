package imitori.services;

import java.util.*;

import imitori.neo4j.entity.SimilarToRelEntity;
import imitori.neo4j.entity.WordEntity;
import imitori.neo4j.repositories.WordRepository;
import imitori.mongodb.repository.JAWordRepository;
import imitori.mongodb.entity.JAWordEntity;
import imitori.mongodb.entity.JAWordEntity.sense_Class;
import imitori.mongodb.repository.JAWordCrudRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JaEnDicService {
    private final static Logger LOG = LoggerFactory.getLogger(JaEnDicService.class);

    private final JAWordCrudRepository jaWordCrudRepository;
    private final JAWordRepository jaWordRepository;

    public JaEnDicService(JAWordCrudRepository crud, JAWordRepository repo) {
        this.jaWordCrudRepository = crud;
        this.jaWordRepository = repo;
    }

    @Transactional(readOnly = true)
    public Long addOneWord(JAWordEntity word) {
        long id = this.jaWordRepository.getMaxWordId() + 1;
        word.setid(id);
        this.jaWordCrudRepository.insert(word);
        return id;
    }

    @Transactional(readOnly = true)
    public JAWordEntity getWordById(long id) {
        Optional<JAWordEntity> resq = this.jaWordCrudRepository.findById(id);
        if (resq.isPresent()) {
            return resq.get();
        } else {
            return new JAWordEntity();
        }
    }

    @Transactional(readOnly = true)
    public Long getMaxId() {
        return this.jaWordRepository.getMaxWordId();
    }

    @Transactional(readOnly = true)
    public ArrayList<String> getAllMeansById(Long id) {
        // Return the colletion of <Word: Id>
        ArrayList<String> res = new ArrayList<>();
        Optional<JAWordEntity> resq = this.jaWordCrudRepository.findById(id);
        JAWordEntity word = new JAWordEntity();
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

}