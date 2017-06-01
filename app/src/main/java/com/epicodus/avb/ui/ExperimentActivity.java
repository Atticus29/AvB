package com.epicodus.avb.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.epicodus.avb.R;
import com.epicodus.avb.adapters.SingleTreatmentAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ExperimentActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.singleExperimentText) TextView mSingleExperimentText;
    @Bind(R.id.viewAllButton) Button mViewAllButton;
    @Bind(R.id.treatmentGrid) GridView mTreatmentGrid;
    @Bind(R.id.tweetResultsButton) Button mTweetResultsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment);
        ButterKnife.bind(this);
        Typeface spaceAge = Typeface.createFromAsset(getAssets(), "fonts/spaceage.ttf");

        Intent intent = getIntent();
        String experimentName = intent.getStringExtra("name");
        String treatmentOneName = intent.getStringExtra("treatmentOneName");
        String treatmentTwoName = intent.getStringExtra("treatmentTwoName");
        String[] treatments = new String[] {treatmentOneName, treatmentTwoName};
        String output = String.format("Experiment: %s", experimentName);
        mSingleExperimentText.setTypeface(spaceAge);
        mSingleExperimentText.setText(output);
        mViewAllButton.setOnClickListener(this);
        mTweetResultsButton.setOnClickListener(this);
        mTreatmentGrid.setAdapter(new SingleTreatmentAdapter(this, treatments));
    }

    @Override
    public void onClick(View v){
        if(v == mViewAllButton){
            Intent intent = new Intent(ExperimentActivity.this, AllExperimentsActivity.class);
            startActivity(intent);
        } else if (v==mTweetResultsButton){
            Log.d("tweetclicked", "onClick: got here");
            Intent intent = new Intent(ExperimentActivity.this, TwitterActivity.class);
            startActivity(intent);
        }

    }
}
