package net.swallowsnest.mjsongs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by marshas on 6/25/17.
 */

public class SongAdapter extends ArrayAdapter<Song> {

    public SongAdapter(Context context, ArrayList<Song> songs) {
        super(context, 0, songs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.song_item, parent, false);
        }

// Get the object located at this position in the list
        final Song songs = getItem(position);

        // Find the TextView in the list_item.xml layout
        TextView headlineTextView = (TextView) listItemView.findViewById(R.id.title);
        // Set the text on the TextView
        headlineTextView.setText(songs.getmTrack());

        TextView releaseDateTextView = (TextView) listItemView.findViewById(R.id.date);
        releaseDateTextView.setText(songs.getmReleaseDate());

        ImageView artworkImageView = (ImageView) listItemView.findViewById(R.id.image);
        artworkImageView.setImageResource(R.drawable.placeholder);

        new DownloadImageTask(artworkImageView).execute(songs.getmArtworkUrl());

        RelativeLayout listItemContainerView = (RelativeLayout) listItemView.findViewById(R.id.song_item);
        listItemContainerView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse
                        (songs.getTrackUrl())));
            }
        });

        return listItemView;
    }

    }

