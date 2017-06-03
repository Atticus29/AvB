package com.epicodus.avb.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.epicodus.avb.R;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.http.HTTP;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.aboutTextView) TextView aboutTextView;
    @Bind(R.id.emailTextView) TextView emailLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        emailLabel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v == emailLabel){
            Log.d("emailClick", "got here");
            String mailto = "mailto: mark.aaron.fisher@gmail.com";
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse(mailto));
            // TODO eventually get some user input for the message
            startActivity(emailIntent);
        }
    }
}
