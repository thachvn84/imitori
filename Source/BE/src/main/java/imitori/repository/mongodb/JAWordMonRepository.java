package imitori.repository.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import imitori.entity.mongodb.JAWordMonEntity;
import imitori.utils.BEConstant;
import imitori.utils.MongoUtils;

@Repository
public class JAWordMonRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    public Integer getMaxId() {
        Query query = new Query();
        query.limit(1);
        JAWordMonEntity maxObject = mongoTemplate.findOne(query, JAWordMonEntity.class);
        if (maxObject == null) {
            return 0;
        }
        return maxObject.id;
    }

    public Integer getWordCount() {
        return mongoTemplate.findAll(JAWordMonEntity.class).size();
    }

    /*
     * Search a word by given string, and return this id if found, if not, return -1
     * Return value: 
     *  -1 : Not found
     *  id : ID of found word
     */
    public Integer searchWord(String w) {
        Query query = new Query();
        query.limit(1);
        query.addCriteria(Criteria.where("word").is(w));
        JAWordMonEntity mr = mongoTemplate.findOne(query, JAWordMonEntity.class);
        if (mr == null) {
            return BEConstant.WORD_NOT_FOUND;
        } else {
            return mr.id;
        }
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
    public Integer searchWord(JAWordMonEntity w) {
        Query query = new Query();
        query.limit(1);
        query.addCriteria(Criteria.where("word").is(w.word));
        JAWordMonEntity mr = mongoTemplate.findOne(query, JAWordMonEntity.class);
        if (mr == null) {
            return BEConstant.WORD_NOT_FOUND;
        } 
        if (mr.furi == null || mr.tl == null) {
            return BEConstant.WORD_FOUND_BUT_HAS_NULL_FIELD;
        }
        if (!mr.furi.equals(w.furi) || !mr.tl.equals(w.tl)) {
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
    public ArrayList<JAWordMonEntity> searchAllByWord(String w, Integer option) {
        List<JAWordMonEntity> res = new ArrayList<>();
        ArrayList<JAWordMonEntity> ares = new ArrayList<>();

        Query query = MongoUtils.modifyCondition("word", w, option);
        res = mongoTemplate.find(query, JAWordMonEntity.class);

        for (int i = 0; i < res.size(); i++) {
            ares.add(res.get(i));
        }
        return ares;
    }
    /*
     *  searchAllByFurigana: Same as search by word, but key is furigana
     *  Input & Return value are same searchAllByWord also.
     */
    public ArrayList<JAWordMonEntity> searchAllByFurigana(String w, Integer option) {
        List<JAWordMonEntity> res = new ArrayList<>();

        Query query = MongoUtils.modifyCondition("furigana", w, option);
        res = mongoTemplate.find(query, JAWordMonEntity.class);

        ArrayList<JAWordMonEntity> ares = new ArrayList<>();
        for (int i = 0; i < res.size(); i++) {
            ares.add(res.get(i));
        }
        return ares;
    }
    /*
     *  updateWordById: Update a word by given id and w data
     *  This function will search word by id in dic, and update result with new data given by w
     *  Input:
     *      Integer id: The word ID
     *      JAWordMonEntity w: The w data.
     *  Return value: 
     *      word id
     */
    public Integer updateWordById(Integer id, JAWordMonEntity w) {
        Query query = new Query();

        query.addCriteria(Criteria.where("id").is(id));
        query.limit(1);

        JAWordMonEntity res = mongoTemplate.findOne(query, JAWordMonEntity.class);
        if (res != null) {
            res.updateFrom(w);
        } else {
            return BEConstant.WORD_NOT_FOUND;
        }

        return res.id;
    }
}