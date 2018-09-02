package imitori.utils;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class MongoUtils {
    /*
     *  modifyCondition: Modify the Query condition to search.
     */
    public Query modifyCondition(String key, String value, int option) {
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

}