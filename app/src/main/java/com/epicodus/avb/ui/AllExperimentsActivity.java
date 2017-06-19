package com.epicodus.avb.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.avb.Constants;
import com.epicodus.avb.adapters.FirebaseExperimentViewHolder;
import com.epicodus.avb.models.Experiment;
import com.epicodus.avb.R;
import com.epicodus.avb.util.OnExperimentSelectedListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AllExperimentsActivity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.createButton) Button mCreateButton;
    @Bind(R.id.recyclerView) RecyclerView recyclerView;
    @Bind(R.id.greetingAndExperiment) TextView greetingAndExperiment;

    private DatabaseReference mExperimentReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    private int orientation;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_experiments);
        ButterKnife.bind(this);

        orientation = this.getResources().getConfiguration().orientation;
        mCreateButton.setOnClickListener(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
//                    Log.d("got here", user.getDisplayName());
//                    greetingAndExperiment.setText("Welcome, " + user.getDisplayName() + "!");
                } else{
//                    greetingAndExperiment.setText("");
                }
            }
        };
        mExperimentReference = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_EXPERIMENTS)
                .child(uid);
        setupFirebaseAdapter();
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            setupLandscapeFirebaseAdapter();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void setupFirebaseAdapter(){
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Experiment, FirebaseExperimentViewHolder>(Experiment.class, R.layout.experiment_blurb_item, FirebaseExperimentViewHolder.class, mExperimentReference) {
            @Override
            protected void populateViewHolder(FirebaseExperimentViewHolder viewHolder, Experiment model, int position) {
                viewHolder.bindExperiment(model);
            }
        };
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mFirebaseAdapter);
    }

    private void setupLandscapeFirebaseAdapter(){
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Experiment, FirebaseExperimentViewHolder>(Experiment.class, R.layout.experiment_longer_blurb_item, FirebaseExperimentViewHolder.class, mExperimentReference) {
            @Override
            protected void populateViewHolder(FirebaseExperimentViewHolder viewHolder, Experiment model, int position) {
                viewHolder.bindExperiment(model);
            }
        };
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onClick(View v){
        if(v == mCreateButton){
            Intent intent = new Intent(AllExperimentsActivity.this, AddExperimentActivity.class);
            startActivity(intent);
        }
    }
}
