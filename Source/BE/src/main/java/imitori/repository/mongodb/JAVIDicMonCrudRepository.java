package imitori.repository.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import imitori.entity.mongodb.JAVIDicMonEntity;

// This is an Interface.
// No need Annotation here
public interface JAVIDicMonCrudRepository extends MongoRepository<JAVIDicMonEntity, Integer> { // Integer: Type of ENWord ID.

}