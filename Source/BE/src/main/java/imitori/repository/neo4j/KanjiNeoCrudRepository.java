package imitori.repository.neo4j;

public interface KanjiNeoCrudRepository extends Neo4jRepository<KanjiNeoEntity, Integer> {
    public Integer searchWord(String s);
}