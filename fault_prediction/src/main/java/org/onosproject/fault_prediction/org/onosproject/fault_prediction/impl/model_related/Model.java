package org.onosproject.fault_prediction.org.onosproject.fault_prediction.impl.model_related;

import org.onosproject.fault_prediction.org.onosproject.fault_prediction.impl.FPMessage;
import org.onosproject.fault_prediction.org.onosproject.fault_prediction.impl.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * Created by mica on 18-6-5.
 */
public class Model {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private Util util = new Util();


    public void modelChooseAndParametersConfigure(){
        parametersConfigure(chooseMLModel());
    }

    protected ModelType chooseMLModel(){
        return ModelType.ANN;
    }

    protected ConcurrentMap parametersConfigure(ModelType modelType){
        ConcurrentMap modelAndPara = new ConcurrentHashMap();
        switch(modelType){
            case ANN:
                modelAndPara = util.composeANNData();
                sendModelAndParaConfigFinish();
                break;
        }
        return modelAndPara;
    }

    protected void sendModelAndParaConfigFinish(){
        FPMessage modelAndParaConfigFinish = new FPMessage(FPMessage.MessageType.MODELANDPARACONFIGFINISH);
        new MessageHandler().formMessageList(modelAndParaConfigFinish);
    }
}
