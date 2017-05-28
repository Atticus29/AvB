package com.epicodus.avb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ExperimentActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.singleExperimentText) TextView mSingleExperimentText;
    @Bind(R.id.viewAllButton) Button mViewAllButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String experimentName = intent.getStringExtra("name");
        String treatmentOneName = intent.getStringExtra("treatmentOneName");
        String treatmentTwoName = intent.getStringExtra("treatmentTwoName");
        String output = String.format("Experiment: %s\nTreatment 1: %s\nTreatment 2: %s", experimentName, treatmentOneName, treatmentTwoName);
        mSingleExperimentText.setText(output);
        mViewAllButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v == mViewAllButton){
            Intent intent = new Intent(ExperimentActivity.this, AllExperimentsActivity.class);
            startActivity(intent);
        }

    }
}
