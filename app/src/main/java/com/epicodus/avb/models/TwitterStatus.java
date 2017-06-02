package com.epicodus.avb.models;

import java.util.ArrayList;

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
        ArrayList<String> hashTagsWithOctothorps = new ArrayList<>();
        for(int i = 0; i<hashTags.size(); i++){
            hashTagsWithOctothorps.add("#" + hashTags.get(i));
        }
        return hashTagsWithOctothorps;
    }
}
