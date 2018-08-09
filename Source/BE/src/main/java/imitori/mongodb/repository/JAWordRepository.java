package imitori.mongodb.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import imitori.mongodb.entity.JAWordEntity;

@Repository
public class JAWordRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public long getMaxWordId() {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "id"));
        query.limit(1);
        JAWordEntity maxObject = mongoTemplate.findOne(query, JAWordEntity.class);
        if (maxObject == null) {
            return 0L;
        }
        return maxObject.getid();
    }

    public int getWordCount() {
        return mongoTemplate.findAll(JAWordEntity.class).size();
    }

    public void insertWord(long w) {
        JAWordEntity word = new JAWordEntity();
        word.setent_seq(w);

        mongoTemplate.insert(word);
    }

} 