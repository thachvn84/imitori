package imitori.repository.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import imitori.entity.mongodb.VIWordMonEntity;

// This is an Interface.
// No need Annotation here
public interface VIWordMonCrudRepository extends MongoRepository<VIWordMonEntity, Integer> { // Integer: Type of ENWord ID.

}