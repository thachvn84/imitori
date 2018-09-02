package imitori.repository.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import imitori.entity.mongodb.JAENDicMonEntity;
import imitori.utils.BEConstant;
import imitori.utils.MongoUtils;

@Repository
public class JAENDicMonRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public Integer getMaxWordId() {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "id"));
        query.limit(1);
        JAENDicMonEntity maxObject = mongoTemplate.findOne(query, JAENDicMonEntity.class);
        if (maxObject == null) {
            return 0;
        }
        return maxObject.getid();
    }

    public int getWordCount() {
        return mongoTemplate.findAll(JAENDicMonEntity.class).size();
    }

    public void insertWord(Integer w) {
        JAENDicMonEntity word = new JAENDicMonEntity();
        word.setent_seq(w);

        mongoTemplate.insert(word);
    }

    /*
     * Search a word by given string with kanji, and return this id if found, if not, return -1
     * Return value:
     *  -1: Not found
     *  id: ID of found word
     */
    public Integer searchWordKeb(String w) {
        Query query = new Query();
        query.limit(1);
        query.addCriteria(Criteria.where("k_ele.keb").is(w));
        JAENDicMonEntity mr = mongoTemplate.findOne(query, JAENDicMonEntity.class);
        if (mr == null) {
            return BEConstant.WORD_NOT_FOUND;
        } else {
            return mr.id;
        }
    }

    /*
     * Search a word by given string with hiragana, and return this id if found, if not, return -1
     * Return value:
     *  -1: Not found
     *  id: ID of found word
     */
    public Integer searchWordReb(String w) {
        Query query = new Query();
        query.limit(1);
        query.addCriteria(Criteria.where("r_ele.reb").is(w));
        JAENDicMonEntity mr = mongoTemplate.findOne(query, JAENDicMonEntity.class);
        if (mr == null) {
            return BEConstant.WORD_NOT_FOUND;
        } else {
            return mr.id;
        }
    }

    /*
     * Search all by Keb
     * Input: 
     *  String w: input keb
     *  Integer option: 
     *      SEARCH_EQUAL: search with fully equal between String option
     *      SEARCH_CONTAIN: search with w is contain in keb
     *  Return value:
     *      ArrayList of result
     */
    public ArrayList<JAENDicMonEntity> searchAllByKeb(String w, Integer option) {
        List<JAENDicMonEntity> res = new ArrayList<>();
        ArrayList<JAENDicMonEntity> ares = new ArrayList<>();

        Query query = new MongoUtils().modifyCondition("k_ele.keb", w, option);
        res = mongoTemplate.find(query, JAENDicMonEntity.class);

        for (int i = 0; i < res.size();  i++) {
            ares.add(res.get(i));
        }
        return ares;
    }

    /*
     * Search all by Reb
     * Input: 
     *  String w: input reb
     *  Integer option: 
     *      SEARCH_EQUAL: search with fully equal between String option
     *      SEARCH_CONTAIN: search with w is contain in reb
     *  Return value:
     *      ArrayList of result
     */
    public ArrayList<JAENDicMonEntity> searchAllByReb(String w, Integer option) {
        List<JAENDicMonEntity> res = new ArrayList<>();
        ArrayList<JAENDicMonEntity> ares = new ArrayList<>();

        Query query = new MongoUtils().modifyCondition("r_ele.reb", w, option);
        res = mongoTemplate.find(query, JAENDicMonEntity.class);

        for (int i = 0; i < res.size();  i++) {
            ares.add(res.get(i));
        }
        return ares;
    }

    /*
     * updateWordById
     * Input: 
     *  Integer id: The word ID
     *  JAENDicMonEntity w: the w data.
     * Return value: 
     *  word id
     */
    public Integer updateWordById(Integer id, JAENDicMonEntity w) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        query.limit(1);

        JAENDicMonEntity res = mongoTemplate.findOne(query, JAENDicMonEntity.class);
        if (res != null) {
            res.updateFrom(w);
        } else {
            return BEConstant.WORD_NOT_FOUND;
        }
        return res.id;
    }
} 