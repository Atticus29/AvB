package com.epicodus.avb.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.epicodus.avb.R;
import com.epicodus.avb.models.Experiment;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class BriefExperimentAdapter extends RecyclerView.Adapter<BriefExperimentAdapter.BriefExperimentViewHolder> {
    private Context mContext;
    private ArrayList<Experiment> experiments;

    public BriefExperimentAdapter (Context context, ArrayList<Experiment> experiments){
        mContext = context;
        this.experiments = experiments;
    }

    @Override
    public BriefExperimentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.experiment_blurb_item, parent, false);
        BriefExperimentViewHolder viewHolder = new BriefExperimentViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BriefExperimentViewHolder holder, int position) {
        holder.bindExperiment(experiments.get(position));
    }

    @Override
    public int getItemCount() {
        return experiments.size();
    }

    public class BriefExperimentViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.experimentName) TextView experimentName;
        @Bind(R.id.treatment1) TextView treatment1;
        @Bind(R.id.treatment2) TextView treatment2;

        private Context context;

        public BriefExperimentViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }

        public void bindExperiment(Experiment experiment){
            experimentName.setText(experiment.getName());
            treatment1.setText(experiment.getTreatmentOneName());
            treatment2.setText(experiment.getTreatmentTwoName());
        }
    }
}
