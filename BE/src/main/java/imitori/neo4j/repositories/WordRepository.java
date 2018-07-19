package imitori.neo4j.repositories;

import java.util.Collection;

import imitori.neo4j.entity.WordEntity;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface WordRepository extends Neo4jRepository<WordEntity, Long> {
    //Find only one word by word
    @Query("MATCH(w:Word{word:{w}}) RETURN w LIMIT 1")
    WordEntity findOneByWord(@Param("w") String word);

    //Find only one word by kana
    @Query("MATCH(w:Word{kana:{k}}) RETURN w LIMIT 1")
    WordEntity findOneByKana(@Param("k") String kana);

    //Find only one word by Romaji
    @Query("MATCH(w:Word{romaji:{k}}) RETURN w LIMIT 1")
    WordEntity findOneByRomaji(@Param("k") String romaji);

    //Find all words
    Collection<WordEntity> findAllByWord(@Param("word") String word);

    @Query("MATCH (w:Word)-[r:USED_IN]->(s:Sentence) RETURN m, r, s LIMIT {limit}")
    Collection<WordEntity> usedInGraph(@Param("limit") int limit);

    @Query("CREATE(w:Word{word:{w}, kana:{k}, romaji:{ro}}) RETURN w")
    WordEntity addOneWord(@Param("w") String w, @Param("k") String k, @Param("ro") String ro);
    @Query("MATCH(n:Word) "  
           +"with n.word as w, collect(n) as nodes "
           +"where size(nodes) > 1 "
           +"foreach (n in tail(nodes)| detach delete n)")
    Long removeDuplicateWord();


}