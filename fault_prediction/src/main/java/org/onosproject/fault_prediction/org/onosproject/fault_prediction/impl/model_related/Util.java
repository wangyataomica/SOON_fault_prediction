package org.onosproject.fault_prediction.org.onosproject.fault_prediction.impl.model_related;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by mica on 18-7-3.
 */
public class Util {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private ConcurrentMap modelAndPara = new ConcurrentHashMap<>();

    protected ConcurrentMap composeANNData(){
        ANN a = new ANN();
        modelAndPara.put("modelType","ANN");
        modelAndPara.put("NeuronsNumberInInputLayer",a.getNNIL());
        modelAndPara.put("NeuronsNumberInOnputLayer",a.getNNOL());
        modelAndPara.put("NumberofHiddenLayer",a.getNH());
        modelAndPara.put("NeuronsNumberInHiddenLayer",a.getNNHL());
        modelAndPara.put("LearningRate",a.getLR());
        modelAndPara.put("epoch",a.getEpoch());
        log.info("ANN parameters configure finish");
        return modelAndPara;
    }
}
