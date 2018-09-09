package imitori.repository.neo4j;

import java.util.ArrayList;
import imitori.entity.neo4j.KanjiNeoEntity;

public class KanjiNeoRepository {
    private final KanjiNeoCrudRepository crud;

    public KanjiNeoRepository(KanjiNeoCrudRepository cr) {
        this.crud = cr;
    }

    public Integer searchWord(String s) {
        return crud.searchWord(s);
    }

    public Integer searchKanji(KanjiNeoEntity k) {
        return -1;
    }

    public Integer getMaxId() {
        return -1;
    }

    public Integer insert(KanjiNeoEntity k) {
        return -1;
    }

    public KanjiNeoEntity findOneByWord(String w) {
        KanjiNeoEntity res = new KanjiNeoEntity();
        return res;
    }

    public ArrayList<KanjiNeoEntity> findAllByWord(String w, Integer option) {
        ArrayList<KanjiNeoEntity> res = new ArrayList<>();
        return res;
    }

    public Integer updateKanji(Integer id, KanjiNeoEntity w) {
        return -1;
    }
}