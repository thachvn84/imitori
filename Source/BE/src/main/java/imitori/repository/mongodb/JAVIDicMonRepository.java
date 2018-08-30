package imitori.repository.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import imitori.entity.mongodb.JAVIDicMonEntity;
import imitori.utils.BEConstant;

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
            return BEConstant.WORD_NOT_FOUND;
        } 
        if (mr.furigana == null || mr.tl == null) {
            return BEConstant.WORD_FOUND_BUT_HAS_NULL_FIELD;
        }
        if (!mr.furigana.equals(w.furigana) || !mr.tl.equals(w.tl)) {
            return BEConstant.WORD_NOT_FOUND;
        }
        
        return mr.id;
    }

    /*
     *  modifyCondition: Modify the Query condition to search.
     */
    private Query modifyCondition(String key, String value, int option) {
        Query query = new Query();
        switch(option) {
            case BEConstant.SEARCH_EQUAL:{
                query.addCriteria(Criteria.where(key).is(value));
            } break;
            case BEConstant.SEARCH_CONTAIN: {
                query.addCriteria(Criteria.where(key).in(value));
            } break;
            default: {

            } break;
        }
        return query;
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
    public ArrayList<JAVIDicMonEntity> searchAllByWord(String w, Integer option) {
        List<JAVIDicMonEntity> res = new ArrayList<>();
        ArrayList<JAVIDicMonEntity> ares = new ArrayList<>();

        Query query = modifyCondition("word", w, option);

        res = mongoTemplate.find(query, JAVIDicMonEntity.class);
        for(int i = 0; i < res.size(); i++) {
            ares.add(res.get(i));
        }
        return ares;
    }

    /*
     *  searchAllByFurigana: Same as search by word, but key is furigana
     *  Input & Return value are same searchAllByWord also.
     */
    public ArrayList<JAVIDicMonEntity> searchAllByFurigana(String w, Integer option) {
        List<JAVIDicMonEntity> res = new ArrayList<>();

        Query query = modifyCondition("furigana", w, option);

        res = mongoTemplate.find(query, JAVIDicMonEntity.class);
        ArrayList<JAVIDicMonEntity> ares = new ArrayList<>();
        for(int i = 0; i < res.size(); i++) {
            ares.add(res.get(i));
        }
        return ares;
    }

    /*
     *  updateWordById: Update a word by given id and w data
     *  This function will search word by id in dic, and update result with new data given by w
     *  Input:
     *      Integer id: The word ID
     *      JAVIDicMonEntity w: The w data.
     *  Return value: 
     *      word id
     */
    public Integer updateWordById(Integer id, JAVIDicMonEntity w) {
        Query query = new Query();

        query.addCriteria(Criteria.where("id").is(id));
        query.limit(1);

        JAVIDicMonEntity res = mongoTemplate.findOne(query, JAVIDicMonEntity.class);
        if (res != null) {
            res.updateFrom(w);
        } else {
            return BEConstant.WORD_NOT_FOUND;
        }

        return res.id;
    }


}