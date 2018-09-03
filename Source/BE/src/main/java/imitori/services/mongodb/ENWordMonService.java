package imitori.services.mongodb;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import imitori.entity.mongodb.ENWordMonEntity;
import imitori.repository.mongodb.ENWordMonCrudRepository;
import imitori.repository.mongodb.ENWordMonRepository;
import imitori.utils.BEConstant;

/*
 * Provide at least
 *      - Intialize
 *      - Quick Add
 *      - Full add
 *      - Search one
 *      - Search all
 *      - Modify one
 *      - Modify batch
 *      - Delete one
 *      - Delete batch
 *      - Remove duplicate word (advance)
 *      - Remove duplicate element (advance)
 */
@Service
public class ENWordMonService {
    private final static Logger LOG = LoggerFactory.getLogger(ENWordMonService.class);

    private final ENWordMonCrudRepository encrud;
    private final ENWordMonRepository enrep;

    /*
    * ======================== INITIALIZE ========================
    */
    public ENWordMonService(ENWordMonCrudRepository cr, ENWordMonRepository re) {
        LOG.debug("Initialize");
        this.encrud = cr;
        this.enrep = re;
    }
    /*
    * ======================== QUICK ADD ========================
    */
    @Transactional(readOnly = true)
    public Integer addENWord(String s) {
        Integer id = -1;
        ENWordMonEntity w = new ENWordMonEntity();
        w.word = s;
        id = this.enrep.searchWord(s);
        if (id == -1) {
            id = this.enrep.getMaxId() + 1;
            w.id = id;
            this.encrud.insert(w);
            return id;
        } else {
            return id;
        }
    }

    /*
    * ======================== FULL ADD ========================
    */
    @Transactional(readOnly = true)
    public Integer addEnWord(ENWordMonEntity w) {
        Integer sid = this.enrep.searchEnWord(w);
        if (sid == BEConstant.WORD_NOT_FOUND) {
            Integer id = this.enrep.getMaxId() + 1;
            w.id = id;
            this.encrud.insert(w);
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
    public ENWordMonEntity findFirstEnWord(String w) {
        ENWordMonEntity res = new ENWordMonEntity();
        Integer res_id = enrep.searchWord(w);
        if (res_id >= 0) {
            Optional<ENWordMonEntity> opt = encrud.findById(res_id);
            if (opt.isPresent()) {
                res = opt.get();
            }
        }
        return res;
    }
    /*
    * ======================== SEARCH ALL ========================
    */
    /*
     * Search all by word
     */
    @Transactional(readOnly = true)
    public ArrayList<ENWordMonEntity> searchAllByWord(String w, Integer option) {
        ArrayList<ENWordMonEntity> res = new ArrayList<>();
        res = enrep.searchAllByWord(w, option);
        return res;
    }
     /*
    * ======================== MODIFY ONE ========================
    */
    @Transactional(readOnly = true)
    public Integer updateWord(Integer id, ENWordMonEntity w) {
        return this.enrep.updateENWordById(id, w);
    }
    /*
    * ======================== MODIFY BATCH ======================
    */
    /*
    * ======================== DELETE ONE ========================
    */
    /*
    * ======================== DELETE BATCH ======================
    */
    /*
    * ======================== REMOVE DUPLICATE WORD =============
    */
    /*
    * ======================== REEMOVE DUPLICATE ELEMENT =========
    */
}