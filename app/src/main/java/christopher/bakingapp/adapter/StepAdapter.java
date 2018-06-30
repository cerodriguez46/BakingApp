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
import christopher.bakingapp.retrofit.StepModel;

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
        int layoutIdForListItem = R.layout.list_item_ingredients;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        StepViewHolder viewHolder = new StepViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StepAdapter.StepViewHolder holder, int position) {

        holder.step.setText(stepList.get(position).getShortDesc());

    }

    @Override
    public int getItemCount() {
        return 0;
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
                    Toast.makeText(mContext, "You clicked it!", Toast.LENGTH_SHORT).show();

                }
            });
        }

    }
}
