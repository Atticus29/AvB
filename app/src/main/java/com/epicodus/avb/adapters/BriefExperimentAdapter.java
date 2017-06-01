package com.epicodus.avb.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.epicodus.avb.models.Experiment;

import java.util.ArrayList;


public class BriefExperimentAdapter extends ArrayAdapter {
    private Context mContext;
    private ArrayList<Experiment> experiments;

    public BriefExperimentAdapter (Context context, int resource, ArrayList<Experiment> experiments){
        super(context, resource);
        this.experiments = experiments;

    }

    @Override
    public Object getItem(int position) {
        Experiment currentExperiment = experiments.get(position);
        return String.format("Experiment: %s", currentExperiment.getName());
    }

    @Override
    public int getCount() {
        return experiments.size();
    }
}
