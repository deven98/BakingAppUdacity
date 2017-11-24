package devapp.com.bakingappudacity;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import devapp.com.bakingappudacity.utils.NetworkUtils;


public class RecipeDetailAdapter extends RecyclerView.Adapter<RecipeDetailAdapter.RecipeDetailHolder>{

    private Context mContext;
    private ItemClickListener itemClickListener;


    public RecipeDetailAdapter(Context mContext, ItemClickListener itemClickListener){

        this.mContext = mContext;
        this.itemClickListener = itemClickListener;

    }

    interface ItemClickListener{
        public void onItemClick(int position);
    }

    @Override
    public RecipeDetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecipeDetailHolder(LayoutInflater.from(mContext).inflate(R.layout.recipe_detail_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecipeDetailHolder holder, int position) {

        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return NetworkUtils.STEP_SHORT_DESCRIPTION.size();
    }

    public class RecipeDetailHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView shortDescription;
        ImageView thumbnail;

        public RecipeDetailHolder(View itemView) {

            super(itemView);

            shortDescription = (TextView) itemView.findViewById(R.id.recipe_detail_item_short_description_text_view);

            thumbnail = (ImageView) itemView.findViewById(R.id.recipe_detail_list_item_image_view);

            itemView.setOnClickListener(this);
        }

        public void bind(int position){

            String shortDescriptionText = "Step: " + String.valueOf(position+1) + "\n" + NetworkUtils.STEP_SHORT_DESCRIPTION.get(position);

            shortDescription.setText(shortDescriptionText);

            try {
                Picasso.with(mContext).load(NetworkUtils.STEP_THUMBNAIL_URL.get(position)).error(R.drawable.baking).into(thumbnail);
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
