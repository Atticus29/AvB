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


public class ExperimentDetailFragment extends Fragment implements View.OnClickListener{
    @Bind(R.id.singleExperimentTextDetailFragment) TextView singleExperimentTextDetailFragment;
    @Bind(R.id.experimentImageDetailFragment) ImageView experimentImageDetailFragment;
    @Bind(R.id.treatmentNameDetailFragment) TextView treatmentNameDetailFragment;
    @Bind(R.id.tx1TrailsRemainingDetailFragment) TextView tx1TrailsRemainingDetailFragment;
    @Bind(R.id.treatment2NameDetailFragment) TextView treatment2NameDetailFragment;
    @Bind(R.id.tx2TrailsRemainingDetailFragment) TextView tx2TrailsRemainingDetailFragment;
    @Bind(R.id.significanceButtonDetailFragment) Button significanceButtonDetailFragment;

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
        singleExperimentTextDetailFragment.setText(currentExperiment.getName());
        Picasso.with(view.getContext())
                .load(currentExperiment.getImageURL())
                .into(experimentImageDetailFragment);
        significanceButtonDetailFragment.setOnClickListener(this);
        treatmentNameDetailFragment.setText(currentExperiment.getTreatmentOneName());
        tx1TrailsRemainingDetailFragment.setText(Integer.toString(currentExperiment.getTreatmentOneSuccesses()) + " successes and " +  Integer.toString(currentExperiment.getTreatmentOneFailures())+ " failures");
        treatment2NameDetailFragment.setText(currentExperiment.getTreatmentTwoName());
        tx2TrailsRemainingDetailFragment.setText(Integer.toString(currentExperiment.getTreatmentTwoSuccesses()) + " successes and " + Integer.toString(currentExperiment.getTreatmentTwoFailures())+ " failures");
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == significanceButtonDetailFragment){
            Log.d("clickedButton", "clicked");
        }
    }
}
