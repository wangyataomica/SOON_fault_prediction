package org.onosproject.fault_prediction.org.onosproject.fault_prediction.impl;

import org.apache.felix.scr.annotations.*;
import org.onosproject.core.ApplicationId;
import org.onosproject.core.CoreService;
import org.onosproject.fault_prediction.org.onosproject.fault_prediction.impl.data_related.DataCollectByReadFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Timer;

/**
 * Created by mica on 18-5-28.
 * Fault_prediction Controller deals with the message from others
 * and do corresponding action.
 */

@Component(immediate = true)
@Service
public class FPControllerImpl {
    //private ApplicationId appId;
    private static final String APP_ID = "org.onosproject.fault_prediction";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
    protected CoreService coreService;

    @Activate
    protected void activate () throws IOException{
        coreService.registerApplication(APP_ID);
        Timer timer = new Timer();
        timer.schedule(new MessageHandler(),1000);
        timer.schedule(new DataCollectByReadFile(),2000);
        log.info("Started");
    }

    @Deactivate
    protected void deactivate (){
        log.info("Stopped");
    }

}
