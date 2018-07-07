package christopher.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import christopher.bakingapp.R;
import christopher.bakingapp.retrofit.RecipeModel;

/**
 * Implementation of App Widget functionality.
 */
public class BakingWidgetProvider extends AppWidgetProvider {

    public static final String ACTION_VIEW_DETAILS =
            "com.company.android.ACTION_VIEW_DETAILS";
    public static final String EXTRA_ITEM =
            "com.company.android.CollectionWidgetProvider.EXTRA_ITEM";


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {


    }

    //called when widget is created and at every update interval
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int i = 0; i < appWidgetIds.length; i++) {

            int widgetId = appWidgetIds[i];

            Intent intent = new Intent(context, BakingWidgetService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);

            RemoteViews widgetView = new RemoteViews(context.getPackageName(), R.layout.baking_widget_provider);
            widgetView.setRemoteAdapter(R.id.list_view, intent);
            widgetView.setEmptyView(R.id.list_view, R.id.empty_view);

            Intent detailIntent = new Intent(ACTION_VIEW_DETAILS);
            PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, detailIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            widgetView.setPendingIntentTemplate(R.id.list_view, pIntent);

            appWidgetManager.updateAppWidget(widgetId, widgetView);
        }

        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(ACTION_VIEW_DETAILS)) {
            RecipeModel recipes = (RecipeModel) intent.getSerializableExtra(EXTRA_ITEM);
            if (recipes != null) {
                // Handle the click here.
                // Maybe start a details activity?
                // Maybe consider using an Activity PendingIntent instead of a Broadcast?
            }
        }

        super.onReceive(context, intent);
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

