package imitori.repository.neo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import imitori.entity.neo4j.SensNeoEntity;

public interface SensNeoRepository extends Neo4jRepository<SensNeoEntity, Integer> {

}