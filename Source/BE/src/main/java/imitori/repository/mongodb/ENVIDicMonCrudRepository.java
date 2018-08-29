package imitori.repository.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import imitori.entity.mongodb.ENVIDicMonEntity;

// This is an Interface.
// No need Annotation here
public interface ENVIDicMonCrudRepository extends MongoRepository<ENVIDicMonEntity, Integer> { // Integer: Type of ENWord ID.

}