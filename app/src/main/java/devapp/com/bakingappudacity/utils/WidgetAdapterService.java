package devapp.com.bakingappudacity.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.widget.Toast;

import devapp.com.bakingappudacity.R;

public class WidgetAdapterService extends RemoteViewsService{

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetAdapter(this.getApplicationContext());
    }
}

class WidgetAdapter implements RemoteViewsService.RemoteViewsFactory {

    private Context context;

    WidgetAdapter(Context mContext){

        context = mContext;

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return NetworkUtils.RECIPE_NAMES.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews item = new RemoteViews(context.getPackageName(), R.layout.widget_list_item);
        item.setTextViewText(R.id.widget_list_view_item_text_view,NetworkUtils.RECIPE_NAMES.get(position));

        Bundle extras = new Bundle();
        extras.putInt("position", position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);
        // Make it possible to distinguish the individual on-click
        // action of a given item
        item.setOnClickFillInIntent(R.id.widget_item_frame, fillInIntent);

        return item;
    }

    @Override
    public RemoteViews getLoadingView() {
        return new RemoteViews(context.getPackageName(),R.layout.widget_list_item);
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

}
