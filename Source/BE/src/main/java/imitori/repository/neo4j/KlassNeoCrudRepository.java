package imitori.repository.neo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import imitori.entity.neo4j.KlassNeoEntity;

public interface KlassNeoCrudRepository extends Neo4jRepository<KlassNeoEntity, Integer> {
    public Integer searchWord(String s);

}