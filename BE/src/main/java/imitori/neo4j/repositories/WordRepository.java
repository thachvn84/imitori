package imitori.neo4j.repositories;

import java.util.Collection;

import imitori.neo4j.entity.WordEntity;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface WordRepository extends Neo4jRepository<WordEntity, Long> {
    WordEntity findByWord(@Param("word") String word);

    Collection<WordEntity> findByWordLike(@Param("word") String word);

    @Query("MATCH (w:Word)-[r:USED_IN]->(s:Sentence) RETURN m, r, s LIMIT {limit}")
    Collection<WordEntity> usedInGraph(@Param("limit") int limit);
}