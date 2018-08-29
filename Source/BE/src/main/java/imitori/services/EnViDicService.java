package imitori.services;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import imitori.entity.mongodb.ENVIDicMonEntity;
import imitori.repository.mongodb.ENVIDicMonCrudRepository;
import imitori.repository.mongodb.ENVIDicMonRepository;

@Service
public class EnViDicService {
    private final static Logger LOG = LoggerFactory.getLogger(JaEnDicService.class);

    private final ENVIDicMonCrudRepository enVIWordCrudRepository;
    private final ENVIDicMonRepository enVIWordRepository;

    public EnViDicService(ENVIDicMonCrudRepository crud, ENVIDicMonRepository repo) {
        this.enVIWordCrudRepository = crud;
        this.enVIWordRepository = repo;
    }

    @Transactional(readOnly = true)
    public Integer addOneWord(ENVIDicMonEntity word) {
        Integer id = this.enVIWordRepository.getMaxWordId() + 1;
        word.setid(id);
        this.enVIWordCrudRepository.insert(word);
        return id;
    }

    @Transactional(readOnly = true)
    public ENVIDicMonEntity getWordById(Integer id) {
        Optional<ENVIDicMonEntity> resq = this.enVIWordCrudRepository.findById(id);
        if (resq.isPresent()) {
            return resq.get();
        } else {
            return new ENVIDicMonEntity();
        }
    }

    @Transactional(readOnly = true)
    public Integer getMaxId() {
        return this.enVIWordRepository.getMaxWordId();
    }

    @Transactional(readOnly = true)
    public ArrayList<String> getAllMeansById(Integer id) {
        // Return the colletion of <Word: Id>
        ArrayList<String> res = new ArrayList<>();
        Optional<ENVIDicMonEntity> resq = this.enVIWordCrudRepository.findById(id);
        ENVIDicMonEntity word = new ENVIDicMonEntity();
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
    public ArrayList<ENVIDicMonEntity> getWordByWord(String w) {
        ArrayList<ENVIDicMonEntity> res = new ArrayList<>();
        return this.enVIWordRepository.findByWord(w);
    }

}