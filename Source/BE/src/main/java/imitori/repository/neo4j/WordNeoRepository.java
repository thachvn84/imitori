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

public interface WordNeoRepository extends Neo4jRepository<WordNeoEntity, Integer> {

    // Add a word
    

}