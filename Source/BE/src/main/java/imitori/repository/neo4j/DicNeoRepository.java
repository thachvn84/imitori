package imitori.repository.neo4j;

import java.util.ArrayList;

import imitori.entity.neo4j.DicNeoEntity;

public class DicNeoRepository {
    private final DicNeoCrudRepository crud;

    public DicNeoRepository(DicNeoCrudRepository cr) {
        this.crud = cr;
    }

    public Integer searchWord(String s) {
        return crud.searchWord(s);
    }

    public Integer searchDic(DicNeoEntity d) {
        return -1;
    }

    public Integer getMaxId() {
        return -1;
    }

    public Integer insert(DicNeoEntity w) {
        return -1;
    }

    public DicNeoEntity findOneByWord(String w) {
        DicNeoEntity res = new DicNeoEntity();
        return res;
    }

    public ArrayList<DicNeoEntity> findAllByWord(String w, Integer option) {
        ArrayList<DicNeoEntity> res = new ArrayList<>();
        return res;
    }

    public Integer updateDic(Integer id, DicNeoEntity w) {
        return -1;
    }


}