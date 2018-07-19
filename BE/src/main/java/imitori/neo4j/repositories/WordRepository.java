package imitori.neo4j.repositories;

import java.util.Collection;

import imitori.neo4j.entity.WordEntity;
import imitori.neo4j.dto.WordDto;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface WordRepository extends Neo4jRepository<WordEntity, Long> {
    @Query("MATCH(w:Word{word:{w}}) RETURN w LIMIT 1")
    WordEntity findByWord(@Param("w") String word);

    Collection<WordEntity> findByWordLike(@Param("word") String word);

    @Query("MATCH (w:Word)-[r:USED_IN]->(s:Sentence) RETURN m, r, s LIMIT {limit}")
    Collection<WordEntity> usedInGraph(@Param("limit") int limit);

    @Query("CREATE(w:Word{word:{w}, kana:{k}, romaji:{ro}}) RETURN w")
    WordEntity addOneWord(@Param("w") String w, @Param("k") String k, @Param("ro") String ro);
}