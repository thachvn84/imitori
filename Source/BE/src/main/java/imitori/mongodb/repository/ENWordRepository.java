package imitori.mongodb.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
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
}