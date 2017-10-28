package devapp.com.bakingappudacity;

import android.annotation.SuppressLint;

import android.content.AsyncTaskLoader;
import android.content.Intent;
import android.content.Loader;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import devapp.com.bakingappudacity.utils.NetworkUtils;

public class RecipeDetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Void>{

    int positionOfRecipe;

    public static final int RECIPE_DETAIL_LOADER_ID = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Intent in = getIntent();

        if(in.hasExtra("position")){
            positionOfRecipe = in.getIntExtra("position",0);
        }

        getSupportLoaderManager().initLoader(RECIPE_DETAIL_LOADER_ID,null,this);

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

}
