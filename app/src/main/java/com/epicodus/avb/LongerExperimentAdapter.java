package com.epicodus.avb;


import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class LongerExperimentAdapter  extends ArrayAdapter{
    private Context mContext;
    private ArrayList<Experiment> experiments;

    public LongerExperimentAdapter (Context context, int resource, ArrayList<Experiment> experiments){
        super(context, resource);
        this.experiments = experiments;

    }

    @Override
    public Object getItem(int position) {
        Experiment currentExperiment = experiments.get(position);
        return String.format("Experiment: %s\nTreatment 1: %s\nTreatment 2: %s", currentExperiment.getName(), currentExperiment.getTreatmentOneName(), currentExperiment.getTreatmentTwoName());
    }

    @Override
    public int getCount() {
        return experiments.size();
    }

}
