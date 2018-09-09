package imitori.repository.neo4j;

import java.util.ArrayList;

import imitori.entity.neo4j.KlassNeoEntity;

public class KlassNeoRepository {
    private final KlassNeoCrudRepository crud;

    public KlassNeoRepository(KlassNeoCrudRepository cr) {
        this.crud = cr;
    }

    public Integer searchWord(String s) {
        return crud.searchWord(s);
    }

    public Integer searchKlass(KlassNeoEntity k) {
        return -1;
    }
    public Integer getMaxId() {
        return -1;
    }
 
    public Integer insert(KlassNeoEntity w) {
        return -1;
    }
 
    public KlassNeoEntity findOneByWord(String w) {
        KlassNeoEntity res = new KlassNeoEntity();
        return res;
    }

    public ArrayList<KlassNeoEntity> findAllByWord(String w, Integer option) {
        ArrayList<KlassNeoEntity> res = new ArrayList<>();
        return res;
    }

    public Integer updateKlass(Integer id, KlassNeoEntity w) {
        return -1;
    }
}