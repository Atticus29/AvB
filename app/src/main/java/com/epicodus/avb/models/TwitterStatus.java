package com.epicodus.avb.models;

import java.util.ArrayList;

/**
 * Created by Guest on 6/2/17.
 */

public class TwitterStatus {
    private String timeCreated;
    private String text;
    private ArrayList<String> hashTags;
    public TwitterStatus(String time, String content, ArrayList<String> hashTags){
        this.timeCreated = time;
        this.text = content;
        this.hashTags = hashTags;
    }

    public String getTimeCreated() {
        return timeCreated;
    }

    public String getText() {
        return text;
    }

    public ArrayList<String> getHashTags() {
        return hashTags;
    }
}
