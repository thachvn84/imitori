package imitori.repository.neo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import imitori.entity.neo4j.DicNeoEntity;

public interface DicNeoRepository extends Neo4jRepository<DicNeoEntity, Integer> {

}