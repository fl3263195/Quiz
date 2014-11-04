package com.example.lfan.instagramtest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;
import com.nhaarman.listviewanimations.ArrayAdapter;

/**
 * Created by lfan on 11/3/14.
 */
public class ImageAdapter extends ArrayAdapter {
    public static final String TAG = "ImageAdapter";


    Context mContext;
    List<Tweet> mTweetsList;
    View.OnClickListener mListenr;
    public ImageAdapter(Context context, List<Tweet> tweetList) {
        super();
        mContext = context;
        mTweetsList = tweetList;
        mListenr = new ImageOnClickListener();
    }
    @Override
    public int getCount() {
        return mTweetsList.size()/3;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        ViewHolder vh;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_images, viewGroup, false);
            vh = new ViewHolder();
            vh.image1 = (ImageView) view.findViewById(R.id.image1);

            vh.image2 = (ImageView) view.findViewById(R.id.image2);

            vh.image3 = (ImageView) view.findViewById(R.id.image3);


            view.setTag(vh);
        }
        else {
            vh = (ViewHolder) view.getTag();
        }
        vh.image1.setOnClickListener(mListenr);
        vh.image2.setOnClickListener(mListenr);
        vh.image3.setOnClickListener(mListenr);
        int index = 3 * position;
        vh.image1.setTag(index);
        vh.image2.setTag(index+1);
        vh.image3.setTag(index+2);
        Log.d(TAG, "downloading images from " + mTweetsList.get(index).getUrlLowResolution());
        Picasso.with(mContext).load(mTweetsList.get(index).getUrlStandardResolution()).placeholder(R.drawable.placeholder_large).into(vh.image1);
        Picasso.with(mContext).load(mTweetsList.get(index+1).getUrlStandardResolution()).placeholder(R.drawable.placeholder_small).into(vh.image2);
        Picasso.with(mContext).load(mTweetsList.get(index+2).getUrlStandardResolution()).placeholder(R.drawable.placeholder_small).into(vh.image3);
        return view;
    }

    class ImageOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            int[] screenLocation = new int[2];
            view.getLocationOnScreen(screenLocation);
//            PictureD
            Intent subActivity = new Intent(mContext, PictureDetailsActivity.class);
            subActivity.
                    putExtra(Constant.PACKAGE + ".left", screenLocation[0]).
                    putExtra(Constant.PACKAGE + ".top", screenLocation[1]).
                    putExtra(Constant.PACKAGE + ".width", view.getWidth()).
                    putExtra(Constant.PACKAGE + ".height", view.getHeight()).
                    putExtra(Constant.PACKAGE + ".position", (Integer) view.getTag());
            mContext.startActivity(subActivity);
            ((Activity)mContext).overridePendingTransition(0,0);
        }
    }

    static class ViewHolder {
        ImageView image1;
        ImageView image2;
        ImageView image3;
    }
}
