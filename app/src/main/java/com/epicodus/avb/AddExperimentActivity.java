package com.epicodus.avb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddExperimentActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.nameInput) EditText mNameInput;
    @Bind(R.id.treatmentOneName) EditText mTreatmentOneName;
    @Bind(R.id.treatmentTwoName) EditText mTreatmentTwoName;
    @Bind(R.id.effectSizeInput) EditText mEffectSizeInput;
    @Bind(R.id.submitButton) Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_experiment);
        ButterKnife.bind(this);
        mSubmitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v == mSubmitButton){
            Intent intent = new Intent(AddExperimentActivity.this, ExperimentActivity.class);
            startActivity(intent);
        }
    }
}
