package com.epicodus.avb.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.epicodus.avb.R;

import org.w3c.dom.Text;

import butterknife.Bind;

public class AboutActivity extends AppCompatActivity {
    @Bind(R.id.aboutTextView) TextView aboutTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}
