package com.epicodus.avb.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.avb.R;
import com.epicodus.avb.models.TwitterStatus;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 6/2/17.
 */

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.TweetViewHolder>{
    private ArrayList<TwitterStatus> tweets = new ArrayList<>();
    private Context context;

    public TweetAdapter(Context context, ArrayList<TwitterStatus> tweets){
        this.context = context;
        this.tweets = tweets;
    }

    @Override
    public TweetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.twitter_list_item, parent, false);
        TweetViewHolder viewHolder = new TweetViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TweetViewHolder holder, int position) {
        holder.bindTwitterStatus(tweets.get(position));
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public class TweetViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.tweetText) TextView tweetText;
        @Bind(R.id.tweetDate) TextView tweetDate;
        @Bind(R.id.hashTags) TextView hashTags;

        private Context context;

        public TweetViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }

        public void bindTwitterStatus(TwitterStatus status){
            tweetText.setText(status.getText());
            tweetDate.setText(status.getTimeCreated());
            hashTags.setText(status.getHashTags());
        }
    }
}
