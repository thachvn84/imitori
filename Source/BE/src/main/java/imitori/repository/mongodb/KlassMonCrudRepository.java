package imitori.repository.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import imitori.entity.mongodb.KlassMonEntity;

public interface KlassMonCrudRepository extends MongoRepository<KlassMonEntity, Integer> {

}