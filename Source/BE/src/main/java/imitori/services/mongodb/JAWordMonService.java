package imitori.services.mongodb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JAWordMonService {
    private final static Logger LOG = LoggerFactory.getLogger(JAWordMonService.class);

    
    public JAWordMonService() {
        LOG.debug("Create new Instance");
    }
}