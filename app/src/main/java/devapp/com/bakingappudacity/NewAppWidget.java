package devapp.com.bakingappudacity;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import devapp.com.bakingappudacity.utils.WidgetAdapterService;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

        Intent intent = new Intent(context, WidgetAdapterService.class);

        views.setRemoteAdapter(R.id.widget_list_view,intent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {

                // Create an Intent to launch ExampleActivity
                //Intent intent = new Intent(context, MainActivity.class);
                //PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

                // Get the layout for the App Widget and attach an on-click listener
                // to the button
                //RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
                //Intent intent = new Intent(context, WidgetAdapterService.class);
                //views.setRemoteAdapter(R.id.widget_list_view,intent);

                //Intent intent = new Intent(context, WidgetAdapterService.class);

                //views.setRemoteAdapter(R.id.widget_list_view,intent);

                updateAppWidget(context, appWidgetManager, appWidgetId);
                // Tell the AppWidgetManager to perform an update on the current app widget
                //appWidgetManager.updateAppWidget(appWidgetId, views);

        }

        super.onUpdate(context,appWidgetManager,appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created

        //AppWidgetManager appManager = AppWidgetManager.getInstance(context);
        //ComponentName thisWidget = new ComponentName(context, NewAppWidget.class);

        //updateAppWidget(context,appManager,appManager.getAppWidgetIds(thisWidget));

    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

