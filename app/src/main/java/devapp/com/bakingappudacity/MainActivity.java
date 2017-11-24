package devapp.com.bakingappudacity;

import android.annotation.SuppressLint;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import devapp.com.bakingappudacity.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Void>{

    public static int FRAGMENT_LIST_LOADER_ID = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportLoaderManager().initLoader(FRAGMENT_LIST_LOADER_ID,null,this);

    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public Loader<Void> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<Void>(this) {

            @Override
            protected void onStartLoading() {
                super.onStartLoading();

                if(NetworkUtils.result.matches(""))
                forceLoad();
            }

            @Override
            public Void loadInBackground() {

                NetworkUtils.getRecipesNames();

                return null;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Void> loader, Void data) {

        RecipeListFragment.recipeListAdapter.notifyDataSetChanged();
        Toast.makeText(this, String.valueOf(NetworkUtils.RECIPE_IMAGE_LINKS.size()), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLoaderReset(Loader<Void> loader) {

    }
}
