package com.epicodus.avb.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import com.epicodus.avb.Constants;
import com.epicodus.avb.adapters.FirebaseExperimentViewHolder;
import com.epicodus.avb.models.Experiment;
import com.epicodus.avb.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import butterknife.Bind;
import butterknife.ButterKnife;

public class AllExperimentsActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.createButton) Button mCreateButton;
    @Bind(R.id.recyclerView) RecyclerView recyclerView;
    private DatabaseReference mExperimentReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_experiments);
        ButterKnife.bind(this);
        mExperimentReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_EXPERIMENTS);
        setupFirebaseAdapter();
        mCreateButton.setOnClickListener(this);
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
