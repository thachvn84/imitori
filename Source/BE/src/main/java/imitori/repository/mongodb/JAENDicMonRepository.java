package imitori.repository.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import imitori.entity.mongodb.JAENDicMonEntity;

@Repository
public class JAENDicMonRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public Integer getMaxWordId() {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "id"));
        query.limit(1);
        JAENDicMonEntity maxObject = mongoTemplate.findOne(query, JAENDicMonEntity.class);
        if (maxObject == null) {
            return 0;
        }
        return maxObject.getid();
    }

    public int getWordCount() {
        return mongoTemplate.findAll(JAENDicMonEntity.class).size();
    }

    public void insertWord(Integer w) {
        JAENDicMonEntity word = new JAENDicMonEntity();
        word.setent_seq(w);

        mongoTemplate.insert(word);
    }

} 