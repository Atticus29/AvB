package com.epicodus.avb.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.epicodus.avb.R;
import com.epicodus.avb.services.TwitterService;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TweetDisplayActivity extends AppCompatActivity {
    public static final String TAG = TweetDisplayActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_display);
        getTweets();
    }

    private void getTweets(){
        final TwitterService twitterService = new TwitterService();
        twitterService.findTweets(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try{
                    String jsonData = response.body().string();
                    Log.d(TAG, jsonData);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
