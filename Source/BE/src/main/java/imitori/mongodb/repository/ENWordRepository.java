package imitori.mongodb.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import imitori.mongodb.entity.ENWordEntity;

@Repository
public class ENWordRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    public long getMaxWordId() {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "id"));
        query.limit(1);
        ENWordEntity maxObject = mongoTemplate.findOne(query, ENWordEntity.class);
        if (maxObject == null) {
            return 0L;
        }
        return maxObject.getid();
    }

    public ArrayList<ENWordEntity> findByWord(String w) {
        ArrayList<ENWordEntity> res = new ArrayList<>();
        Query query = new Query();
        query.addCriteria(new Criteria("word").is(w));
        List<ENWordEntity> mres = mongoTemplate.find(query, ENWordEntity.class);
        for (int i = 0; i < mres.size(); i++) {
            res.add(mres.get(i));
        }
        return res;
    }
}