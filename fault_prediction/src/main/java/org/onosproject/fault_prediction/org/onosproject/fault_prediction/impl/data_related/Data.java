package org.onosproject.fault_prediction.org.onosproject.fault_prediction.impl.data_related;


import java.util.Map;

/**
 * Created by mica on 18-5-31.
 */
class Data {
    private double[] temhistorical;
    private double temfuture;

    public Data(double[] temhistorical,double temfuture){
        this.temhistorical=temhistorical;
        this.temfuture=temfuture;
    }
    public void setTemhistorical(double[] temhistorical){
        this.temhistorical = temhistorical;
    }
    public double[] getTemhistorical(){
        return temhistorical;
    }
    public void setTemfuture(double temfuture){
        this.temfuture = temfuture;
    }
    public double getTemfuture(){
        return temfuture;
    }
    /*protected Input input;
    protected Output output;

    protected Data() {}
    protected Data(Input input, Output output) {
        this.input = input;
        this.output=output;
    }

     class Input {
        protected double currenttime;
        protected Map temhistorical;

        public Input(){}
        public Input(double currenttime, Map temhistorical) {
            this.currenttime = currenttime;
            this.temhistorical = temhistorical;
        }
    }

    class Output {
        protected double temfuture;

        public Output(){}
        public Output(double temfuture) {
            this.temfuture = temfuture;
        }
    }*/

}
