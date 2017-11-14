package devapp.com.bakingappudacity;

import android.content.Context;
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

public class RecipeDetailFragment extends Fragment {

    public FragmentCommunicator fragmentCommunicator;

    public RecipeDetailFragment(){
    }

    public static RecipeDetailAdapter recipeDetailAdapter;
    RecyclerView recipeDetailRecyclerView;
    LinearLayoutManager linearLayoutManager;
    ItemClickListener itemClickListener;

    public interface ItemClickListener{
        public void onItemClick(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        itemClickListener = (ItemClickListener) context;

        fragmentCommunicator = (FragmentCommunicator) getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.recipe_detail_fragment,container,false);

        recipeDetailAdapter = new RecipeDetailAdapter(getContext(), new RecipeDetailAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {

                NetworkUtils.STEP_CHOSEN = position;

                // Starts only when app runs on a mobile phone.
                if(RecipeDetailActivity.isMobile){
                    Intent in = new Intent(getContext(),RecipeStepDetailActivity.class);
                    in.putExtra("position",position);
                    startActivity(in);}

                //Starts when app is running in tablet mode.
                else{
                    fragmentCommunicator.sendData(position);
                }

            }
        });

        recipeDetailRecyclerView = (RecyclerView) v.findViewById(R.id.recipe_detail_fragment_recycler_view);

        recipeDetailRecyclerView.setNestedScrollingEnabled(false);

        linearLayoutManager = new LinearLayoutManager(getContext());

        recipeDetailRecyclerView.setAdapter(recipeDetailAdapter);

        recipeDetailRecyclerView.setLayoutManager(linearLayoutManager);

        return v;

    }

}
