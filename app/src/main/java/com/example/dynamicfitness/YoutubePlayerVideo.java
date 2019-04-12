package com.example.dynamicfitness;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

import org.w3c.dom.Text;

public class YoutubePlayerVideo extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player_video);

        youTubeView = (com.google.android.youtube.player.YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(Config.YOUTUBE_API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
        Intent intent = getIntent();
        String exerciseName = intent.getStringExtra("exerciseName");
        String videoName = exerciseName + "_video";
        // Id of what you want
        int stringId = getResources().getIdentifier(videoName, "string", YoutubePlayerVideo.this.getPackageName());
        // Actually what you want
        String uTubeLink = getResources().getString(stringId);
        if (!wasRestored) {
            player.cueVideo(uTubeLink); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
        }

        TextView exerciseLabel = (TextView) findViewById(R.id.exercisePageLabel);
        exerciseLabel.setText(exerciseName);

        String descriptionName = exerciseName + "_description";
        int descriptionId = getResources().getIdentifier(descriptionName, "string", YoutubePlayerVideo.this.getPackageName());
        String description = getResources().getString(descriptionId);

        TextView exerciseDescription = (TextView) findViewById(R.id.exercisePageDescription);
        exerciseDescription.setText(description);
    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format(getString(R.string.player_error), errorReason.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.YOUTUBE_API_KEY, this);
        }
    }

    protected Provider getYouTubePlayerProvider() {
        return youTubeView;
    }
}
