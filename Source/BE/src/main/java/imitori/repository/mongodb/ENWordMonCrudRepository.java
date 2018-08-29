package imitori.repository.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import imitori.entity.mongodb.ENWordMonEntity;

public interface ENWordMonCrudRepository extends MongoRepository<ENWordMonEntity, Integer> {

}