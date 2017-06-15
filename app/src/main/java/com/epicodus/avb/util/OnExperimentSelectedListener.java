package com.epicodus.avb.util;

import com.epicodus.avb.models.Experiment;

import java.util.ArrayList;

/**
 * Created by mf on 6/15/17.
 */

public interface OnExperimentSelectedListener {
    public void onExperimentSelected(Integer position, ArrayList<Experiment> experiments);

}
