package net.swallowsnest.mjsongs;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Song>>, SwipeRefreshLayout.OnRefreshListener {

    private static int SONG_LOADER_ID = 0;

    private SongAdapter mAdapter;
    private TextView mEmptyStateTextView;
    public ImageView artworkView;

    ArrayList<Song> songs = new ArrayList<>();
    SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipe.setOnRefreshListener(this);
        new DownloadImageTask(artworkView);

        // Find a reference to the {@link ListView} in the layout
        ListView songListView = (ListView) findViewById(R.id.list);
        mAdapter = new SongAdapter(this, new ArrayList<Song>());
        songListView.setAdapter(mAdapter);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty);
        songListView.setEmptyView(mEmptyStateTextView);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected song.
        songListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current song that was clicked on
                Song currentSong = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri songUri = Uri.parse(currentSong.getTrackUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, songUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        getLoaderManager().initLoader(SONG_LOADER_ID, null, this);
    }

    @Override
    public Loader<List<Song>> onCreateLoader(int i, Bundle bundle) {

        return new SongLoader(this);
    }


    @Override
    public void onLoadFinished(Loader<List<Song>> loader, List<Song> data) {
        swipe.setRefreshing(false);
        if (data != null) {
            mAdapter.setNotifyOnChange(false);
            mAdapter.clear();
            mAdapter.setNotifyOnChange(true);
            mAdapter.addAll(data);
        } else {
            //Set empty state text to display "No Songs found."
            mEmptyStateTextView.setText(R.string.no_songs);

            //Clear the adapter of previous songs
            mAdapter.clear();

        }

    }

    @Override
    public void onLoaderReset(Loader<List<Song>> loader) {

    }

    @Override
    public void onRefresh() {
        getLoaderManager().restartLoader(SONG_LOADER_ID, null, this);
    }
}

