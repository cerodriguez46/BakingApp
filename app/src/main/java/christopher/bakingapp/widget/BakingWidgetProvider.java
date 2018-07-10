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


//This class binds all the widget components together
//The AppWidgetProvider is a special type of BroadcastReceiver that is specifically used to
// send messages to a home screen widget. The AppWidgetProvider class provides several different callback methods,
// one each for the five different broadcasts that a widget would typically receive.

public class BakingWidgetProvider extends AppWidgetProvider {

    public static final String ACTION_VIEW_DETAILS =
            "com.company.android.ACTION_VIEW_DETAILS";
    public static final String EXTRA_ITEM =
            "com.company.android.CollectionWidgetProvider.EXTRA_ITEM";


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

    }


    //called when widget is created and at every update interval

    //do our normal widget setup, but we also bind our service to the widget's view using a service
    // intent and the setRemoteAdapter() method of the RemoteViews class.
    // We also specify which of our widget views is going to be the empty view.
    // Then, we create and set our pending intent template

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

    //we can now listen for that custom broadcast action we assigned in our pending intent template.
    // If we receive an intent of that type, we can do whatever we want with it.
    // If it's not the custom action, be sure to call super so that onUpdate gets called when necessary.
    // Don't forget to register this receiver in the manifest to listen for both the update and custom intent actions.
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

