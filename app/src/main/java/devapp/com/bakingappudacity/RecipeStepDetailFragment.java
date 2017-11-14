package devapp.com.bakingappudacity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import devapp.com.bakingappudacity.utils.NetworkUtils;


public class RecipeStepDetailFragment extends Fragment {

    SimpleExoPlayerView simpleExoPlayerView;
    Button nextButton;
    Button prevButton;
    SimpleExoPlayer exoPlayer;
    TextView descriptionTextView;

    public boolean isHorizontal = false;
    public long playbackTime = 0;

    void initialize(View v){

        isHorizontal = false;

        simpleExoPlayerView = (SimpleExoPlayerView) v.findViewById(R.id.recipe_step_detail_fragment_exoplayer_view);

        if(v.findViewById(R.id.recipe_step_detail_fragment_description_text_view) != null) {

            isHorizontal = true;

            descriptionTextView = (TextView) v.findViewById(R.id.recipe_step_detail_fragment_description_text_view);
            nextButton = (Button) v.findViewById(R.id.recipe_step_detail_fragment_next_button);
            prevButton = (Button) v.findViewById(R.id.recipe_step_detail_fragment_previous_button);


            setOnClickListeners();
        }

        try {
            prepareExoPlayer();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void prepareExoPlayer(){

        exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), new DefaultTrackSelector(),new DefaultLoadControl());

        simpleExoPlayerView.setPlayer(exoPlayer);

        Uri MediaURI = Uri.parse(NetworkUtils.STEP_VIDEO_URL.get(NetworkUtils.STEP_CHOSEN));

        MediaSource mediaSource = new ExtractorMediaSource(MediaURI,new DefaultDataSourceFactory(getContext(),Util.getUserAgent(getContext(),"BakingAppUdacity")),new DefaultExtractorsFactory(),null,null);

        exoPlayer.prepare(mediaSource);

        exoPlayer.setPlayWhenReady(true);

        if(isHorizontal)
        descriptionTextView.setText(NetworkUtils.STEP_DESCRIPTION.get(NetworkUtils.STEP_CHOSEN));

        Toast.makeText(getContext(), String.valueOf(playbackTime), Toast.LENGTH_SHORT).show();

        exoPlayer.seekTo(playbackTime);

    }

    void setOnClickListeners(){

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(NetworkUtils.STEP_CHOSEN < NetworkUtils.STEP_DESCRIPTION.size() - 1){

                    NetworkUtils.STEP_CHOSEN += 1;

                    playbackTime = 0;

                    releasePlayer();
                    prepareExoPlayer();

                }
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(NetworkUtils.STEP_CHOSEN != 0){

                    NetworkUtils.STEP_CHOSEN -= 1;

                    playbackTime = 0;

                    releasePlayer();
                    prepareExoPlayer();

                }

            }
        });

    }

    public RecipeStepDetailFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        try {
            playbackTime = Long.parseLong(savedInstanceState.getString("playback"));
        }catch (Exception e){
            e.printStackTrace();
        }
        View v = inflater.inflate(R.layout.recipe_step_detail_fragment,container,false);

        initialize(v);

        return v;

    }

    private void releasePlayer() {
        if (exoPlayer != null) {
            exoPlayer.release();
            exoPlayer = null;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        try {
            Toast.makeText(getContext(), String.valueOf(exoPlayer.getCurrentPosition()), Toast.LENGTH_SHORT).show();
            outState.putString("playback", String.valueOf(exoPlayer.getCurrentPosition()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onListFragmentClicked(int position){

        NetworkUtils.STEP_CHOSEN = position;

        playbackTime = 0;

        releasePlayer();
        prepareExoPlayer();

    }

}
