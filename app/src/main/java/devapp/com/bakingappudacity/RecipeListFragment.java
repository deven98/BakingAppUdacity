package devapp.com.bakingappudacity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import devapp.com.bakingappudacity.utils.NetworkUtils;


public class RecipeListFragment extends Fragment {

    public RecipeListFragment(){

    }

    public static RecipeListAdapter recipeListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.recipe_list_fragment,container,false);

        RecyclerView recipeListRecyclerView = (RecyclerView) v.findViewById(R.id.recipe_list_recycler_view);

        recipeListAdapter = new RecipeListAdapter(getContext(), new RecipeListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {

                Intent in = new Intent(getContext(),RecipeDetailActivity.class);
                in.putExtra("position",position);
                startActivity(in);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        recipeListRecyclerView.setAdapter(recipeListAdapter);

        recipeListRecyclerView.setLayoutManager(layoutManager);

        return v;

    }
}
