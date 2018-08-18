package imitori.services;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import imitori.mongodb.entity.JAENWordEntity;
import imitori.mongodb.repository.JAENWordCrudRepository;
import imitori.mongodb.repository.JAENWordRepository;

@Service
public class JaEnDicService {
    private final static Logger LOG = LoggerFactory.getLogger(JaEnDicService.class);

    private final JAENWordCrudRepository jaENWordCrudRepository;
    private final JAENWordRepository jaENWordRepository;

    public JaEnDicService(JAENWordCrudRepository crud, JAENWordRepository repo) {
        this.jaENWordCrudRepository = crud;
        this.jaENWordRepository = repo;
    }

    @Transactional(readOnly = true)
    public Long addOneWord(JAENWordEntity word) {
        long id = this.jaENWordRepository.getMaxWordId() + 1;
        word.setid(id);
        this.jaENWordCrudRepository.insert(word);
        return id;
    }

    @Transactional(readOnly = true)
    public JAENWordEntity getWordById(long id) {
        Optional<JAENWordEntity> resq = this.jaENWordCrudRepository.findById(id);
        if (resq.isPresent()) {
            return resq.get();
        } else {
            return new JAENWordEntity();
        }
    }

    @Transactional(readOnly = true)
    public Long getMaxId() {
        return this.jaENWordRepository.getMaxWordId();
    }

    @Transactional(readOnly = true)
    public ArrayList<String> getAllMeansById(Long id) {
        // Return the colletion of <Word: Id>
        ArrayList<String> res = new ArrayList<>();
        Optional<JAENWordEntity> resq = this.jaENWordCrudRepository.findById(id);
        JAENWordEntity word = new JAENWordEntity();
        if (resq.isPresent()) {
            word = resq.get();
            ArrayList<String> mean = new ArrayList<>();
            mean = word.getAllEnMeans();
            for (int i = 0; i < mean.size(); i++) {
                res.add(mean.get(i));
            }
        } else {
            return res;
        }
        return res;
    }

}