package org.onosproject.fault_prediction.org.onosproject.fault_prediction.impl;

/**
 * Created by mica on 18-5-28.
 */
public class FPMessage {

    MessageType messagetype;

    public FPMessage(){}

    public FPMessage(MessageType messagetype){
        this.messagetype=messagetype;
    }

    public MessageType getType(FPMessage ms){
        return ms.messagetype;
    }

    public void setType(MessageType mstype){
        this.messagetype=mstype;
    }

    public enum MessageType{
        DATACOLLECTFINSH,
        MODELANDPARACONFIGFINISH
    };
}
