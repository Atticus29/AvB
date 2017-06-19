package com.epicodus.avb.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.avb.Constants;
import com.epicodus.avb.R;
import com.epicodus.avb.models.Experiment;
import com.epicodus.avb.ui.AllExperimentsActivity;
import com.epicodus.avb.ui.ExperimentActivity;
import com.epicodus.avb.ui.ExperimentDetailFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

public class FirebaseExperimentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    View mView;
    Context mContext;
    private int orientation;

    public FirebaseExperimentViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
        orientation = itemView.getResources().getConfiguration().orientation;
    }

    public void bindExperiment(Experiment experiment){
        TextView experimentName = (TextView) mView.findViewById(R.id.experimentName);
        TextView treatment1 = (TextView) mView.findViewById(R.id.treatment1);
        TextView treatment2 = (TextView) mView.findViewById(R.id.treatment2);
        experimentName.setText(experiment.getName());
        treatment1.setText(experiment.getTreatmentOneName());
        treatment2.setText(experiment.getTreatmentTwoName());
        if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d("hi mark", "got here");
            ImageView experimentImage = (ImageView) mView.findViewById(R.id.blurbImage);
            dropImageIntoView(experiment.getImageURL(), mContext, experimentImage);
        }
    }

    public void dropImageIntoView(String imageURL, Context context, ImageView imageView){
        if(!imageURL.contains("http")){
            try{
                Bitmap imageBitmap = decodeFromFirebaseBase64(imageURL);
                imageView.setImageBitmap(imageBitmap);
            } catch(IOException e){
                e.printStackTrace();
            }
        } else{
            Picasso.with(context)
                    .load(imageURL)
                    .resize(200, 200)
                    .centerCrop()
                    .into(imageView);
        }
    }

    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }

    @Override
    public void onClick(View view){
        final ArrayList<Experiment> experiments = new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_EXPERIMENTS)
                .child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    experiments.add(snapshot.getValue(Experiment.class));
                }
                int itemPosition = getLayoutPosition();
//                if(orientation == Configuration.ORIENTATION_LANDSCAPE){
//                    Log.d("is landscape", "yes");
//                    createDetailFragment(experiments.get(itemPosition));
//                } else{
                    Intent intent = new Intent(mContext, ExperimentActivity.class);
                    intent.putExtra("position", itemPosition);
                    intent.putExtra("currentExperiment", Parcels.wrap(experiments.get(itemPosition)));
                    mContext.startActivity(intent);
//                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
