package com.epicodus.avb;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Guest on 6/1/17.
 */

public class Constants {
    public static final String TWITTER_CONSUMER_KEY = BuildConfig.TWITTER_CONSUMER_KEY;
    public static final String TWITTER_CONSUMER_SECRET = BuildConfig.TWITTER_CONSUMER_SECRET;
    public static final String TWITTER_ACCESS_TOKEN = BuildConfig.TWITTER_ACCESS_TOKEN;
    public static final String TWITTER_TOKEN_SECRET = BuildConfig.TWITTER_TOKEN_SECRET;
    public static final String TWITTER_BASE_URL = "https://api.twitter.com/1.1/search/tweets.json?q=%23ABTest";
    public static final String FIREBASE_CHILD_EXPERIMENTS = "experiments";
    public static final String PREFERENCES_MOST_RECENT_EXPERIMENT = "recentExperiment";
    public static final Double[] POTENTIAL_EFFECT_SIZES = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};

//    https://api.twitter.com/1.1/statuses/update.json?status=Posting%20from%20%40apigee's%20API%20test%20console.%20It's%20like%20a%20command%20line%20for%20the%20Twitter%20API!%20%23apitools&display_coordinates=false
}
