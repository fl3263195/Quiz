package com.example.lfan.instagramtest;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.dragdrop.TouchViewDraggableManager;

import java.util.List;

/**
 * Created by lfan on 11/3/14.
 */
public class ListController extends EndlessScrollListener {
    static float sAnimatorScale = 1;
    Context mContext;
    ListView mListView;
    ImageAdapter mAdapter;
    List<Tweet> tweetList;
    TweetsManager mManager;
    public ListController(Context context, ListView listView) {
        mManager = TweetsManager.getInstance();
        mContext = context;
        mListView = listView;
        mListView.setOnScrollListener(this);
        new GetTweetsTask().execute();
    }

    public void buildListView() {

    }

    @Override
    public void onLoadMore(int page, int totalItemsCount) {
        new GetTweetsTask().execute();
    }

    class GetTweetsTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... strings) {
            mManager.getNextTweets();
            return null;
        }

        @Override
        protected void onPostExecute(Void res) {
            if (mAdapter == null) {
                mAdapter = new ImageAdapter(mContext, mManager.getTweetsList());
                mListView.setAdapter(mAdapter);
                mListView.addFooterView(LayoutInflater.from(mContext).inflate(R.layout.footer, null));
            }
            mAdapter.notifyDataSetChanged();

        }
    }


}
