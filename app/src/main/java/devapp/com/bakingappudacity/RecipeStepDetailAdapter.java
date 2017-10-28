package devapp.com.bakingappudacity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import devapp.com.bakingappudacity.utils.NetworkUtils;

/**
 * Created by HP on 26-10-2017.
 */

public class RecipeStepDetailAdapter extends RecyclerView.Adapter<RecipeStepDetailAdapter.RecipeStepDetailHolder> {

    //private ItemClickListener itemClickListener;
    private Context mContext;

    public RecipeStepDetailAdapter(Context mContext){

        this.mContext = mContext;
        //this.itemClickListener = itemClickListener;

    }

    interface ItemClickListener{

        public void onItemClick(int position);

    }

    @Override
    public RecipeStepDetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecipeStepDetailHolder(LayoutInflater.from(mContext).inflate(R.layout.recipe_step_detail_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecipeStepDetailHolder holder, int position) {

        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return NetworkUtils.STEP_DESCRIPTION.size();
    }

    public class RecipeStepDetailHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView detailTextView;

        public RecipeStepDetailHolder(View itemView) {
            super(itemView);

            detailTextView = (TextView) itemView.findViewById(R.id.recipe_step_detail_item_text_view);

            itemView.setOnClickListener(this);
        }

        void bind(int position){

            detailTextView.setText(NetworkUtils.STEP_DESCRIPTION.get(position));

        }

        @Override
        public void onClick(View v) {

            //itemClickListener.onItemClick(getAdapterPosition());

        }
    }

}
