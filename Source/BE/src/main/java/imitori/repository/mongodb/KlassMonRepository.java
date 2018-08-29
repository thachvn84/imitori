package imitori.repository.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import imitori.entity.mongodb.KlassMonEntity;

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

}