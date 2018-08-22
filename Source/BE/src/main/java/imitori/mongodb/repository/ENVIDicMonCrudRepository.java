package imitori.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import imitori.mongodb.entity.ENVIDicMonEntity;

// This is an Interface.
// No need Annotation here
public interface ENVIDicMonCrudRepository extends MongoRepository<ENVIDicMonEntity, Integer> { // Integer: Type of ENWord ID.

}