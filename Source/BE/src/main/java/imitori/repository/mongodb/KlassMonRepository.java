package imitori.repository.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import imitori.entity.mongodb.KlassMonEntity;
import imitori.utils.BEConstant;
import imitori.utils.MongoUtils;

@Repository
public class KlassMonRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    public Integer getMaxId() {
        Query query = new Query();
        query.limit(1);
        KlassMonEntity maxObject = mongoTemplate.findOne(query, KlassMonEntity.class);
        if (maxObject == null) {
            return 0;
        }
        return maxObject.id;
    }

    public Integer getWordCount() {
        return mongoTemplate.findAll(KlassMonEntity.class).size();
    }

    /*
     * Search a Klass by given string, and return this id if found, if not, return -1
     * Return value: 
     *  -1 : Not found
     *  id : ID of found Klass
     */
    public Integer searchKlass(String w) {
        Query query = new Query();
        query.limit(1);
        query.addCriteria(Criteria.where("klass").is(w));
        KlassMonEntity mr = mongoTemplate.findOne(query, KlassMonEntity.class);
        if (mr == null) {
            return BEConstant.WORD_NOT_FOUND;
        } else {
            return mr.id;
        }
    }

    /*
     * Search a klass, and return this id if found, if not, return -1
     * Comparision condition (AND conditions):
     *  - klass equal
     *  - hanviet equal
     *  - mean equal or one is null
     * Return value: 
     *  -1 : Not found
     *  id : ID of found word
     */
    public Integer searchKlass(KlassMonEntity k) {
        Query query = new Query();
        query.limit(1);
        query.addCriteria(Criteria.where("klass").is(k.klass));
        KlassMonEntity mr = mongoTemplate.findOne(query, KlassMonEntity.class);
        if (mr == null) {
            return BEConstant.WORD_NOT_FOUND;
        }
        if (mr.hanviet == null || mr.mean == null) {
            return BEConstant.WORD_FOUND_BUT_HAS_NULL_FIELD;
        }
        if (!mr.hanviet.equals(k.hanviet) || !mr.mean.equals(k.mean)) {
            return BEConstant.WORD_NOT_FOUND;
        }
        return -1;
    }

    /*
     *  searchAllByHanviet: Search and get multiple word that klass.hanviet equal hv
     *  Input:
     *      String hv: input word
     *      Integer option: 
     *          SEARCH_EQUAL: search with fully equal between String option
     *          SEARCH_CONTAIN: search with w is contain in hanviet
     *  Return value: 
     *      ArrayList of result
     */
    public ArrayList<KlassMonEntity> searchAllByHanviet(String hv, Integer option) {
        List<KlassMonEntity> res = new ArrayList<>();
        ArrayList<KlassMonEntity> ares = new ArrayList<>();
        
        Query query  = new MongoUtils().modifyCondition("hanviet", hv, option);
        res = mongoTemplate.find(query, KlassMonEntity.class);
        
        for (int i = 0; i < res.size(); i++) {
            ares.add(res.get(i));
        }
        return ares;
    }

    /*
     *  searchAllByMean: Same as search by hanviet, but key is mean
     *  Input & Return value are same searchAllByHanviet also.
     */
    public ArrayList<KlassMonEntity> searchAllByMean(String w, Integer option) {
        List<KlassMonEntity> res = new ArrayList<>();

        Query query = new MongoUtils().modifyCondition("mean", w, option);
        res = mongoTemplate.find(query, KlassMonEntity.class);

        ArrayList<KlassMonEntity> ares = new ArrayList<>();
        for (int i = 0; i < res.size(); i++) {
            ares.add(res.get(i));
        }
        return ares;
    }

    /*
     *  updateKlassById: Update a Klass by given id and w data
     *  This function will search word by id in dic, and update result with new data given by w
     *  Input:
     *      Integer id: The word ID
     *      KlassMonEntity w: The w data.
     *  Return value: 
     *      Klass id
     */
    public Integer updateKlassById(Integer id, KlassMonEntity kl) {
        Query query = new Query();

        query.addCriteria(Criteria.where("id").is(id));
        query.limit(1);

        KlassMonEntity res = mongoTemplate.findOne(query, KlassMonEntity.class);
        if (res != null) {
            res.updateFrom(kl);
        } else {
            return BEConstant.WORD_NOT_FOUND;
        }
        
        return res.id;
    }
}