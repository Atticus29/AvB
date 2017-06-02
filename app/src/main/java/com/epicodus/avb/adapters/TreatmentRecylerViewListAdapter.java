package com.epicodus.avb.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.avb.R;
import com.epicodus.avb.models.Experiment;
import com.mopub.nativeads.MoPubRecyclerViewHolder;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 6/2/17.
 */

public class TreatmentRecylerViewListAdapter extends RecyclerView.Adapter<TreatmentRecylerViewListAdapter.TreatmentViewHolder>{
    private String[] treatments;
    private Context context;

    public TreatmentRecylerViewListAdapter (Context context, String[] treatments){
        this.context = context;
        this.treatments = treatments;

    }

    @Override
    public TreatmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.experiment_list_item, parent, false);
        TreatmentViewHolder viewHolder = new TreatmentViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TreatmentViewHolder holder, int position) {
        holder.bindExperiment(treatments);
    }

    @Override
    public int getItemCount() {
        return treatments.length;
    }

    public class TreatmentViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.treatmentName) TextView treatmentName;
        @Bind(R.id.reportSuccessButton) Button reportSuccessButton;
        @Bind(R.id.reportFailureButton) Button reportFailureButton;
        @Bind(R.id.treatment2Name) TextView treatment2Name;
        @Bind(R.id.reportSuccessButton2) Button reportSuccessButton2;
        @Bind(R.id.reportFailureButton2) Button reportFailureButton2;

        private Context context;

        public TreatmentViewHolder (View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }

        public void bindExperiment(String[] treatments){
            treatmentName.setText(treatments[0]);
            treatment2Name.setText(treatments[1]);
//            TODO figure out how to handle click events inside an adapter
        }
    }

}
