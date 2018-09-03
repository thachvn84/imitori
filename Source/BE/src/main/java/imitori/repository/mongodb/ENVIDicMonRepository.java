package imitori.repository.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import imitori.entity.mongodb.ENVIDicMonEntity;
import imitori.utils.BEConstant;
import imitori.utils.MongoUtils;

@Repository
public class ENVIDicMonRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    public Integer getMaxWordId() {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "id"));
        query.limit(1);
        ENVIDicMonEntity maxObject = mongoTemplate.findOne(query, ENVIDicMonEntity.class);
        if (maxObject == null) {
            return 0;
        }
        return maxObject.getid();
    }

    public Integer getWordCount() {
        return mongoTemplate.findAll(ENVIDicMonEntity.class).size();
    }

    /*
     * Search a ENVIDic by given word
     * Result value: 
     *  -1: Not found
     *  id: Found ID
     */
    public Integer searchENVIDic(String w) {
        Query query = new Query();
        query.limit(1);
        query.addCriteria(Criteria.where("word").is(w));
        ENVIDicMonEntity mr = mongoTemplate.findOne(query, ENVIDicMonEntity.class);
        if (mr == null) {
            return BEConstant.WORD_NOT_FOUND;
        }
        return mr.id;
    }
    /*
     * Search a ENVIDic by given Entity, and return thí id if found, if not, return -1
     * Search condition: 
     *  word
     * Return value: 
     *  -1: Not found
     *  id: Found ENVIDic id
     */
    public Integer searchENVIDic(ENVIDicMonEntity w) {
        Query query = new Query();
        query.limit(1);
        query.addCriteria(Criteria.where("word").is(w.word));
        ENVIDicMonEntity mr = mongoTemplate.findOne(query, ENVIDicMonEntity.class);
        if (mr == null) {
            return BEConstant.WORD_NOT_FOUND;
        }
        return mr.id;
    }

    /*
     * Search a ENVIDic by given string, and return this id if found, if not, return -1
     * Search option: 
     *  SEARCH_EQUAL
     *  SEARCH_CONTAINT
     * Return value: 
     *  -1: Not found
     *  id: found ENVIdic id
     * 
     */
    public ArrayList<ENVIDicMonEntity> findAllByWord(String w, Integer option) {
        ArrayList<ENVIDicMonEntity> res = new ArrayList<>();
        Query query = MongoUtils.modifyCondition("word", w, option);
        List<ENVIDicMonEntity> mres = mongoTemplate.find(query, ENVIDicMonEntity.class);
        for (int i = 0; i < mres.size(); i++) {
            res.add(mres.get(i));
        }
        return res;
    }

    /*
     * Update ENVIDic by ID
     * input 
     *  Integer id: The Word id
     *  ENVIDicMonEntity w: the Word data
     * Return value: 
     *  ENVIDic id
     */
    public Integer updateENVIDicById(Integer id, ENVIDicMonEntity w) {
        Query query = new Query();

        query.addCriteria(Criteria.where("id").is(id));
        query.limit(1);

        ENVIDicMonEntity res = mongoTemplate.findOne(query, ENVIDicMonEntity.class);
        if (res != null) {
            res.updateFrom(w);
        } else {
            return BEConstant.WORD_NOT_FOUND;
        }
        return res.id;
    }
}