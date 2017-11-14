package devapp.com.bakingappudacity;

import android.annotation.SuppressLint;

import android.content.AsyncTaskLoader;
import android.content.Intent;
import android.content.Loader;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import devapp.com.bakingappudacity.utils.NetworkUtils;

public class RecipeDetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Void>, RecipeDetailFragment.ItemClickListener, FragmentCommunicator{

    int positionOfRecipe;

    public static final int RECIPE_DETAIL_LOADER_ID = 102;

    RecipeStepDetailFragment detailActivityFragment;

    public static boolean isMobile = true;

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(findViewById(R.id.recipe_step_detail_fragment) != null){
            isMobile = false;
        }

        Intent in = getIntent();

        if(in.hasExtra("position")){
            positionOfRecipe = in.getIntExtra("position",0);
        }

        getSupportLoaderManager().initLoader(RECIPE_DETAIL_LOADER_ID,null,this);

        detailActivityFragment = (RecipeStepDetailFragment) getSupportFragmentManager().findFragmentById(R.id.recipe_step_detail_fragment);

    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public android.support.v4.content.Loader<Void> onCreateLoader(int id, Bundle args) {

        return new android.support.v4.content.AsyncTaskLoader<Void>(this) {

            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                forceLoad();
            }

            @Override
            public Void loadInBackground() {

                NetworkUtils.clearRecipeDetails();
                NetworkUtils.getRecipeDetails(positionOfRecipe);

                return null;
            }
        };

    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<Void> loader, Void data) {

    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<Void> loader) {

    }

    @Override
    public void onItemClick(int position) {



    }

    //Communicates with other fragment
    @Override
    public void sendData(int position) {

        if(detailActivityFragment != null)
        detailActivityFragment.onListFragmentClicked(position);

    }

}
