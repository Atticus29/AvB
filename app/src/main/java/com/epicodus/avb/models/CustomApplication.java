package com.epicodus.avb.models;

import android.app.Application;

import com.twitter.sdk.android.core.Twitter;

/**
 * Created by Guest on 6/1/17.
 */

public class CustomApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Twitter.initialize(this);
    }
}
