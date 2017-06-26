package net.swallowsnest.mjsongs;

/**
 * Created by marshas on 6/25/17.
 */

public class Song {
    private String mTrack;

    private String mReleaseDate;
    private String mTrackUrl;
    private String mArtworkUrl;


    public Song(String track, String releaseDate, String trackUrl, String artworkUrl) {
        mTrack = track;
        mReleaseDate = releaseDate;
        mTrackUrl = trackUrl;
        mArtworkUrl = artworkUrl;

    }


    public String getmTrack() {
        return mTrack;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public String getTrackUrl() {
        return mTrackUrl;
    }

    public String getmArtworkUrl(){
        return mArtworkUrl;
    }

}



