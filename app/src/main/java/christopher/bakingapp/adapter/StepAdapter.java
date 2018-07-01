package christopher.bakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import christopher.bakingapp.R;
import christopher.bakingapp.retrofit.StepModel;
import christopher.bakingapp.ui.activities.PlayerActivity;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {

    private Context mContext;


    private ArrayList<StepModel> stepList = new ArrayList<>();

    public StepAdapter(Context mContext, ArrayList<StepModel> stepList) {

        this.mContext = mContext;
        this.stepList = stepList;

    }

    @Override
    public StepAdapter.StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.list_item_steps;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        StepViewHolder viewHolder = new StepViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StepAdapter.StepViewHolder holder, int position) {

        StepModel selectedRecipe = stepList.get(position);
        holder.step.setText(selectedRecipe.getShortDesc());

    }

    @Override
    public int getItemCount() {
        return stepList.size();
    }

    class StepViewHolder extends RecyclerView.ViewHolder {

        TextView step;


        public StepViewHolder(View itemView) {
            super(itemView);

            step = (TextView) itemView.findViewById(R.id.tv_steps);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int clickedPosition = getAdapterPosition();
                    Intent intent = new Intent(mContext, PlayerActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("stepsDetails", stepList.get(clickedPosition).getDescription());
                    bundle.putString("stepsVideo", stepList.get(clickedPosition).getVidUrl());
                    intent.putExtra("stepDetailBundle", bundle);
//intent.putExtra("recipeParcel", clickedPosition);
//intent.putExtra("ingredientParcel", clickedPosition);
//intent.putExtra("stepParcel", clickedPosition);
                    mContext.startActivity(intent);

                }
            });
        }

    }
}
