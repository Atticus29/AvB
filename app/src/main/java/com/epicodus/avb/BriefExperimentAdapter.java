package com.epicodus.avb;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;

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
