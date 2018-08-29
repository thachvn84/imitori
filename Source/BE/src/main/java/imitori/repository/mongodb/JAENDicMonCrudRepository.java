package imitori.repository.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import imitori.entity.mongodb.JAENDicMonEntity;

// This is an Interface.
// No need Annotation here
public interface JAENDicMonCrudRepository extends MongoRepository<JAENDicMonEntity, Integer> { // Long: Type of JAWord ID.

}