package imitori.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import imitori.mongodb.entity.ENVIWordEntity;

// This is an Interface.
// No need Annotation here
public interface ENVIWordCrudRepository extends MongoRepository<ENVIWordEntity, Long> { // Long: Type of ENWord ID.

}