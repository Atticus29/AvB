package com.epicodus.avb.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.epicodus.avb.R;
import com.epicodus.avb.models.TwitterStatus;
import com.epicodus.avb.services.TwitterService;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TweetDisplayActivity extends AppCompatActivity {
    public static final String TAG = TweetDisplayActivity.class.getSimpleName();
    public ArrayList<TwitterStatus> twitterStatuses = new ArrayList<>();

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
//                    Log.d(TAG, jsonData);
                    twitterStatuses = twitterService.processResults(response);
                    TweetDisplayActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TwitterStatus[] statuses = new TwitterStatus[twitterStatuses.size()];
                            for(int i = 0; i<statuses.length; i++){
                                statuses[i] = twitterStatuses.get(i);
                                Log.d(TAG, "time"+statuses[i].getTimeCreated());
                                Log.d(TAG, "text"+statuses[i].getText());
                                ArrayList<String> hashTags = statuses[i].getHashTags();
                                for(int j = 0; j<hashTags.size(); j++){
                                    Log.d(TAG, "hashTags"+hashTags.get(j));
                                }

                            }
                        }
                    });

                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
