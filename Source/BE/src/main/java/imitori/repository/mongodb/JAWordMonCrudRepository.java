package imitori.repository.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import imitori.entity.mongodb.JAWordMonEntity;;

// This is an Interface.
// No need Annotation here
public interface JAWordMonCrudRepository extends MongoRepository<JAWordMonEntity, Integer> { // Integer: Type of ENWord ID.

}