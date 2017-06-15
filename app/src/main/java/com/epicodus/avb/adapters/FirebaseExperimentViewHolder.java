package com.epicodus.avb.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.epicodus.avb.Constants;
import com.epicodus.avb.R;
import com.epicodus.avb.models.Experiment;
import com.epicodus.avb.ui.AllExperimentsActivity;
import com.epicodus.avb.ui.ExperimentActivity;
import com.epicodus.avb.ui.ExperimentDetailFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    private int orientation;
            //= itemView.getResources().getConfiguration().orientation;

    public FirebaseExperimentViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
        orientation = itemView.getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            prepopulateDetailFragment();
        }
    }

    private void createDetailFragment(Experiment currentExperiment){
        ExperimentDetailFragment experimentDetailFragment = ExperimentDetailFragment.newInstance(currentExperiment);
        FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.experimentDetailContainer, experimentDetailFragment);
        ft.commit();
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
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_EXPERIMENTS)
                .child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    experiments.add(snapshot.getValue(Experiment.class));
                }
                int itemPosition = getLayoutPosition();
                if(orientation == Configuration.ORIENTATION_LANDSCAPE){
                    Log.d("is landscape", "yes");
                    createDetailFragment(experiments.get(itemPosition));
                } else{
                    Intent intent = new Intent(mContext, ExperimentActivity.class);
                    intent.putExtra("position", itemPosition);
                    intent.putExtra("currentExperiment", Parcels.wrap(experiments.get(itemPosition)));
                    mContext.startActivity(intent);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void prepopulateDetailFragment(){
        final ArrayList<Experiment> experiments = new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_EXPERIMENTS)
                .child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    experiments.add(snapshot.getValue(Experiment.class));
                }
                int itemPosition = getLayoutPosition();
                if(orientation == Configuration.ORIENTATION_LANDSCAPE){
                    Log.d("is landscape", "yes");
                    createDetailFragment(experiments.get(itemPosition));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
