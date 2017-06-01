package com.epicodus.avb.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.epicodus.avb.adapters.BriefExperimentAdapter;
import com.epicodus.avb.models.Experiment;
import com.epicodus.avb.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AllExperimentsActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.createButton) Button mCreateButton;
    @Bind(R.id.experimentsLayout) ListView mExperimentsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_experiments);
        ButterKnife.bind(this);
        mCreateButton.setOnClickListener(this);
        BriefExperimentAdapter adapter = new BriefExperimentAdapter(this, android.R.layout.simple_list_item_1, Experiment.allExperiments);
        mExperimentsLayout.setAdapter(adapter);
    }

    @Override
    public void onClick(View v){
        if(v == mCreateButton){
            Intent intent = new Intent(AllExperimentsActivity.this, AddExperimentActivity.class);
            startActivity(intent);
        }
    }
}
