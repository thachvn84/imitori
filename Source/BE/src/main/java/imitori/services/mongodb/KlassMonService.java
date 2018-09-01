package imitori.services.mongodb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import imitori.dto.KanjiClassDto;
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
     * ======================== SEARCH ALL ========================
     */
    /*
     * ======================== MODIFY ONE ========================
     */
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