package org.onosproject.fault_prediction.org.onosproject.fault_prediction.impl.data_related;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import org.onosproject.fault_prediction.org.onosproject.fault_prediction.impl.FPMessage;
import org.onosproject.fault_prediction.org.onosproject.fault_prediction.impl.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by mica on 18-5-29.
 * DataCollectByReadFile collect performance and warning data
 * by read file.
 */
public class DataCollectByReadFile extends TimerTask {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final String txtPath = "\\home\\mica\\fault_prediction\\data";

    List<Data> dataset = new ArrayList<Data>();

    public void run(){
        try{
            dataset.clear();
            new DataCollectByReadFile().dataCollectByReadTxt(txtPath);
        }
        catch(IOException ex){
            log.error("IO errors");
        }

    }

    protected void dataCollectByReadTxt(String txtPath) throws IOException{

        File file = new File(txtPath);
        File[] files = file.listFiles();
        for(int i=0;i<files.length;i++){
            if (files[i].isFile()) {
                double[] temhistorical = null;
                double temfuture = 0;
                InputStreamReader reader = new InputStreamReader(new FileInputStream(files[i]));
                BufferedReader br = new BufferedReader(reader);
                String line = null;
                int linenumber = 1;
                line = br.readLine();
                while(line!=null){
                    /*if (linenumber==1){
                        currenttime = Double.parseDouble(line);
                        linenumber++;
                    }*/
                    if(linenumber>0&&linenumber<21){
                        temhistorical[linenumber-1] = (Double.parseDouble(line));
                        linenumber++;
                    }
                    if(linenumber==21){
                        temfuture = Double.parseDouble(line);
                    }
                }
                Data data = new Data(temhistorical,temfuture);
                dataset.add(data);
            }
            else{
                log.info("No file found");
            }
            if(i==files.length-1){
                log.info("The last file");
                sendDataCollectFinishMessage();
            }
        }

    }
    public void sendDataCollectFinishMessage(){
            FPMessage DataCollectFinish = new FPMessage(FPMessage.MessageType.DATACOLLECTFINSH);
            new MessageHandler().formMessageList(DataCollectFinish);
    }
}
