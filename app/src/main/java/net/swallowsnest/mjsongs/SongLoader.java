package net.swallowsnest.mjsongs;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by marshas on 6/25/17.
 */

public class SongLoader extends AsyncTaskLoader<List<Song>> {
    private static final String TAG = SongLoader.class.getName();

    private String mUrl;

    public SongLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<Song> loadInBackground() {
        List<Song> songs = null;
        try {
            URL url = QueryUtils.createUrl();
            String jsonResponse = QueryUtils.makeHttpRequest(url);
            Log.i(TAG, jsonResponse);
            songs = QueryUtils.parseJson(jsonResponse);
        } catch (IOException e) {
            Log.e("Queryutils", "Error Loader LoadInBackground: ", e);
        }
        return songs;
    }
}

