package imitori.services.admin;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import imitori.mongodb.entity.ENVIWordEntity;
import imitori.mongodb.repository.ENVIWordCrudRepository;
import imitori.mongodb.repository.ENVIWordRepository;

@Service
public class EnViDicService {
    private final static Logger LOG = LoggerFactory.getLogger(JaEnDicService.class);

    private final ENVIWordCrudRepository enVIWordCrudRepository;
    private final ENVIWordRepository enVIWordRepository;

    public EnViDicService(ENVIWordCrudRepository crud, ENVIWordRepository repo) {
        this.enVIWordCrudRepository = crud;
        this.enVIWordRepository = repo;
    }

    @Transactional(readOnly = true)
    public Long addOneWord(ENVIWordEntity word) {
        long id = this.enVIWordRepository.getMaxWordId() + 1;
        word.setid(id);
        this.enVIWordCrudRepository.insert(word);
        return id;
    }

    @Transactional(readOnly = true)
    public ENVIWordEntity getWordById(long id) {
        Optional<ENVIWordEntity> resq = this.enVIWordCrudRepository.findById(id);
        if (resq.isPresent()) {
            return resq.get();
        } else {
            return new ENVIWordEntity();
        }
    }

    @Transactional(readOnly = true)
    public Long getMaxId() {
        return this.enVIWordRepository.getMaxWordId();
    }

    @Transactional(readOnly = true)
    public ArrayList<String> getAllMeansById(Long id) {
        // Return the colletion of <Word: Id>
        ArrayList<String> res = new ArrayList<>();
        Optional<ENVIWordEntity> resq = this.enVIWordCrudRepository.findById(id);
        ENVIWordEntity word = new ENVIWordEntity();
        if (resq.isPresent()) {
            word = resq.get();
            ArrayList<String> mean = new ArrayList<>();
            mean = word.getAllViMeans();
            for (int i = 0; i < mean.size(); i++) {
                res.add(mean.get(i));
            }
        } else {
            return res;
        }
        return res;
    }

    @Transactional(readOnly = true)
    public ArrayList<ENVIWordEntity> getWordByWord(String w) {
        ArrayList<ENVIWordEntity> res = new ArrayList<>();
        return this.enVIWordRepository.findByWord(w);
    }

}