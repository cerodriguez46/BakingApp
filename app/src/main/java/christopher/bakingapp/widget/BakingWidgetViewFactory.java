package christopher.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;

import christopher.bakingapp.R;
import christopher.bakingapp.retrofit.RecipeModel;

public class BakingWidgetViewFactory implements RemoteViewsService.RemoteViewsFactory {

    private static final int ID_CONSTANT = 0x0101010;

    private ArrayList<RecipeModel> mRecipes;
    private Context mContext;

    public BakingWidgetViewFactory(Context context) {
        mContext = context;
        mRecipes = new ArrayList<RecipeModel>();
    }

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

    @Override
    public int getCount() {
        return mRecipes.size();
    }

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

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return ID_CONSTANT + i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
