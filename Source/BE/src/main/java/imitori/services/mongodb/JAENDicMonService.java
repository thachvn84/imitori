package imitori.services.mongodb;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import imitori.entity.mongodb.JAENDicMonEntity;
import imitori.repository.mongodb.JAENDicMonCrudRepository;
import imitori.repository.mongodb.JAENDicMonRepository;
import imitori.utils.BEConstant;

@Service
public class JAENDicMonService {
    private final static Logger LOG = LoggerFactory.getLogger(JAENDicMonService.class);

    private final JAENDicMonCrudRepository jecrud;
    private final JAENDicMonRepository jerep;

    /*
     * ======================== INITIALIZE ========================
     */
    public JAENDicMonService(JAENDicMonCrudRepository cr, JAENDicMonRepository re) {
        LOG.debug("Initialize");
        this.jecrud = cr;
        this.jerep = re;
    }

    /*
     * ======================== QUICK ADD ========================
     */
    /*
     * Quick add kanji
     */
    @Transactional(readOnly = true)
    public Integer addJAENWord(String w) {
        Integer id = -1;
        LOG.debug("Add JAENDic(Quick): " + w);
        JAENDicMonEntity wo = new JAENDicMonEntity();
        wo.k_ele.add(new JAENDicMonEntity.k_ele_Class(w));
        id = this.jerep.searchWordKeb(w);
        if (id == -1) {
            id = this.jerep.getMaxWordId() + 1;
            wo.id = id;
            this.jecrud.insert(wo);
            return id;
        } else {
            return id;
        }

    }
    /*
     * Quick add furi
     */
    @Transactional(readOnly = true)
    public Integer addJAENWordFuri(String w) {
        Integer id = -1;
        LOG.debug("Add JAENDic(Quick): " + w);
        JAENDicMonEntity wo = new JAENDicMonEntity();
        wo.r_ele.add(new JAENDicMonEntity.r_ele_Class(w));
        id = this.jerep.searchWordReb(w);
        if (id == -1) {
            id = this.jerep.getMaxWordId() + 1;
            wo.id = id;
            this.jecrud.insert(wo);
            return id;
        } else {
            return id;
        }
    }
    /*
    * ======================== FULL ADD ========================
    */
    @Transactional(readOnly = true)
    public Integer addJAENWord(JAENDicMonEntity w) {
        LOG.debug("addJAENWord (keb:" + w.k_ele.get(0).keb + ", reb:"+w.r_ele.get(0).reb+")");
        boolean search_by_furi = false;
        boolean search_by_kj = false;
        boolean result_by_kj = false;
        boolean result_by_furi = false;
        if (w.getKeb().size() > 0) {
            search_by_kj = true;
        }
        if (w.getReb().size() > 0) {
            search_by_furi = true;
        }
        if (search_by_kj) {
            Integer sid = this.jerep.searchWordKeb(w.getKeb().get(0));
            if (sid != BEConstant.WORD_NOT_FOUND) {
                result_by_kj = true;
                return sid;
            }
        } 
        if (search_by_furi) {
            Integer sid = this.jerep.searchWordReb(w.getReb().get(0));
            if (sid == BEConstant.WORD_NOT_FOUND) {
                result_by_furi = true;
                return sid;
            }
        }
        if (!result_by_kj || !result_by_furi) {
            if (search_by_kj || search_by_furi) {
                Integer id = this.jerep.getMaxWordId() + 1;
                w.id = id;
                this.jecrud.insert(w);
                return id;
            } else {
                return -1;
            }
        }
        return -1;
    }

    /*
    * ======================== SEARCH ONE ========================
    */
    /*
     * Search by word.k_ele.keb and return first result
     */
    @Transactional(readOnly = true)
    public JAENDicMonEntity findFirstKeb(String w) {
        JAENDicMonEntity res = new JAENDicMonEntity();
        ArrayList<JAENDicMonEntity> ares = this.jerep.searchAllByKeb(w, BEConstant.SEARCH_EQUAL);
        res = ares.get(0);
        return res;
    }

    /*
     * Search by word.r_ele.reb and return first result
     */
    @Transactional(readOnly = true)
    public JAENDicMonEntity findFirstFuri(String w) {
        JAENDicMonEntity res = new JAENDicMonEntity();
        ArrayList<JAENDicMonEntity> ares = this.jerep.searchAllByReb(w, BEConstant.SEARCH_EQUAL);
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
    public ArrayList<JAENDicMonEntity> findAllFirstKeb(String w, Integer option) {
        return jerep.searchAllByKeb(w, option);
    }

    /*
     * Search All by word.r_ele.reb and return ArrayList of result
     */
    @Transactional(readOnly = true)
    public ArrayList<JAENDicMonEntity> findAllFirstFuri(String w, Integer option) {
        return jerep.searchAllByReb(w, option);
    }
    /*
    * ======================== MODIFY ONE ========================
    */
    @Transactional(readOnly = true)
    public Integer updateWord(Integer id, JAENDicMonEntity w) {
        return this.jerep.updateWordById(id, w);
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