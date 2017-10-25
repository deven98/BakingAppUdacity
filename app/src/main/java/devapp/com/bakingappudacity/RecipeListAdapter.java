package devapp.com.bakingappudacity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import devapp.com.bakingappudacity.utils.NetworkUtils;

/**
 * Created by HP on 25-10-2017.
 */

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {

    private Context mContext;

    public RecipeListAdapter(Context mContext){

        this.mContext = mContext;

    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecipeViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recipe_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {

        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return NetworkUtils.RECIPE_NAMES.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder{

        TextView recipeName;

        public RecipeViewHolder(View itemView) {

            super(itemView);

            recipeName = (TextView) itemView.findViewById(R.id.recipe_list_item_text_view);

        }

        void bind(int position){

            recipeName.setText(NetworkUtils.RECIPE_NAMES.get(position));

        }

    }

}
