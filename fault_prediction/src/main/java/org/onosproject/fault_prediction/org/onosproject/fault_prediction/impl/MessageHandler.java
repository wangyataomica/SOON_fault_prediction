package org.onosproject.fault_prediction.org.onosproject.fault_prediction.impl;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.onosproject.fault_prediction.org.onosproject.fault_prediction.impl.model_related.Model;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.TimerTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by mica on 18-5-28.
 * MessageHandler deals with messages and do corresponding actions.
 */
public class MessageHandler extends TimerTask{

    private final Logger log = LoggerFactory.getLogger(getClass());

    protected List<FPMessage> mslist = new ArrayList<FPMessage>(100);
    private BlockingQueue<FPMessage> msqueue = new ArrayBlockingQueue<FPMessage>(10);

    class ReadMessage {
        public void processFPMessage(){
            while(true){
                log.info("Start read message");
                try{
                    processMessage(msqueue.take());
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }


    public void run(){
        msqueue.clear();
        new MessageHandler().new ReadMessage().processFPMessage();
    }


    public void formMessageList (FPMessage ms){
        mslist.add(ms);
        msqueue.add(ms);
    }


    void processMessage(FPMessage ms){
        switch(ms.getType(ms)){
            case DATACOLLECTFINSH:
                //transfer dataset to TF
                //choose what model to use and configure the parameters
                new Model().modelChooseAndParametersConfigure();
                break;
            case MODELANDPARACONFIGFINISH:
                //transfer modelandpara too TF
        }
    };
}
