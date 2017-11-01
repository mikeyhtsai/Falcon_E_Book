package com.sctech.falcon_e_book;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

import static com.sctech.falcon_e_book.MainFalconEbookActivity.LOG_TAG;

/**
 * Created by miketsai on 10/31/2017.
 */

public class BookLoader extends AsyncTaskLoader<List<Books>> {

    private String mUrl;

    public BookLoader (Context context, String url)
    {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.v(LOG_TAG, "Start Loading");
        forceLoad();
    }

    @Override
    public List<Books> loadInBackground() {
        if (mUrl == null) {
            Log.e(LOG_TAG, "Null pointer of mUrl while in loading background");
            return null;
        }
        Log.v(LOG_TAG, "Loading background to get earthQuake Report from server");
        List<Books> books = QueryUtils.extractBooks(mUrl);
        return books;

    }
}
