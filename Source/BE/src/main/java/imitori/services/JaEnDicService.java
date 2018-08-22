package imitori.services;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import imitori.mongodb.entity.JAENDicMonEntity;
import imitori.mongodb.repository.JAENDicMonCrudRepository;
import imitori.mongodb.repository.JAENDicMonRepository;

@Service
public class JaEnDicService {
    private final static Logger LOG = LoggerFactory.getLogger(JaEnDicService.class);

    private final JAENDicMonCrudRepository jaENDicMonCrudRepository;
    private final JAENDicMonRepository jaENDicMonRepository;

    public JaEnDicService(JAENDicMonCrudRepository crud, JAENDicMonRepository repo) {
        this.jaENDicMonCrudRepository = crud;
        this.jaENDicMonRepository = repo;
    }

    @Transactional(readOnly = true)
    public Integer addOneWord(JAENDicMonEntity word) {
        Integer id = this.jaENDicMonRepository.getMaxWordId() + 1;
        word.setid(id);
        this.jaENDicMonCrudRepository.insert(word);
        return id;
    }

    @Transactional(readOnly = true)
    public JAENDicMonEntity getWordById(Integer id) {
        Optional<JAENDicMonEntity> resq = this.jaENDicMonCrudRepository.findById(id);
        if (resq.isPresent()) {
            return resq.get();
        } else {
            return new JAENDicMonEntity();
        }
    }

    @Transactional(readOnly = true)
    public Integer getMaxId() {
        return this.jaENDicMonRepository.getMaxWordId();
    }

    @Transactional(readOnly = true)
    public ArrayList<String> getAllMeansById(Integer id) {
        // Return the colletion of <Word: Id>
        ArrayList<String> res = new ArrayList<>();
        Optional<JAENDicMonEntity> resq = this.jaENDicMonCrudRepository.findById(id);
        JAENDicMonEntity word = new JAENDicMonEntity();
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
        System.out.println(res.toString());
        return res;
    }

}