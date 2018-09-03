package imitori.services.mongodb;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import imitori.entity.mongodb.KlassMonEntity;
import imitori.repository.mongodb.KlassMonCrudRepository;
import imitori.repository.mongodb.KlassMonRepository;
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
public class KlassMonService {
    private final static Logger LOG = LoggerFactory.getLogger(KlassMonService.class);

    private final KlassMonCrudRepository kcrudrep;
    private final KlassMonRepository krep;

    /*
     * ======================== INITIALIZE ========================
     */
    public KlassMonService(KlassMonCrudRepository cr, KlassMonRepository rp) {
        LOG.debug("Create new Instance");
        this.kcrudrep = cr;
        this.krep = rp;
    }

    /*
     * ======================== QUICK ADD ========================
     */
    public Integer addKlass(String k) {
        Integer id = -1;
        LOG.debug("addKanji(Quick): " + k);
        KlassMonEntity kj = new KlassMonEntity();
        kj.klass = k;
        id = this.krep.searchKlass(k);
        if (id == -1) {
            id = this.krep.getMaxId() + 1;
            kj.id = id;
            this.kcrudrep.insert(kj);
            return id;
        } else {
            return id;
        }
    }
    /*
     * ======================== FULL ADD ========================
     */
    @Transactional(readOnly = true)
    public Integer addKlass(KlassMonEntity k) {
        LOG.debug("addOneWord: " + k.klass);
        Integer sid = this.krep.searchKlass(k);
        if (sid == BEConstant.WORD_NOT_FOUND) {
            Integer id = this.krep.getMaxId() + 1;
            k.id = id;
            this.kcrudrep.insert(k);
            return id;
        } else {
            return sid;
        }
    }
    /*
     * ======================== SEARCH ONE ========================
     */
    /*
     * Search by klass and return first result.
     */
    @Transactional(readOnly = true)
    public KlassMonEntity findFirstKlass(String w) {
        KlassMonEntity res = new KlassMonEntity();
        Integer res_id = krep.searchKlass(w);
        if (res_id >= 0) {
            Optional<KlassMonEntity> opt = kcrudrep.findById(res_id);
            if (opt.isPresent()) {
                res = opt.get();
            }
        }
        return res;
    }
    /*
     * Search by hanviet and return first result
     */
    @Transactional(readOnly = true)
    public KlassMonEntity findFirstHV(String w, Integer option) {
        ArrayList<KlassMonEntity> res = new ArrayList<>();
        res = krep.searchAllByHanviet(w, option);
        if (res.size() > 0) {
            return res.get(0);
        } else {
            return new KlassMonEntity();
        }
    }
    /*
     * Search by mean and return first result
     */
    @Transactional(readOnly = true)
    public KlassMonEntity findFirstMean(String w) {
        ArrayList<KlassMonEntity> res = new ArrayList<>();
        res = krep.searchAllByMean(w, BEConstant.SEARCH_CONTAIN);
        if (res.size() > 0) {
            return res.get(0);
        } else {
            return new KlassMonEntity();
        }
    }
     /*
     * ======================== SEARCH ALL ========================
     */
    /*
     *  Search All by Hanviet
     */
    @Transactional(readOnly = true)
    public ArrayList<KlassMonEntity> searchAllByHV(String w, Integer option) {
        ArrayList<KlassMonEntity> res = krep.searchAllByHanviet(w, option);
        return res;
    }
    /*
     *  Search All by mean
     */
    @Transactional(readOnly = true)
    public ArrayList<KlassMonEntity> searchAllByMeans(String w) {
        ArrayList<KlassMonEntity> res = krep.searchAllByMean(w, BEConstant.SEARCH_CONTAIN);
        return res;
    }
    /*
     * ======================== MODIFY ONE ========================
     */
    @Transactional(readOnly = true)
    public Integer updateWord(Integer id, KlassMonEntity w) {
        return this.krep.updateKlassById(id, w);
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