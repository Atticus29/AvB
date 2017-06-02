package com.epicodus.avb.services;

import android.util.Log;

import com.epicodus.avb.Constants;
import com.epicodus.avb.models.TwitterStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

/**
 * Created by Guest on 6/2/17.
 */

public class TwitterService {

    public static void findTweets(Callback callback) {
        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(Constants.TWITTER_CONSUMER_KEY, Constants.TWITTER_CONSUMER_SECRET);
        consumer.setTokenWithSecret(Constants.TWITTER_ACCESS_TOKEN, Constants.TWITTER_TOKEN_SECRET);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new SigningInterceptor(consumer))
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.TWITTER_BASE_URL).newBuilder();
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<TwitterStatus> processResults(Response response) {
        ArrayList<TwitterStatus> statuses = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            Log.d("json", jsonData);
            if (response.isSuccessful()) {
                JSONObject twitterJSON = new JSONObject(jsonData);
                JSONArray statusesJSON = twitterJSON.getJSONArray("statuses");
                Log.d("statuses", statusesJSON.toString());
                for (int i = 0; i < statusesJSON.length(); i++) {
                    JSONObject statusJSON = statusesJSON.getJSONObject(i);
                    String time = statusJSON.getString("created_at");
                    Log.d("time", time);
                    String text = statusJSON.getString("text");
                    Log.d("text", text);
                    ArrayList<String> hashTags = new ArrayList<>();
                    JSONArray hashTagsJSON = statusJSON.getJSONObject("entities").getJSONArray("hashtags");
                    for (int j = 0; j < hashTagsJSON.length(); j++) {
                        JSONObject hashTagObj = hashTagsJSON.getJSONObject(j);
                        hashTags.add(hashTagObj.getString("text"));
                    }
                    TwitterStatus currentStatus = new TwitterStatus(time, text, hashTags);
                    statuses.add(currentStatus);
                }
            }

        }catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }
        return statuses;
    }
}
