package com.epicodus.avb;

/**
 * Created by mf on 5/26/17.
 */

public class Experiment {
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
}
