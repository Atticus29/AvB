package com.epicodus.avb.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import butterknife.Bind;
import butterknife.ButterKnife;

public class AllExperimentsActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.createButton) Button mCreateButton;
    @Bind(R.id.recyclerView) RecyclerView recyclerView;
    @Bind(R.id.greetingAndExperiment) TextView greetingAndExperiment;
    private DatabaseReference mExperimentReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_experiments);
        ButterKnife.bind(this);
        mCreateButton.setOnClickListener(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String mostRecentExperimentName = mSharedPreferences.getString(Constants.PREFERENCES_MOST_RECENT_EXPERIMENT, "No recent experiments on this device");
        greetingAndExperiment.setText("Most-recently created experiment on this device:\n" + mostRecentExperimentName);
        Log.d("test", "onCreate:" + mostRecentExperimentName);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        mExperimentReference = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_EXPERIMENTS)
                .child(uid);
        setupFirebaseAdapter();

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
