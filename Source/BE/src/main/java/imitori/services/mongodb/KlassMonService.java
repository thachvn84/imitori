package imitori.services.mongodb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import imitori.dto.KanjiClassDto;
import imitori.repository.mongodb.KlassMonCrudRepository;
import imitori.repository.mongodb.KlassMonRepository;

@Service
public class KlassMonService {
    private final static Logger LOG = LoggerFactory.getLogger(KlassMonService.class);

    private final KlassMonCrudRepository crudRepo;
    private final KlassMonRepository repo;

    public KlassMonService(KlassMonCrudRepository cr, KlassMonRepository rp) {
        LOG.debug("Create new Instance");
        this.crudRepo = cr;
        this.repo = rp;
    }

    @Transactional(readOnly = true)
    public KanjiClassDto addOneWord(KanjiClassDto word) {
        KanjiClassDto res = new KanjiClassDto();

        return res;
    }
}