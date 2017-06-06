package com.epicodus.avb.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.epicodus.avb.adapters.BriefExperimentAdapter;
import com.epicodus.avb.adapters.TreatmentRecylerViewListAdapter;
import com.epicodus.avb.models.Experiment;
import com.epicodus.avb.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AllExperimentsActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.createButton) Button mCreateButton;
    @Bind(R.id.recyclerView) RecyclerView recyclerView;
    private BriefExperimentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_experiments);
        ButterKnife.bind(this);
        mCreateButton.setOnClickListener(this);
        adapter = new BriefExperimentAdapter(this);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AllExperimentsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onClick(View v){
        if(v == mCreateButton){
            Intent intent = new Intent(AllExperimentsActivity.this, AddExperimentActivity.class);
            startActivity(intent);
        }
    }
}
