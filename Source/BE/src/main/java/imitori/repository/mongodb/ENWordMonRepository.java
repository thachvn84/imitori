package imitori.repository.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import imitori.entity.mongodb.ENWordMonEntity;
import imitori.utils.BEConstant;
import imitori.utils.MongoUtils;

@Repository
public class ENWordMonRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    public Integer getMaxId() {
        Query query = new Query();
        query.limit(1);
        ENWordMonEntity maxObject = mongoTemplate.findOne(query, ENWordMonEntity.class);
        if (maxObject == null) {
            return 0;
        }
        return maxObject.id;
    }

    public Integer getWordCount() {
        return mongoTemplate.findAll(ENWordMonEntity.class).size();
    }

    /*
     * Search an EnWord by given string, and return this id if found, if not, return -1
     * Comparison condition: 
     *  - word
     * Return value: 
     *  -1: Not found
     *  id: ID of found word.
     */   
    public Integer searchWord(String w) {
        Query query = new Query();
        query.limit(1);
        query.addCriteria(Criteria.where("word").is(w));

        ENWordMonEntity mr = mongoTemplate.findOne(query, ENWordMonEntity.class);
        if (mr == null) {
            return BEConstant.WORD_NOT_FOUND;
        } else {
            return mr.id;
        }
    }


    /*
     * Search an EnWord by given class, and return this id if found, if not, return -1
     * Comparison condition: 
     *  - word
     *  - tl
     * Return value: 
     *  -1: Not found
     *  id: ID of found word.
     */
    public Integer searchEnWord(ENWordMonEntity w) {
        Query query = new Query();
        query.limit(1);
        query.addCriteria(Criteria.where("word").is(w.word));

        ENWordMonEntity mr = mongoTemplate.findOne(query, ENWordMonEntity.class);
        if (mr == null) {
            return BEConstant.WORD_NOT_FOUND;
        }
        if (w.tl != null && !mr.tl.equals(w.tl)) {
            return BEConstant.WORD_FOUND_BUT_HAS_NULL_FIELD;
        }
        return mr.id;
    }

    /*
     * SearchAllByWord: Search and get mutiple word that equal in word.word
     * Input: 
     *  String w: input word
     *  Integer option: 
     *      SEARCH_EQUAL: search with fully equal between String option
     *      SEARCH_CONTAIN: search with w is contain in word
     *  Return value: 
     *      Arraylist of result
     */
    public ArrayList<ENWordMonEntity> searchAllByWord(String w, Integer option) {
        List<ENWordMonEntity> res = new ArrayList<>();
        ArrayList<ENWordMonEntity> ares = new ArrayList<>();

        Query query = MongoUtils.modifyCondition("word", w, option);
        res = mongoTemplate.find(query, ENWordMonEntity.class);

        for (int i = 0; i < res.size(); i++) {
            ares.add(res.get(i));
        }
        return ares;
    }

    /*
     * updateENWord by ID: Update an EnWord by given id and w data
     * This function will search word by id in DB, and update the result with new data.
     * Input: 
     *  Integer id : The word id
     *  ENWordMonEntity w the w data. 
     * Return value: 
     *  ENWord id
     */
    public Integer updateENWordById(Integer id, ENWordMonEntity w) {
        Query query = new Query();

        query.addCriteria(Criteria.where("id").is(id));
        query.limit(1);

        ENWordMonEntity res = mongoTemplate.findOne(query, ENWordMonEntity.class);
        if (res != null) {
            res.updateFrom(w);
        } else {
            return BEConstant.WORD_NOT_FOUND;
        }
        return res.id;
    }
}