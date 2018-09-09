package imitori.repository.neo4j;

import java.util.ArrayList;

import imitori.entity.neo4j.SensNeoEntity;

public class SensNeoRepository {
    private final SensNeoCrudRepository crud;

    public SensNeoRepository(SensNeoCrudRepository cr) {
        this.crud = cr;
    }

    public Integer searchSen(String w) {
        return crud.searchSen(w);
    }

    public Integer searchSensNeo(SensNeoEntity s) {
        return -1;
    }

    public Integer getMaxId() {
        return -1;
    }

    public Integer insert(SensNeoEntity w) {
        return -1;
    }

    public SensNeoEntity findOneByWord(String w) {
        SensNeoEntity res = new SensNeoEntity();
        return res;
    }

    public ArrayList<SensNeoEntity> findAllByWord(String w, Integer option) {
        ArrayList<SensNeoEntity> res = new ArrayList<>();
        return res;
    }

    public Integer updateSens(Integer id, SensNeoEntity s) {
        return -1;
    }


}