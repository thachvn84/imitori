package imitori.repository.neo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import imitori.entity.neo4j.KanjiNeoEntity;

public interface KanjiNeoRepository extends Neo4jRepository<KanjiNeoEntity, Integer> {
    

}