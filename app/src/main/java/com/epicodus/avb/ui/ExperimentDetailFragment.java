package com.epicodus.avb.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.avb.R;
import com.epicodus.avb.models.Experiment;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ExperimentDetailFragment extends Fragment{
    @Bind(R.id.experimentNameDetailFragment) TextView experimentNameDetailFragment;
    @Bind(R.id.experimentImageDetailFragment) ImageView experimentImageDetailFragment;
    @Bind(R.id.treatment1DetailFragment) TextView treatment1DetailFragment;
    @Bind(R.id.treatment2DetailFragment) TextView treatment2DetailFragment;
    @Bind(R.id.tx1successesDetailFragment) TextView tx1successesDetailFragment;
    @Bind(R.id.tx2successesDetailFragment) TextView tx2successesDetailFragment;
    @Bind(R.id.tx1failuresDetailFragment) TextView tx1failuresDetailFragment;
    @Bind(R.id.tx2failuresDetailFragment) TextView tx2failuresDetailFragment;

    private Experiment currentExperiment;


    public ExperimentDetailFragment() {
    }

    public static ExperimentDetailFragment newInstance(Experiment experiment){
        Log.d("experiment name is:", experiment.getName());
        ExperimentDetailFragment experimentDetailFragment = new ExperimentDetailFragment();
        Bundle args =  new Bundle();
        args.putParcelable("currentExperiment", Parcels.wrap(experiment));
        experimentDetailFragment.setArguments(args);
        return experimentDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        currentExperiment = Parcels.unwrap(getArguments().getParcelable("currentExperiment"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_experiment_detail, container,false);
        ButterKnife.bind(this, view);
        Picasso.with(view.getContext())
        .load(currentExperiment.getImageURL())
        .into(experimentImageDetailFragment);

        experimentNameDetailFragment.setText(currentExperiment.getName());
        treatment1DetailFragment.setText(currentExperiment.getTreatmentOneName());
        treatment2DetailFragment.setText(currentExperiment.getTreatmentTwoName());
        tx1successesDetailFragment.setText(Integer.toString(currentExperiment.getTreatmentOneSuccesses()));
        tx2successesDetailFragment.setText(Integer.toString(currentExperiment.getTreatmentTwoSuccesses()));
        tx1failuresDetailFragment.setText(Integer.toString(currentExperiment.getTreatmentOneFailures()));
        tx2failuresDetailFragment.setText(Integer.toString(currentExperiment.getTreatmentTwoFailures()));

        return view;
    }

}
