package imitori.services.mongodb; 

import java.util.ArrayList;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 

import imitori.entity.mongodb.JAWordMonEntity; 
import imitori.repository.mongodb.JAWordMonCrudRepository; 
import imitori.repository.mongodb.JAWordMonRepository; 
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
public class JAWordMonService {
    private final static Logger LOG = LoggerFactory.getLogger(JAWordMonService.class); 

    private final JAWordMonCrudRepository jcrudrep; 
    private final JAWordMonRepository jrep; 

    /*
     * ======================== INITIALIZE =========
     */
    public JAWordMonService(JAWordMonCrudRepository c, JAWordMonRepository r) {
        this.jcrudrep = c; 
        this.jrep = r; 
    }

    /*
     * ======================== QUICK ADD =========
     */
    public Integer addWord(String w) {
        Integer id = -1; 
        LOG.debug("addOneWord(Quick): " + w); 
        JAWordMonEntity word = new JAWordMonEntity(); 
        word.word = w; 
        id = this.jrep.searchWord(w); 
        if (id == -1) {
            id = this.jrep.getMaxId() + 1; 
            word.id = id; 
            return id; 
        }else {
            return id; 
        }
    }

    /*
     * ======================== FULL ADD =========
     */
    @Transactional(readOnly = true)
    public Integer addOneWord(JAWordMonEntity w) {
        LOG.debug("addOneWord: " + w.word); 
        Integer sid = this.jrep.searchWord(w); 
        if (sid == BEConstant.WORD_NOT_FOUND) {
            Integer id = this.jrep.getMaxId() + 1; 
            w.id = id; 
            this.jcrudrep.insert(w); 
            return id; 
        } else {
            return sid; 
        }
    }

    /*
     * ======================== SEARCH ONE =========
     */
    @Transactional(readOnly = true)
    public JAWordMonEntity findFirestWord(String w, Integer option) {
        JAWordMonEntity res = new JAWordMonEntity();
        //TODO: Implement this function
        return res;
    }

    @Transactional(readOnly = true)
    public JAWordMonEntity findFirstFuri(String w, Integer option) {
        JAWordMonEntity res = new JAWordMonEntity();

        return res;
    }
    /*
     * ======================== SEARCH ALL =========
     */
    @Transactional(readOnly = true)
    public ArrayList<JAWordMonEntity> searchWord(String w, Integer option) {
        LOG.debug("searchWord: " + w + "(option: " + option + ")");
        ArrayList<JAWordMonEntity> res = new ArrayList<>();
        res = this.jrep.searchAllByWord(w, option);
        return res;
    }

    @Transactional(readOnly = true)
    public ArrayList<JAWordMonEntity> searchFuri(String w, Integer option) {
        LOG.debug("searchFuri: " + w + "(option: " + ")");
        ArrayList<JAWordMonEntity> res = new ArrayList<>();
        res = this.jrep.searchAllByFurigana(w, option);
        return res;
    }
    /*
     * ======================== MODIFY ONE =========
     */
    @Transactional(readOnly = true)
    public Integer updateWord(Integer id, JAWordMonEntity w) {
        return this.jrep.updateWordById(id, w); 
    }
    /*
     * ======================== MODIFY BATCH =========
     */
    @Transactional(readOnly = true)
    public ArrayList<Integer> updateBatchWord(ArrayList<Integer> id, ArrayList<JAWordMonEntity> w) {
        ArrayList<Integer> res = new ArrayList<>();
        // TODO: Implement
        return res;
    }
    /*
     * ======================== DELETE ONE =========
     */
    @Transactional(readOnly = true)
    public void deleteWord(Integer id) {
        //TODO: Implement this function
    }
    /*
     * ======================== DELETE BATCH =========
     */

     @Transactional(readOnly = true)
     public void deleteBatchWord(ArrayList<Integer> id) {
         //TODO: implement this task.
     }
    /*
     * ======================== REMOVE DUPLICATE WORD =========
     */
    @Transactional(readOnly = true)
    public void removeDuplicateWord() {
        //TODO: Implement this task
    }
    /*
     * ======================== REMOVE DUPLICATE ELEMENT =========
     */

     @Transactional(readOnly = true)
     public JAWordMonEntity removeDuplicateElement(JAWordMonEntity w) {
         JAWordMonEntity res = new JAWordMonEntity();
         // TODO: Implement this task.
         return res;
     }
}