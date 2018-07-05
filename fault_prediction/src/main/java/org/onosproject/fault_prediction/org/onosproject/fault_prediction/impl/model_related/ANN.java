package org.onosproject.fault_prediction.org.onosproject.fault_prediction.impl.model_related;

/**
 * Created by mica on 18-6-5.
 */
public class ANN {
    private int NeuronsNumberInInputLayer = 21;
    private int NeuronsNumberInOnputLayer = 1;
    private int NumberofHiddenLayer = 1;
    private int[] NeuronsNumberInHiddenLayer = new int[NumberofHiddenLayer];
    private double LearningRate = 0.01;
    private int epoch = 5;


    double getLR(){
        return LearningRate;
    }
    void setLR(double learningRate){this.LearningRate = learningRate;}

    int getEpoch(){
        return epoch;
    }
    void setEpoch(int epoch){this.epoch = epoch;}

    int getNNIL(){
        return NeuronsNumberInInputLayer;
    }
    void setNNIL(int nnil){this.NeuronsNumberInInputLayer = nnil;}

    int getNNOL(){
        return NeuronsNumberInOnputLayer;
    }
    void setNNOL(int nnol){this.NeuronsNumberInOnputLayer = nnol;}

    int getNH(){
        return NumberofHiddenLayer;
    }
    void setNH(int nh){this.NumberofHiddenLayer = nh;}

    int[] getNNHL(){
        for(int i=0;i<NeuronsNumberInHiddenLayer.length;i++){
            NeuronsNumberInHiddenLayer[i] = 40;
        }
        return NeuronsNumberInHiddenLayer;
    }
    void setNNHL(int[] nnhl){this.NeuronsNumberInHiddenLayer = nnhl;}


}
