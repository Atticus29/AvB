package com.epicodus.avb.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.avb.Constants;
import com.epicodus.avb.R;
import com.epicodus.avb.adapters.TreatmentRecylerViewListAdapter;
import com.epicodus.avb.models.Experiment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ExperimentActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.singleExperimentText) TextView mSingleExperimentText;
    @Bind(R.id.viewAllButton) Button mViewAllButton;
    @Bind(R.id.treatmentRecyclerView) RecyclerView treatmentRecyclerView;
    @Bind(R.id.tweetResultsButton) Button mTweetResultsButton;
    @Bind(R.id.experimentImage) ImageView imageView;

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    private Experiment currentExperiment;
    private TreatmentRecylerViewListAdapter adapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment);
        ButterKnife.bind(this);

        mTweetResultsButton.setVisibility(View.GONE);

        currentExperiment = Parcels.unwrap(getIntent().getParcelableExtra("currentExperiment"));

        String imageUrl = currentExperiment.getImageURL();
        dropImageIntoView(imageUrl, this);

        Typeface spaceAge = Typeface.createFromAsset(getAssets(), "fonts/spaceage.ttf");

        String experimentName = currentExperiment.getName();
        String treatmentOneName = currentExperiment.getTreatmentOneName();
        String treatmentTwoName = currentExperiment.getTreatmentTwoName();
        String[] treatments = new String[] {treatmentOneName, treatmentTwoName};
        String output = String.format("Experiment: %s", experimentName);
        mSingleExperimentText.setTypeface(spaceAge);
        mSingleExperimentText.setText(output);
        mViewAllButton.setOnClickListener(this);
        mTweetResultsButton.setOnClickListener(this);

        adapter = new TreatmentRecylerViewListAdapter(this, treatments);
        treatmentRecyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ExperimentActivity.this);
        treatmentRecyclerView.setLayoutManager(layoutManager);
        treatmentRecyclerView.setHasFixedSize(true);
    }

    public void dropImageIntoView(String imageURL, Context context){
        if(!imageURL.contains("http")){
            try{
                Bitmap imageBitmap = decodeFromFirebaseBase64(imageURL);
                imageView.setImageBitmap(imageBitmap);
            } catch(IOException e){
//                e.printStackTrace();
            }
        } else{
            Log.d("contains http", imageURL);
            Picasso.with(context)
                    .load(imageURL)
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(imageView);
        }
    }

    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_photo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.camera:
                launchCamera();
            default:
                break;
        }
        return false;
    }

    public void launchCamera(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(this.getPackageManager())!=null){
            startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==REQUEST_IMAGE_CAPTURE && resultCode == this.RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
            encodeBitmapAndSaveToFirebase(imageBitmap);
        }
    }

    public void encodeBitmapAndSaveToFirebase(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_EXPERIMENTS)
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(currentExperiment.getPushId())
                .child("imageURL");
        currentExperiment.setImageURL(imageEncoded);
        ref.setValue(imageEncoded);
    }
}
