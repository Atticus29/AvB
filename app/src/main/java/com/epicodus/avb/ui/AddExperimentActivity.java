package com.epicodus.avb.ui;

import android.content.Intent;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.epicodus.avb.Constants;
import com.epicodus.avb.models.Experiment;
import com.epicodus.avb.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

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
            String treatmentOne = mTreatmentOneName.getText().toString();
            String treatmentTwo = mTreatmentTwoName.getText().toString();
            String effectSizeInput = mEffectSizeInput.getText().toString();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            if(!name.matches("") && !treatmentOne.matches("") && !treatmentTwo.matches("") && !effectSizeInput.matches("")){
                double effectSizeAsNumber = parseDouble(effectSizeInput);
                if(effectSizeAsNumber >= 0.0 && effectSizeAsNumber <= 1.0){
                    Intent intent = new Intent(AddExperimentActivity.this, ExperimentActivity.class);

                    //Eventually, it would be idea to figure out how to pass an entire object through the intent. Perhaps this will be resolved when the data is persisted as JSON
                    Experiment newExperiment = new Experiment (name, treatmentOne, treatmentTwo, effectSizeAsNumber);
                    DatabaseReference experimentRef = FirebaseDatabase.getInstance()
                            .getReference(Constants.FIREBASE_CHILD_EXPERIMENTS)
                            .child(uid);
                    DatabaseReference pushRef = experimentRef.push();
                    String pushId = pushRef.getKey();
                    newExperiment.setPushId(pushId);
                    pushRef.setValue(newExperiment);
                    intent.putExtra("currentExperiment", Parcels.wrap(newExperiment));

                    startActivity(intent);
                } else{
                    Toast.makeText(AddExperimentActivity.this, "Effect size must range between 0 and 1.", Toast.LENGTH_SHORT).show();
                }

            } else{
                Toast.makeText(AddExperimentActivity.this, "Please make sure to fill out all input fields!", Toast.LENGTH_SHORT).show();
            }


        }
    }
}
