package imitori.repository.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import imitori.entity.mongodb.JAVIDicMonEntity;

@Repository
public class JAVIDicMonRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    public Integer getMaxId() {
        Query query = new Query();
        query.limit(1);
        JAVIDicMonEntity maxObject = mongoTemplate.findOne(query, JAVIDicMonEntity.class);
        if (maxObject == null) {
            return 0;
        }
        return maxObject.id;
    }

    public Integer getWordCount() {
        return mongoTemplate.findAll(JAVIDicMonEntity.class).size();
    }

    /*
     * Search a word, and return this id if found, if not, return -1
     * Comparision condition (AND conditions):
     *  - word equal
     *  - furigana equal
     *  - tl equal or one is null
     * Return value: 
     *  -1 : Not found
     *  id : ID of found word
     */
    public Integer searchWord(JAVIDicMonEntity w) {
        Query query = new Query();
        query.limit(1);
        query.addCriteria(Criteria.where("word").is(w.word));
        JAVIDicMonEntity mr = mongoTemplate.findOne(query, JAVIDicMonEntity.class);
        if (mr == null) {
            return -1;
        } 
        if (mr.furigana == null || mr.tl == null) {
            return -2;
        }
        if (!mr.furigana.equals(w.furigana) || !mr.tl.equals(w.tl)) {
            return -1;
        }
        
        return mr.id;
    }

    public ArrayList<JAVIDicMonEntity> searchAllByWord(String w, Integer option) {
        List<JAVIDicMonEntity> res = new ArrayList<>();
        ArrayList<JAVIDicMonEntity> ares = new ArrayList<>();
        res = mongoTemplate.find(new Query().addCriteria(Criteria.where("word").is(w)), JAVIDicMonEntity.class);
        for(int i = 0; i < res.size(); i++) {
            ares.add(res.get(i));
        }
        return ares;
    }

    public ArrayList<JAVIDicMonEntity> searchAllByFurigana(String w, Integer option) {
        List<JAVIDicMonEntity> res = new ArrayList<>();
        Query query = new Query();
        switch(option) {
            
        }
        res = mongoTemplate.find(new Query().addCriteria(Criteria.where("furigana").is(w)), JAVIDicMonEntity.class);
        
        ArrayList<JAVIDicMonEntity> ares = new ArrayList<>();
        for(int i = 0; i < res.size(); i++) {
            ares.add(res.get(i));
        }
        return ares;
    }

}