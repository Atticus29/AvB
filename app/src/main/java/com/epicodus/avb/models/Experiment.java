package com.epicodus.avb.models;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Parcel
public class Experiment {
    public String name;
    public String treatmentOneName;
    public String treatmentTwoName;
    public int treatmentOneSuccesses;
    public int treatmentTwoSuccesses;
    public int treatmentOneFailures;
    public int treatmentTwoFailures;
    public double desiredEffectSize;
    public int minimumTrialsRequired;
    public String pushId;
    public String imageURL;
    public double chiSq;
    public static Map<Double, Integer> sampleSizeMap = new HashMap<Double, Integer>();

    public Experiment(){}

    public Experiment(String name, String treatmentOneName, String treatmentTwoName, double desiredEffectSize){
        this.name = name;
        this.treatmentOneName = treatmentOneName;
        this.treatmentTwoName = treatmentTwoName;
        this.desiredEffectSize = desiredEffectSize;
        this.treatmentOneFailures = 0;
        this.treatmentOneSuccesses = 0;
        this.treatmentTwoFailures = 0;
        this.treatmentTwoSuccesses = 0;
        this.imageURL = "http://mobileadvertisingwatch.com/wp-content/uploads/2016/02/OpenXcell-Creates-Innovation-Labs-to-Experiment-with-New-App-Technologies.jpg";
        populateSampleSizeMap();
        Integer goalSampleSize = sampleSizeMap.get(desiredEffectSize);
        this.setMinimumTrialsRequired(goalSampleSize);
    }



    public void setMinimumTrialsRequired(int minimumTrialsRequired) {
        if(minimumTrialsRequired %2 == 0){
            this.minimumTrialsRequired = minimumTrialsRequired;
        } else{
            this.minimumTrialsRequired = minimumTrialsRequired + 1;
        }
    }

    public void populateSampleSizeMap(){
        sampleSizeMap.put(0.1, 785);
        sampleSizeMap.put(0.2, 197);
        sampleSizeMap.put(0.3, 88);
        sampleSizeMap.put(0.4, 50);
        sampleSizeMap.put(0.5, 32);
        sampleSizeMap.put(0.6, 22);
        sampleSizeMap.put(0.7, 17);
        sampleSizeMap.put(0.8, 13);
        sampleSizeMap.put(0.9, 10);
        sampleSizeMap.put(1.0, 8);
    }

    public static Map<Double, Integer> getSampleSizeMap() {
        return sampleSizeMap;
    }

    public String getName() {
        return name;
    }

    public String getTreatmentOneName() {
        return treatmentOneName;
    }

    public String getTreatmentTwoName() {
        return treatmentTwoName;
    }

    public int getTreatmentOneSuccesses() {
        return treatmentOneSuccesses;
    }

    public int getTreatmentTwoSuccesses() {
        return treatmentTwoSuccesses;
    }

    public int getTreatmentOneFailures() {
        return treatmentOneFailures;
    }

    public int getTreatmentTwoFailures() {
        return treatmentTwoFailures;
    }

    public double getDesiredEffectSize() {
        return desiredEffectSize;
    }

    public int getMinimumTrialsRequired() {
        return minimumTrialsRequired;
    }

    public String getPushId(){ return pushId; }

    public void setPushId(String pushId){
        this.pushId = pushId;
    }

    public double calculateChiSquared(int treatmentOneSuccesses, int treatmentOneFailures, int treatmentTwoSucesses, int treatmentTwoFailures){
        int total_samples = treatmentOneSuccesses + treatmentOneFailures + treatmentTwoSucesses + treatmentTwoFailures;
        int total_successes = treatmentOneSuccesses + treatmentTwoSucesses;
        int total_treatmentOne_trials = treatmentOneSuccesses + treatmentOneFailures;
        int total_failures = treatmentOneFailures + treatmentTwoFailures;
        int total_treatmentTwo_trials = treatmentTwoSucesses + treatmentTwoFailures;

        double expTreatmentOneSuccesses = ((double)total_successes*total_treatmentOne_trials)/total_samples;
        double expTreatmentTwoSuccesses = ((double)total_successes*total_treatmentTwo_trials)/total_samples;
        double expTreatmentOneFailures = ((double)total_failures*total_treatmentOne_trials)/total_samples;
        double expTreatmentTwoFailures = ((double)total_failures * total_treatmentTwo_trials)/total_samples;

        double chiSqSubunitOne = Math.pow(treatmentOneSuccesses-expTreatmentOneSuccesses ,2)/expTreatmentOneSuccesses;
        double chiSqSubunitSubTwo = Math.pow(treatmentTwoSucesses-expTreatmentTwoSuccesses ,2)/expTreatmentTwoSuccesses;
        double chiSqSubunitThree = Math.pow(treatmentOneFailures-expTreatmentOneFailures ,2)/expTreatmentOneFailures;
        double chiSqSubunitFour = Math.pow(treatmentTwoFailures-expTreatmentTwoFailures ,2)/expTreatmentTwoFailures;

        double chiSq = chiSqSubunitOne + chiSqSubunitSubTwo + chiSqSubunitThree + chiSqSubunitFour;

        this.chiSq = chiSq;
        return chiSq;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }
}
