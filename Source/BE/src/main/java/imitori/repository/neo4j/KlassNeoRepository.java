package imitori.repository.neo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import imitori.entity.neo4j.KlassNeoEntity;

public interface KlassNeoRepository extends Neo4jRepository<KlassNeoEntity, Integer> {

}