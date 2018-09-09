package imitori.repository.neo4j;

public interface WordNeoCrudRepository extends Neo4jRepository<WordNeoEntity, Integer> {
    public Integer searchWord(String s);
}