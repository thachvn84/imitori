package imitori.repository.neo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import imitori.entity.neo4j.WordNeoEntity;

public interface WordNeoCrudRepository extends Neo4jRepository<WordNeoEntity, Integer> {
    public Integer searchWord(String s);
}