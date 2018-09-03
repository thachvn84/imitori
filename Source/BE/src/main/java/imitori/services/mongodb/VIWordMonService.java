package imitori.services.mongodb;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import imitori.entity.mongodb.VIWordMonEntity;
import imitori.repository.mongodb.VIWordMonCrudRepository;
import imitori.repository.mongodb.VIWordMonRepository;
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
public class VIWordMonService {
    private final static Logger LOG = LoggerFactory.getLogger(VIWordMonService.class);

    private final VIWordMonCrudRepository vicrudrep;
    private final VIWordMonRepository virep;

    /*
    * ======================== INITIALIZE ========================
    */
    public VIWordMonService(VIWordMonCrudRepository cr, VIWordMonRepository re) {
        LOG.debug("Initialize");
        this.vicrudrep = cr;
        this.virep = re;
    }
    /*
    * ======================== QUICK ADD ========================
    */
    @Transactional(readOnly = true)
    public Integer addViWord(String s) {
        Integer id = -1;
        LOG.debug("Add ViWord(Quick): " + s);
        VIWordMonEntity w = new VIWordMonEntity();
        w.word = s;
        id = this.virep.searchWord(s);
        if (id == -1) {
            id = this.virep.getMaxId() + 1;
            w.id = id;
            this.vicrudrep.insert(w);
            return id;
        } else {
            return id;
        }
    }
    /*
    * ======================== FULL ADD ========================
    */
    @Transactional(readOnly = true)
    public Integer addViWord(VIWordMonEntity w) {
        LOG.debug("addOneWord: " + w.word);
        Integer sid = this.virep.searchWord(w);
        if (sid == BEConstant.WORD_NOT_FOUND) {
            Integer id = this.virep.getMaxId() + 1;
            w.id = id;
            this.vicrudrep.insert(w);
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
    public VIWordMonEntity findFirstViWord(String w) {
        VIWordMonEntity res = new VIWordMonEntity();
        Integer res_id = virep.searchWord(w);
        if (res_id >= 0) {
            Optional<VIWordMonEntity> opt = vicrudrep.findById(res_id);
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
    public ArrayList<VIWordMonEntity> searchAllByWord(String w, Integer option){
        ArrayList<VIWordMonEntity> res = virep.searchAllByWord(w, option);
        return res;
    }
    /*
    * ======================== MODIFY ONE ========================
    */
    @Transactional(readOnly = true)
    public Integer updateWord(Integer id, VIWordMonEntity w) {
        return this.virep.updateWordById(id, w);
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