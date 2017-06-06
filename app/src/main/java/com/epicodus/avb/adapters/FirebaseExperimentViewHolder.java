package com.epicodus.avb.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.avb.Constants;
import com.epicodus.avb.R;
import com.epicodus.avb.models.Experiment;
import com.epicodus.avb.ui.AllExperimentsActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class FirebaseExperimentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    View mView;
    Context mContext;
    public FirebaseExperimentViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }
    public void bindExperiment(Experiment experiment){
        TextView experimentName = (TextView) mView.findViewById(R.id.experimentName);
        TextView treatment1 = (TextView) mView.findViewById(R.id.treatment1);
        TextView treatment2 = (TextView) mView.findViewById(R.id.treatment2);
        experimentName.setText(experiment.getName());
        treatment1.setText(experiment.getTreatmentOneName());
        treatment2.setText(experiment.getTreatmentTwoName());
    }

    @Override
    public void onClick(View view){
        final ArrayList<Experiment> experiments = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_EXPERIMENTS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    experiments.add(snapshot.getValue(Experiment.class));
                }
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, AllExperimentsActivity.class);
                intent.putExtra("position", itemPosition+"");
                intent.putExtra("experiments", Parcels.wrap(experiments));
                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
