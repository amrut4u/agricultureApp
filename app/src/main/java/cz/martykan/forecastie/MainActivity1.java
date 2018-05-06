package cz.martykan.forecastie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;




public class MainActivity1 extends YouTubeBaseActivity {

    Button mbutton;
    private YouTubePlayerView myouTubePlayerView;
    private YouTubePlayer.OnInitializedListener monInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        myouTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player_view);
        monInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("cMPKStzf83o");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        mbutton = (Button) findViewById(R.id.button_play_youtube_video);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myouTubePlayerView.initialize("AIzaSyADbfzmFSTZqE8RoOAcvpgKqTS9tblD0MM", monInitializedListener);
            }
        });
    }
}
