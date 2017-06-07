package com.epicodus.avb.models;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.ArrayList;

@Parcel
public class Experiment {
    public String name;
    public String treatmentOneName;
    public String treatmentTwoName;
    public int treatmentOneSucesses;
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
        this.treatmentOneSucesses = 0;
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

    public int getTreatmentOneSucesses() {
        return treatmentOneSucesses;
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
}
