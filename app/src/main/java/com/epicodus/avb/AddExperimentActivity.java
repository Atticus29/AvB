package com.epicodus.avb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class AddExperimentActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.nameInput) EditText mNameInput;
    @Bind(R.id.treatmentOneName) EditText mTreatmentOneName;
    @Bind(R.id.treatmentTwoName) EditText mTreatmentTwoName;
    @Bind(R.id.effectSizeInput) EditText mEffectSizeInput;
    @Bind(R.id.submitButton) Button mSubmitButton;
    public static final String TAG =  AddExperimentActivity.class.getSimpleName();

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
            String name = mNameInput.getText().toString();
            Log.d("name", name);
            String treatmentOne = mTreatmentOneName.getText().toString();
            Log.d("tx1", treatmentOne);
            String treatmentTwo = mTreatmentTwoName.getText().toString();
            Log.d("tx2", treatmentTwo.getClass().toString());
            String effectSizeInput = mEffectSizeInput.getText().toString();
            Log.d("effectSize", effectSizeInput);
            if(!name.matches("") && !treatmentOne.matches("") && !treatmentTwo.matches("") && !effectSizeInput.matches("")){
                double effectSizeAsNumber = parseDouble(effectSizeInput);
                Experiment newExperiment = new Experiment (name, treatmentOne, treatmentTwo, effectSizeAsNumber);
                Log.d(TAG, String.valueOf(newExperiment));
                Intent intent = new Intent(AddExperimentActivity.this, ExperimentActivity.class);
                startActivity(intent);
            } else{
                Toast.makeText(AddExperimentActivity.this, "Please make sure to fill out all input fields!", Toast.LENGTH_SHORT).show();
            }


        }
    }
}
