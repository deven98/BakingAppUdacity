package devapp.com.bakingappudacity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import devapp.com.bakingappudacity.utils.NetworkUtils;


public class RecipeStepDetailFragment extends Fragment {

    RecyclerView recyclerView;
    RecipeStepDetailAdapter recipeStepDetailAdapter;
    SimpleExoPlayerView simpleExoPlayerView;
    LinearLayoutManager linearLayoutManager;
    Button nextButton;
    Button prevButton;
    SimpleExoPlayer exoPlayer;

    void initialize(View v){

        recyclerView = (RecyclerView) v.findViewById(R.id.recipe_step_detail_fragment_recycler_view);
        recipeStepDetailAdapter = new RecipeStepDetailAdapter(getContext());
        simpleExoPlayerView = (SimpleExoPlayerView) v.findViewById(R.id.recipe_step_detail_fragment_exoplayer_view);
        linearLayoutManager = new LinearLayoutManager(getContext());
        nextButton = (Button) v.findViewById(R.id.recipe_step_detail_fragment_next_button);
        prevButton = (Button) v.findViewById(R.id.recipe_step_detail_fragment_previous_button);

        exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), new DefaultTrackSelector(),new DefaultLoadControl());

        simpleExoPlayerView.setPlayer(exoPlayer);

        Uri MediaURI = Uri.parse(NetworkUtils.STEP_VIDEO_URL.get(NetworkUtils.STEP_CHOSEN));

        MediaSource mediaSource = new ExtractorMediaSource(MediaURI,new DefaultDataSourceFactory(getContext(),Util.getUserAgent(getContext(),"BakingAppUdacity")),new DefaultExtractorsFactory(),null,null);

        exoPlayer.prepare(mediaSource);

        exoPlayer.setPlayWhenReady(true);

    }

    public RecipeStepDetailFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.recipe_step_detail_fragment,container,false);

        initialize(v);

        return v;

    }
}
