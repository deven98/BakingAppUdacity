package devapp.com.bakingappudacity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import devapp.com.bakingappudacity.utils.NetworkUtils;

/**
 * Created by HP on 25-10-2017.
 */

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {

    private Context mContext;

    ItemClickListener itemClickListener;

    public RecipeListAdapter(Context mContext, ItemClickListener itemClickListener){

        this.mContext = mContext;
        this.itemClickListener = itemClickListener;

    }

    interface ItemClickListener{

        public void onItemClick(int position);

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

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView recipeName;
        ImageView thumbnail;

        public RecipeViewHolder(View itemView) {

            super(itemView);

            recipeName = (TextView) itemView.findViewById(R.id.recipe_list_item_text_view);
            thumbnail = (ImageView) itemView.findViewById(R.id.recipe_list_item_image_view);

            itemView.setOnClickListener(this);

        }

        void bind(int position){

            recipeName.setText(NetworkUtils.RECIPE_NAMES.get(position));
            try {
                Picasso.with(mContext).load(NetworkUtils.RECIPE_IMAGE_LINKS.get(position)).error(R.drawable.baking).into(thumbnail);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void onClick(View v) {

            itemClickListener.onItemClick(getAdapterPosition());

        }
    }

}
