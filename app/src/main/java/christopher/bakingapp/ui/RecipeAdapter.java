package christopher.bakingapp.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import christopher.bakingapp.R;
import christopher.bakingapp.retrofit.RecipeModel;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private Context mContext;


    private ArrayList<RecipeModel> recipeList = new ArrayList<>();

    public RecipeAdapter(Context mContext, ArrayList<RecipeModel> recipeList) {

        this.mContext = mContext;
        this.recipeList = recipeList;
    }

    @Override
    public RecipeAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.list_item_recipes;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        RecipeViewHolder viewHolder = new RecipeViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeAdapter.RecipeViewHolder holder, int position) {
      RecipeModel selectedRecipe = recipeList.get(position);
      holder.example.setText(selectedRecipe.getRecipeName());
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {


        TextView example;



        public RecipeViewHolder(View itemView) {
            super(itemView);

            example = (TextView) itemView.findViewById(R.id.tv_recipe);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int clickedPosition = getAdapterPosition();
                    Toast.makeText(mContext, "You clicked it!", Toast.LENGTH_SHORT).show();

                }
            });
        }

    }


}
