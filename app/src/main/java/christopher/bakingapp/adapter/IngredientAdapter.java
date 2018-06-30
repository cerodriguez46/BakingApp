package christopher.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import christopher.bakingapp.R;
import christopher.bakingapp.retrofit.IngredientModel;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private Context mContext;


    private ArrayList<IngredientModel> ingredientList = new ArrayList<>();

    public IngredientAdapter(Context mContext, ArrayList<IngredientModel> ingredientList) {

        this.mContext = mContext;
        this.ingredientList = ingredientList;

    }

    @Override
    public IngredientAdapter.IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.list_item_ingredients;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        IngredientViewHolder viewHolder = new IngredientViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(IngredientAdapter.IngredientViewHolder holder, int position) {
        IngredientModel selectedRecipe = ingredientList.get(position);
        holder.ingredient.setText(selectedRecipe.getIngredients());
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder {


        TextView ingredient;


        public IngredientViewHolder(View itemView) {
            super(itemView);

            ingredient = (TextView) itemView.findViewById(R.id.tv_ingredient);


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
