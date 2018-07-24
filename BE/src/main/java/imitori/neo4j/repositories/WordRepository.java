package imitori.neo4j.repositories;


import imitori.neo4j.entity.WordEntity;

import java.util.Collection;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface WordRepository extends Neo4jRepository<WordEntity, Long> {

    //Add a word
    @Query("CREATE (w: Word{word:{w}, spell: {sp}, lang: {l}}) return w")
    WordEntity createOneWord(@Param("w") String word, 
                             @Param("sp") String spell, 
                             @Param("l") String lang);

    //Find only one word by word
    @Query("MATCH(w:Word{word:{w}}) RETURN w LIMIT 1")
    WordEntity findOneWord(@Param("w") String word);

    //Find only one word by word and lang
    @Query("MATCH(w:Word{word:{w}, lang: {l}}) return w LIMIT 1")
    WordEntity findOneWord(@Param("w") String word, @Param("l") String lang);

    //Find only one word by id
    @Query("MATCH(w:Word) WHERE ID(w) = {i} RETURN w LIMIT 1")
    WordEntity findOneWord(@Param("i") Long id);

    @Query("MATCH(w:Word{word:{w}})-[r: SIMILAR_TO]->(m) RETURN w,r,m")
    Collection<WordEntity> findWordSimilarTo(@Param("w") String word);

    @Query("MATCH(w:Word{word:{w}})<-[r: SIMILAR_TO]-(m) RETURN w,r,m")
    Collection<WordEntity> findWordSimilarFrom(@Param("w") String word);

    @Query("MATCH (n:Word), (m:Word) " +
           "WHERE ID(n)={id1} and ID(m)={id2} " +
           "CREATE UNIQUE (n)-[r:SIMILAR_TO{score: {s}}]->(m) " +
           "RETURN n,r,m"
    )
    Collection<WordEntity> createSimilarToRel(@Param("id1") Long id1, 
                                              @Param("id2") Long id2, 
                                              @Param("s") Integer score);
}