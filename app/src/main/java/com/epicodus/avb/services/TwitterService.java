package com.epicodus.avb.services;

import com.epicodus.avb.Constants;

import okhttp3.Callback;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;

/**
 * Created by Guest on 6/2/17.
 */

public class TwitterService {

    public static void findTweets(Callback callback){
        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(Constants.TWITTER_CONSUMER_KEY, Constants.TWITTER_CONSUMER_SECRET);


    }
}
