package com.epicodus.avb.models;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.ArrayList;

@Parcel
public class Experiment {
    public String name;
    public String treatmentOneName;
    public String treatmentTwoName;
    public int treatmentOneSuccesses;
    public int treatmentTwoSucesses;
    public int treatmentOneFailures;
    public int treatmentTwoFailures;
    public double desiredEffectSize;
    public int minimumTrialsRequired;
    public String pushId;

    public Experiment(){}

    public Experiment(String name, String treatmentOneName, String treatmentTwoName, double desiredEffectSize){
        this.name = name;
        this.treatmentOneName = treatmentOneName;
        this.treatmentTwoName = treatmentTwoName;
        this.desiredEffectSize = desiredEffectSize;
        this.treatmentOneFailures = 0;
        this.treatmentOneSuccesses = 0;
        this.treatmentTwoFailures = 0;
        this.treatmentTwoSucesses = 0;
        this.minimumTrialsRequired = 25; //this is temporary and will be fleshed out with real statistics when time permits
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

    public int gettreatmentOneSuccesses() {
        return treatmentOneSuccesses;
    }

    public int getTreatmentTwoSucesses() {
        return treatmentTwoSucesses;
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
        double expTreatmentTwoSucesses = ((double)total_successes*total_treatmentTwo_trials)/total_samples;
        double expTreatmentOneFailures = ((double)total_failures*total_treatmentOne_trials)/total_samples;
        double expTreatmentTwoFailures = ((double)total_failures * total_treatmentTwo_trials)/total_samples;

        double chiSqSubunitOne = Math.pow(treatmentOneSuccesses-expTreatmentOneSuccesses ,2)/expTreatmentOneSuccesses;
        double chiSqSubunitSubTwo = Math.pow(treatmentTwoSucesses-expTreatmentTwoSucesses ,2)/expTreatmentTwoSucesses;
        double chiSqSubunitThree = Math.pow(treatmentOneFailures-expTreatmentOneFailures ,2)/expTreatmentOneFailures;
        double chiSqSubunitFour = Math.pow(treatmentTwoFailures-expTreatmentTwoFailures ,2)/expTreatmentTwoFailures;

        double chiSq = chiSqSubunitOne + chiSqSubunitSubTwo + chiSqSubunitThree + chiSqSubunitFour;

        return chiSq;
    }
}
