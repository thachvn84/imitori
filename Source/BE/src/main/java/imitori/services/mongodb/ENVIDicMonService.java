package imitori.services.mongodb;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import imitori.entity.mongodb.ENVIDicMonEntity;
import imitori.repository.mongodb.ENVIDicMonCrudRepository;
import imitori.repository.mongodb.ENVIDicMonRepository;
import imitori.utils.BEConstant;

@Service
public class ENVIDicMonService {
    private final static Logger LOG = LoggerFactory.getLogger(ENVIDicMonService.class);

    private final ENVIDicMonCrudRepository evcrud;
    private final ENVIDicMonRepository evrep;

    /*
     * ======================== INITIALIZE ========================
     */
    public ENVIDicMonService(ENVIDicMonCrudRepository cr, ENVIDicMonRepository re) {
        LOG.debug("Initialize");
        this.evcrud = cr;
        this.evrep = re;
    }
    /*
     * ======================== QUICK ADD ========================
     */
    @Transactional(readOnly = true)
    public Integer addENVIWord(String w) {
        Integer id = -1;
        ENVIDicMonEntity wo = new ENVIDicMonEntity();
        wo.word = w;
        id = this.evrep.searchENVIDic(w);
        if (id == -1) {
            id = this.evrep.getMaxWordId() + 1;
            wo.id = id;
            this.evcrud.insert(wo);
            return id;
        } else {
            return id;
        }
    }
    /*
    * ======================== FULL ADD ========================
    */
    @Transactional(readOnly = true)
    public Integer addENVIWord(ENVIDicMonEntity w) {
        Integer sid = this.evrep.searchENVIDic(w);
        if (sid == BEConstant.WORD_NOT_FOUND) {
            Integer id = this.evrep.getMaxWordId() + 1;
            w.id = id;
            this.evcrud.insert(w);
            return id;
        } else {
            return sid;
        }
    }
    /*
    * ======================== SEARCH ONE ========================
    */
    /*
     * Search by word and return first result
     */
    @Transactional(readOnly = true)
    public ENVIDicMonEntity findFirstWord(String w) {
        ENVIDicMonEntity res = new ENVIDicMonEntity();
        ArrayList<ENVIDicMonEntity> ares = this.evrep.findAllByWord(w, BEConstant.SEARCH_CONTAIN);
        res = ares.get(0);
        return res;
    }

     /*
    * ======================== SEARCH ALL ========================
    */
    /*
     * Search All by word.k_ele.keb and return ArrayList of result
     */
    @Transactional(readOnly = true)
    public ArrayList<ENVIDicMonEntity> findFirstWord(String w, Integer option) {
        return this.evrep.findAllByWord(w, option);
    }

    /*
    * ======================== MODIFY ONE ========================
    */
    @Transactional(readOnly = true)
    public Integer updateWord(Integer id, ENVIDicMonEntity w) {
        return this.evrep.updateENVIDicById(id, w);
    }
}