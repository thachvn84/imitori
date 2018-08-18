package imitori.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import imitori.mongodb.entity.JAENWordEntity;

// This is an Interface.
// No need Annotation here
public interface JAENWordCrudRepository extends MongoRepository<JAENWordEntity, Long> { // Long: Type of JAWord ID.

}