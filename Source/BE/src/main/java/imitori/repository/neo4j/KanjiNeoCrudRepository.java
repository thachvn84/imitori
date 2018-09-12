package imitori.repository.neo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import imitori.entity.neo4j.KanjiNeoEntity;

public interface KanjiNeoCrudRepository extends Neo4jRepository<KanjiNeoEntity, Integer> {
    public Integer searchWord(String s);
}