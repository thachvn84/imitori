package imitori.repository.mongodb;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import imitori.entity.mongodb.KlassMonEntity;
import imitori.utils.BEConstant;

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
        return -1;
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
    public Integer searchKlass(KlassMonEntity k) {
        return -1;
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
        ArrayList<KlassMonEntity> res = new ArrayList<>();

        return res;
    }

    /*
     *  searchAllByMean: Same as search by hanviet, but key is mean
     *  Input & Return value are same searchAllByHanviet also.
     */
    public ArrayList<KlassMonEntity> searchAllByMean(String w, Integer option) {
        ArrayList<KlassMonEntity> res = new ArrayList<>();

        return res;
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
        return -1;
    }
}