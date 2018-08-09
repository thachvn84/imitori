package imitori.mongodb.repository;

import imitori.mongodb.entity.ENWordEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

// This is an Interface.
// No need Annotation here
public interface ENWordCrudRepository extends MongoRepository<ENWordEntity, Long> { // Long: Type of ENWord ID.

}