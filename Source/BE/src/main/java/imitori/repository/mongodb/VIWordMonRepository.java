package imitori.repository.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import imitori.entity.mongodb.VIWordMonEntity;
import imitori.utils.BEConstant;
import imitori.utils.MongoUtils;

@Repository
public class VIWordMonRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    public Integer getMaxId() {
        Query query = new Query();
        query.limit(1);
        VIWordMonEntity maxObject = mongoTemplate.findOne(query, VIWordMonEntity.class);
        if (maxObject == null) {
            return 0;
        }
        return maxObject.id;
    }

    public Integer getWordCount() {
        return mongoTemplate.findAll(VIWordMonEntity.class).size();
    }

    /*
     * Search a word by given string, and return this id if found, if not, return -1
     * Return value: 
     *  -1: Not found
     *  id: ID of found word
     */
    public Integer searchWord(String w) {
        Query query = new Query();
        query.limit(1);
        query.addCriteria(Criteria.where("word").is(w));
        VIWordMonEntity mr = mongoTemplate.findOne(query, VIWordMonEntity.class);
        if (mr == null) {
            return BEConstant.WORD_NOT_FOUND;
        } else {
            return mr.id;
        }
    }

    /*
     * Search a word, and return this id if found, if not, return -1
     * Word comparition condtion(AND condition)
     *  word equal
     *  tl equal
     *  chuyennganh equal (null is OK)
     *  linh vuc equal (null is OK)
     */
    public Integer searchWord(VIWordMonEntity w) {
        Query query = new Query();
        query.limit(1);
        query.addCriteria(Criteria.where("word").is(w));
        VIWordMonEntity mr = mongoTemplate.findOne(query, VIWordMonEntity.class);
        if (mr == null) {
            return BEConstant.WORD_NOT_FOUND;
        }
        if (mr.tl == null || mr.chuyennganh == null || mr.linhvuc == null) {
            return BEConstant.WORD_FOUND_BUT_HAS_NULL_FIELD;
        }
        if (!mr.tl.equals(w.tl) || !mr.chuyennganh.equals(w.chuyennganh) || !mr.linhvuc.equals(w.linhvuc)) {
            return BEConstant.WORD_NOT_FOUND;
        }
        return mr.id;
    }

    /*
     *  searchAllByWord: Search and get multiple word that word.word equal w
     *  Input:
     *      String w: input word
     *      Integer option: 
     *          SEARCH_EQUAL: search with fully equal between String option
     *          SEARCH_CONTAIN: search with w is contain in word
     *  Return value: 
     *      ArrayList of result
     */
    public ArrayList<VIWordMonEntity> searchAllByWord(String w, Integer option) {
        List<VIWordMonEntity> res = new ArrayList<>();
        ArrayList<VIWordMonEntity> ares = new ArrayList<>();

        Query query = MongoUtils.modifyCondition("word", w, option);
        res = mongoTemplate.find(query, VIWordMonEntity.class);

        for (int i = 0; i < res.size(); i++) {
            ares.add(res.get(i));
        }
        return ares;
    }

    /*
     * updateWordById: Update a word by given id and w data
     * This function will search word by id in dic, and update result with new data given by w
     * Input:
     *  Integer id: The word ID
     *  VIWordMonEntity w: The w data
     * Return value: 
     *  word id
     */
    public Integer updateWordById(Integer id, VIWordMonEntity w) {
        Query query = new Query();
        
        query.addCriteria(Criteria.where("id").is(id));
        query.limit(1);

        VIWordMonEntity res = mongoTemplate.findOne(query, VIWordMonEntity.class);
        if (res != null) {
            res.updateFrom(w);
        } else {
            return BEConstant.WORD_NOT_FOUND;
        }

        return res.id;
    }
}