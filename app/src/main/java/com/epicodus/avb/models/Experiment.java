package com.epicodus.avb.models;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.ArrayList;

@Parcel
public class Experiment implements Serializable {
    private String name;
    private String treatmentOneName;
    private String treatmentTwoName;
    private int treatmentOneSucesses;
    private int treatmentTwoSucesses;
    private int treatmentOneFailures;
    private int treatmentTwoFailures;
    private double desiredEffectSize;
    private int minimumTrialsRequired;

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

    public Experiment(){}

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
}
