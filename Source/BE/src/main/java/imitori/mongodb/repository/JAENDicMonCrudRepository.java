package imitori.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import imitori.mongodb.entity.JAENDicMonEntity;

// This is an Interface.
// No need Annotation here
public interface JAENDicMonCrudRepository extends MongoRepository<JAENDicMonEntity, Integer> { // Long: Type of JAWord ID.

}