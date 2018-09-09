package imitori.repository.neo4j;

import java.util.ArrayList;

import imitori.entity.neo4j.WordNeoEntity;

public class WordNeoRepository {
    private final WordNeoCrudRepository crud;

    public WordNeoRepository(WordNeoCrudRepository cr) {
        this.crud = cr;
    }

    public Integer searchWord(String s) {
        return crud.searchWord(s);
    }

    public Integer searchWord(WordNeoEntity s) {
        return -1;
    }

    public Integer getMaxId() {
        return -1;
    }

    public Integer insert(WordNeoEntity w) {
        return -1;
    }

    public WordNeoEntity findOneByWord(String w) {
        WordNeoEntity res = new WordNeoEntity();
        return res;
    }

    public ArrayList<WordNeoEntity> findAllByWord(String w, Integer option) {
        ArrayList<WordNeoEntity> res = new ArrayList<>();
        return res;
    }

    public Integer updateWord(Integer id, WordNeoEntity w) {
        return -1;
    }
}