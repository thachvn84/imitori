package imitori.mongodb.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import imitori.mongodb.entity.ENVIDicMonEntity;

@Repository
public class ENVIDicMonRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    public long getMaxWordId() {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "id"));
        query.limit(1);
        ENVIDicMonEntity maxObject = mongoTemplate.findOne(query, ENVIDicMonEntity.class);
        if (maxObject == null) {
            return 0L;
        }
        return maxObject.getid();
    }

    public ArrayList<ENVIDicMonEntity> findByWord(String w) {
        ArrayList<ENVIDicMonEntity> res = new ArrayList<>();
        Query query = new Query();
        query.addCriteria(new Criteria("word").is(w));
        List<ENVIDicMonEntity> mres = mongoTemplate.find(query, ENVIDicMonEntity.class);
        for (int i = 0; i < mres.size(); i++) {
            res.add(mres.get(i));
        }
        return res;
    }
}