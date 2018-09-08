package imitori.repository.neo4j;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import imitori.entity.neo4j.rel.OppositeToRelNeoEntity;
import imitori.entity.neo4j.rel.RelatedToRelNeoEntity;
import imitori.entity.neo4j.rel.SimilarToRelNeoEntity;
import imitori.entity.neo4j.rel.TranslateToRelNeoEntity;
import imitori.entity.neo4j.WordNeoEntity;

public interface sampleRepository extends Neo4jRepository<WordNeoEntity, Integer> {

    // Add a word
    @Query("CREATE (w: Word{word:{w}, spell: {sp}, lang: {l}}) RETURN w")
    WordNeoEntity createOneWord(@Param("w") String word, @Param("sp") String spell, @Param("l") String lang);

    // Add a word with multi spell
    @Query("CREATE (w: Word{word:{w}, spell: {sp}, lang: {l}}) RETURN w")
    WordNeoEntity createOneWord(@Param("w") String word, @Param("sp") ArrayList<String> spell, @Param("l") String lang);

    // Find only one word by word
    @Query("MATCH(w:Word{word:{w}}) RETURN w LIMIT 1")
    WordNeoEntity findOneWord(@Param("w") String word);

    // Find only one word by word and lang
    @Query("MATCH(w:Word{word:{w}, lang: {l}}) RETURN w LIMIT 1")
    WordNeoEntity findOneWord(@Param("w") String word, @Param("l") String lang);

    // Find only one word by word, spell and lang
    @Query("MATCH(w:Word{word:{w}, spell: {sp}, lang:{l}}) RETURN w LIMIT 1")
    WordNeoEntity findOneWord(@Param("w") String word, @Param("sp") String sp, @Param("l") String lang);

    // Find only one word by id
    @Query("MATCH(w:Word) WHERE ID(w) = {i} RETURN w LIMIT 1")
    WordNeoEntity findOneWord(@Param("i") Integer id);

    // Find word that similar to
    @Query("MATCH(w:Word{word:{w}})-[r: SIMILAR_TO]->(m) RETURN w,r,m")
    Collection<WordNeoEntity> findWordSimilarTo(@Param("w") String word);

    // Find word that similar from
    @Query("MATCH(w:Word{word:{w}})<-[r: SIMILAR_TO]-(m) RETURN w,r,m")
    Collection<WordNeoEntity> findWordSimilarFrom(@Param("w") String word);

    //Create similar to relation
    @Query("MATCH (n:Word),(m:Word) " + "WHERE ID(n)={id1} and ID(m)={id2} " + "MERGE (n)-[r:SIMILAR_TO]->(m) "
            + "ON CREATE SET r.score={sc} " + "ON MATCH SET r.score={sc} " + " RETURN n,r,m")
    SimilarToRelNeoEntity createSimilarToRel(@Param("id1") Integer id1, @Param("id2") Integer id2,
            @Param("sc") Integer score);

    //Find word that relate to 
    @Query("MATCH(w:Word{word:{w}})-[r: RELATED_TO]->(m) return w,r,m")
    Collection<WordNeoEntity> findWordRelatedTo(@Param("w") String word);

    //Find word that related from
    @Query("MATCH(w:Word{word:{w}})<-[r: RELATED_TO]-(m) return w,r,m")
    Collection<WordNeoEntity> findWordRelatedFrom(@Param("w") String word);

    //Create Related To relation
    @Query("MATCH (n:Word),(m:Word) " + "WHERE ID(n)={id1} and ID(m)={id2} "
           + "MERGE (n)-[r:RELATED_TO]->(m) "
           + "ON CREATE SET r.score={sc} "
           + "ON MATCH SET r.score={sc} "
           + "RETURN n,r,m")
    RelatedToRelNeoEntity createRelatedToRel(@Param("id1") Integer id1, @Param("id2") Integer id2, @Param("sc") Integer score);

    //Find word that translate to
    @Query("MATCH(w:Word{word:{w}})-[r: TRANSLATE_TO]->(m) return w,r,m")
    Collection<WordNeoEntity> findWordTranslateTo(@Param("w") String word);

    //Find word that transalted from
    @Query("MATCH(w:Word{word:{w}})<-[r: TRANSLATE_TO]-(m) return w,r,m")
    Collection<WordNeoEntity> findWordTranslateFrom(@Param("w") String word);

    //Create Translate To relation
    @Query("MATCH (n:Word),(m:Word) " + "WHERE ID(n)={id1} and ID(m)={id2} "
           + "MERGE (n)-[r:TRANSLATE_TO]->(m) "
           + "ON CREATE SET r.score={sc} "
           + "ON MATCH SET r.score={sc} "
           + "RETURN n,r,m")
    TranslateToRelNeoEntity createTranslateToRel(@Param("id1") Integer id1, @Param("id2") Integer id2, @Param("sc") Integer score);

    //Find word that opposite to
    @Query("MATCH(w:Word{word:{w}})-[r: OPPOSITE_TO]->(m) return w,r,m")
    Collection<WordNeoEntity> findWordOppositeTo(@Param("w") String word);

    @Query("MATCH(w:Word{word:{w}})<-[r: OPPOSITE_TO]-(m) return w,r,m")
    Collection<WordNeoEntity> findWordOppositeFrom(@Param("w") String word);

    //Create Opposite To relation
    @Query("MATCH (n:Word),(m:Word) " + "WHERE ID(n)={id1} and ID(m)={id2} "
           + "MERGE (n)-[r:OPPOSITE_TO]->(m) "
           + "ON CREATE SET r.score={sc} "
           + "ON MATCH SET r.score={sc} "
           + "RETURN n,r,m")
    OppositeToRelNeoEntity createOppositeToRel(@Param("id1") Integer id1, @Param("id2") Integer id2, @Param("sc") Integer score);

    // Delete a word
    @Query("MATCH (n:Word) WHERE ID(n) = {id} OPTIONAL MATCH (n)-[r]-() DELETE n,r")
    void deleteOneWord(@Param("id") Integer id);

}