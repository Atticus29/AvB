package com.epicodus.avb;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;

import java.util.ArrayList;


public class BriefExperimentAdapter extends ArrayAdapter {
    private Context mContext;
    private ArrayList<Experiment> mExperiments;

    public BriefExperimentAdapter (Context context, int resource, ArrayList<Experiment> experiments){
        super(context, resource);
        this.mExperiments = experiments;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }
}
