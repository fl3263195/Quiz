package com.example.lfan.instagramtest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lfan on 11/3/14.
 */
public class TweetsManager {
    private static TweetsManager mInstance;
    private List<Tweet> tweetsList;
    private String nextURL = "";
    private TweetsManager(){
        nextURL = Constant.REQUEST_URL;
        tweetsList = new ArrayList<Tweet>();
    }
    public static TweetsManager getInstance() {
        if (mInstance == null)
           mInstance = new TweetsManager();
        return mInstance;
    }

    public List<Tweet> getTweetsList() {
        return tweetsList;
    }
    public void getNextTweets(){
        tweetsList.addAll(Utils.getTweetsFromURL(nextURL));
    }
}
