package imitori.services.mongodb;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import imitori.entity.mongodb.JAVIDicMonEntity;
import imitori.repository.mongodb.JAVIDicMonCrudRepository;
import imitori.repository.mongodb.JAVIDicMonRepository;

@Service
public class JAVIDicMonService {
    private final static Logger LOG = LoggerFactory.getLogger(JAVIDicMonService.class);

    private final JAVIDicMonCrudRepository jvcrudrep;
    private final JAVIDicMonRepository jvrep;

    public JAVIDicMonService(JAVIDicMonCrudRepository c, JAVIDicMonRepository r) {
        LOG.debug("Create new Instance");
        this.jvcrudrep = c;
        this.jvrep = r;
    }

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


}