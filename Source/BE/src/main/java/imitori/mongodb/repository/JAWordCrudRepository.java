package imitori.mongodb.repository;

import java.util.Date;
import java.util.List;

import imitori.mongodb.entity.JAWordEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

// This is an Interface.
// No need Annotation here
public interface JAWordCrudRepository extends MongoRepository<JAWordEntity, Long> { // Long: Type of JAWord ID.

}