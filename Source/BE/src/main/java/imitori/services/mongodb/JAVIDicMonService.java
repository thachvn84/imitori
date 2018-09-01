package imitori.services.mongodb;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import imitori.entity.mongodb.JAVIDicMonEntity;
import imitori.repository.mongodb.JAVIDicMonCrudRepository;
import imitori.repository.mongodb.JAVIDicMonRepository;

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
public class JAVIDicMonService {
    private final static Logger LOG = LoggerFactory.getLogger(JAVIDicMonService.class);

    private final JAVIDicMonCrudRepository jvcrudrep;
    private final JAVIDicMonRepository jvrep;

    /*
     * ======================== INITIALIZE ========================
     */
    public JAVIDicMonService(JAVIDicMonCrudRepository c, JAVIDicMonRepository r) {
        LOG.debug("Create new Instance");
        this.jvcrudrep = c;
        this.jvrep = r;
    }

    /*
     * ======================== QUICK ADD ========================
     */
    /*
     * Add a word by given word (quick).
     */
    @Transactional(readOnly = true)
    public Integer addOneWord(String w) {
        LOG.debug("addOneWord(Quick): " + w);
        JAVIDicMonEntity word = new JAVIDicMonEntity();
        word.word = w;
        Integer sid = this.jvrep.searchWord(w);
        if (sid == -1) {
            Integer id = this.jvrep.getMaxId() + 1;
            word.id = id;
            this.jvcrudrep.insert(word);
            return id;
        } else {
            return sid;
        }
    }

    /*
     * ======================== FULL ADD ========================
     */
    /*
     * Add a word if this is not existed.
     */
    @Transactional(readOnly = true)
    public Integer addOneWord(JAVIDicMonEntity w) {
        LOG.debug("addOneWord:" + w.word);
        Integer sid = this.jvrep.searchWord(w);
        if (sid == -1) {
            Integer id = this.jvrep.getMaxId() + 1;
            w.id = id;
            this.jvcrudrep.insert(w);
            return id;
        } else {
            return sid;
        }
    }

    /*
     * ======================== SEARCH ONE ========================
     */
    @Transactional(readOnly = true)
    public JAVIDicMonEntity findFirstWord(String w, Integer option) {
        JAVIDicMonEntity res = new JAVIDicMonEntity();

        // TODO: Implement this function.

        return res;
    }

    @Transactional(readOnly = true)
    public JAVIDicMonEntity findFirstFurigana(String w, Integer option) {
        JAVIDicMonEntity res = new JAVIDicMonEntity();

        return res;
    }
    /*
     * ======================== SEARCH ALL ========================
     */

    @Transactional(readOnly = true)
    public ArrayList<JAVIDicMonEntity> searchWord(String w, Integer option) {
        LOG.debug("searchWord: " + w + "(option: " + option + ")");
        ArrayList<JAVIDicMonEntity> res = new ArrayList<>();
        res = this.jvrep.searchAllByWord(w, option);
        return res;
    }

    @Transactional(readOnly = true)
    public ArrayList<JAVIDicMonEntity> searchFurigana(String w, Integer option) {
        LOG.debug("searchFurigana:" + w + "(option: " + option + ")");
        ArrayList<JAVIDicMonEntity> res = new ArrayList<>();
        res = this.jvrep.searchAllByFurigana(w, option);
        return res;
    }

    /*
     * ======================== MODIFY ONE ========================
     */
    @Transactional(readOnly = true)
    public Integer updateWord(Integer id, JAVIDicMonEntity w) {
        return this.jvrep.updateWordById(id, w);
    }

    /*
     * ======================== MODIFY BATCH ======================
     */
    @Transactional(readOnly = true)
    public ArrayList<Integer> updateBatchWord(ArrayList<Integer> id, ArrayList<JAVIDicMonEntity> w) {
        ArrayList<Integer> res = new ArrayList<>();
        // TODO: Implement
        return res;
    }

    /*
     * ======================== DELETE ONE ========================
     */
    @Transactional(readOnly = true)
    public void deleteWord(Integer id) {
        // TODO: Implement this
    }

    /*
     * ======================== DELETE BATCH ======================
     */
    @Transactional(readOnly = true)
    public void deleteBatchWord(ArrayList<Integer> id) {
        // TODO: Implement this.
    }

    /*
     * ======================== REMOVE DUPLICATE WORD =============
     */
    @Transactional(readOnly = true)
    public void removeDuplicateWord() {
        // TODO: implement
    }

    /*
     * ======================== REEMOVE DUPLICATE ELEMENT =========
     */
    public JAVIDicMonEntity removeDuplidateElement(JAVIDicMonEntity w) {
        JAVIDicMonEntity res = new JAVIDicMonEntity();

        // TODO: implement
        return res;
    }

}