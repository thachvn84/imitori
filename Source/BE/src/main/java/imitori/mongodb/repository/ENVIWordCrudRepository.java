package imitori.mongodb.repository;

import imitori.mongodb.entity.ENVIWordEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

// This is an Interface.
// No need Annotation here
public interface ENVIWordCrudRepository extends MongoRepository<ENVIWordEntity, Long> { // Long: Type of ENWord ID.

}