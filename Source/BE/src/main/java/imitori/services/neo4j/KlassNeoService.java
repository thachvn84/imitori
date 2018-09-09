package imitori.services.neo4j;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import imitori.entity.neo4j.KlassNeoEntity;
import imitori.repository.neo4j.KlassNeoRepository;
import imitori.utils.BEConstant;

@Service 
public class KlassNeoService {
    private final static Logger LOG = LoggerFactory.getLogger(KlassNeoService.class);

    private final KlassNeoRepository repo;

    /*
     * ======================== INITIALIZE ========================
     */
    public KlassNeoService(KlassNeoRepository rp) {
        LOG.debug("Initialize");
        this.repo = rp;
    }

    /*
     * ======================== QUICK ADD ========================
     */
    @Transactional(readOnly = true)
    public Integer addKlass(String k) {
        Integer id = -1;
        KlassNeoEntity klass = new KlassNeoEntity();
        klass.klass = k;
        id = this.repo.searchWord(k);
        if (id == -1) {
            return this.repo.insert(klass);
        } else {
            return id;
        }
    }

    /*
     * ======================== FULL ADD ========================
     */
    @Transactional(readOnly = true)
    public Integer addKlass(KlassNeoEntity k) {
        Integer sid = this.repo.searchKlass(k);
        if (sid == BEConstant.WORD_NOT_FOUND) {
            return this.repo.insert(k);
        } else {
            return sid;
        }
    }
    /*
     * ======================== SEARCH ONE ========================
     */
    @Transactional(readOnly = true)
    public KlassNeoEntity findFirstWord(String w) {
        KlassNeoEntity res = new KlassNeoEntity();
        res = this.repo.findOneByWord(w);
        return res;
    }

    /*
     * ======================== SEARCH ALL ========================
     */
    @Transactional(readOnly = true)
    public ArrayList<KlassNeoEntity> findAllWord(String w, Integer option) {
        return this.repo.findAllByWord(w, option);
    }


    /*
     * ======================== MODIFY ONE ========================
     */
    @Transactional(readOnly = true)
    public Integer updateKlass(Integer id, KlassNeoEntity w) {
        return this.repo.updateKlass(id, w);
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