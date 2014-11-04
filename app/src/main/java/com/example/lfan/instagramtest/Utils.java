package com.example.lfan.instagramtest;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lfan on 11/3/14.
 */
public class Utils {
    private static final String TAG = "Utils";
    public static List<Tweet> getTweetsFromURL(String nextURL) {
        List<Tweet> res = new ArrayList<Tweet>();
        HttpClient httpclient = new DefaultHttpClient();

        HttpResponse response = null;
        try {
            Log.d(TAG, "getting data from " + nextURL);
            response = httpclient.execute(new HttpGet(nextURL));
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                String responseString = out.toString();
                JSONObject data = new JSONObject(responseString);
                nextURL = data.getJSONObject("pagination").getString("next_url");
                JSONArray rawTweets = data.getJSONArray("data");
                int size = rawTweets.length();
                for (int i = 0; i < size; i++) {
                    JSONObject rawTweet = rawTweets.getJSONObject(i);
                    if (rawTweet.getString("type").equals("image")) {
                        Tweet tweet = new Tweet();
                        JSONObject urls = rawTweet.getJSONObject("images");
                        tweet.setUrlThumbnail(urls.getJSONObject("thumbnail").getString("url"));
                        tweet.setUrlLowResolution(urls.getJSONObject("low_resolution").getString("url"));
                        tweet.setUrlStandardResolution(urls.getJSONObject("standard_resolution").getString("url"));
                        res.add(tweet);
                    }
                }
            } else{
                //Closes the connection.
                response.getEntity().getContent().close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return res;
    }
}
