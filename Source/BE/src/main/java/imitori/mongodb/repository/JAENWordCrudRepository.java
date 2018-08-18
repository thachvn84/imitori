package imitori.mongodb.repository;

import java.util.Date;
import java.util.List;

import imitori.mongodb.entity.JAENWordEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

// This is an Interface.
// No need Annotation here
public interface JAENWordCrudRepository extends MongoRepository<JAENWordEntity, Long> { // Long: Type of JAWord ID.

}