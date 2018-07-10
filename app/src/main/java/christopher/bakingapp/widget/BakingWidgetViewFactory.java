package christopher.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;

import christopher.bakingapp.R;
import christopher.bakingapp.retrofit.RecipeModel;

//Remote Views Factory is a special adapter like class for widget

public class BakingWidgetViewFactory implements RemoteViewsService.RemoteViewsFactory {

    private static final int ID_CONSTANT = 0x0101010;

    private ArrayList<RecipeModel> mRecipes;
    private Context mContext;

    public BakingWidgetViewFactory(Context context) {
        mContext = context;
        mRecipes = new ArrayList<RecipeModel>();
    }


    //The onCreate() method is called when the factory is first created.
    // This is where you would setup any initial data to show in the collection.
    // This method can handle some minor heavy lifting but, if you need to load a lot of data from the network,
    // then do that in the onDataSetChanged() method.

    @Override
    public void onCreate() {


    }


    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {
        mRecipes.clear();
    }

    //returns number of views in the collection widget
    //This works exactly the same as getCount() in an adapter and should be used to return the number of items in the collection.
    @Override
    public int getCount() {
        if (mRecipes != null) {
            return mRecipes.size();
        } else {
            return 0;
        }
    }

    // similar to getView method in an adapter, implements remote views instead of a regular view
    //getViewAt() method that works like getView() in an adapter. in this method,
    // we would create a new RemoteViews object that represents our row item,
    // setup any data that needs to be setup, and return the remote view. We can also setup our fill-in
    // intent here and attach it to the returned view.
    @Override
    public RemoteViews getViewAt(int i) {
        RecipeModel recipe = mRecipes.get(i);

        RemoteViews itemView = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);

        itemView.setTextViewText(R.id.title, recipe.getRecipeName());


        Intent intent = new Intent();
        intent.putExtra(BakingWidgetProvider.EXTRA_ITEM, recipe);
        itemView.setOnClickFillInIntent(R.id.item, intent);

        return itemView;

    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    // The getViewTypeCount() method returns the number of different view types used in the collection.
    // We'll only be using one view type, so make this method return 1.
    @Override
    public int getViewTypeCount() {
        return 1;
    }

    //
    @Override
    public long getItemId(int i) {
        return ID_CONSTANT + i;
    }

    //Also, all of our row items are going to have stable IDs, so make hasStableIds() return true.
    @Override
    public boolean hasStableIds() {
        return true;
    }
}
