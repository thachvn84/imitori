package imitori.repository.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import imitori.entity.mongodb.VIWordMonEntity;

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

}