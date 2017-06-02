package com.epicodus.avb.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.epicodus.avb.R;
import com.epicodus.avb.adapters.TweetAdapter;
import com.epicodus.avb.models.TwitterStatus;
import com.epicodus.avb.services.TwitterService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TweetDisplayActivity extends AppCompatActivity {
    public static final String TAG = TweetDisplayActivity.class.getSimpleName();
    public ArrayList<TwitterStatus> twitterStatuses = new ArrayList<>();
    @Bind(R.id.tweetRecyclerView) RecyclerView tweetRecyclerView;

    private TweetAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_display);
        ButterKnife.bind(this);
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
            public void onResponse(Call call, Response response){
                twitterStatuses = twitterService.processResults(response);
                TweetDisplayActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new TweetAdapter(getApplicationContext(), twitterStatuses);
                        tweetRecyclerView.setAdapter(adapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(TweetDisplayActivity.this);
                        tweetRecyclerView.setLayoutManager(layoutManager);
                        tweetRecyclerView.setHasFixedSize(true);
                    }
                });


            }
        });
    }
}
