package christopher.bakingapp.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import christopher.bakingapp.R;
import christopher.bakingapp.adapter.IngredientAdapter;
import christopher.bakingapp.adapter.StepAdapter;
import christopher.bakingapp.retrofit.IngredientModel;
import christopher.bakingapp.retrofit.RecipeModel;
import christopher.bakingapp.retrofit.StepModel;
import christopher.bakingapp.ui.activities.StepActivity;

public class MasterStepFragment extends Fragment {

    private StepAdapter stepAdapter;
    private IngredientAdapter ingredientAdapter;

    private List<StepModel> stepList;
    private List<IngredientModel> ingredientList;

    RecyclerView recyclerViewSteps;
    RecyclerView recyclerViewIngredients;

    RecipeModel recipe;
    IngredientModel ingredients;
    StepModel steps;

    String recipeIngredients;


    private int recyclerViewStepState;
    private int recyclerViewIngredientState;

    public MasterStepFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.steps_master_list, container, false);
        recyclerViewSteps = (RecyclerView) rootView.findViewById(R.id.rv_steps);
        recyclerViewIngredients = (RecyclerView) rootView.findViewById(R.id.rv_ingredients);

        recyclerViewSteps.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerViewIngredients.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


        recyclerViewSteps.setItemAnimator(new DefaultItemAnimator());
        recyclerViewIngredients.setItemAnimator(new DefaultItemAnimator());


        ingredientList = ((StepActivity) getActivity()).getIngredientsList();
        stepList = ((StepActivity) getActivity()).getStepList();

        // Intent intentFromMainActivity = getActivity().getIntent();

       /* recipe = getActivity().getIntent().getParcelableExtra("recipeParcel");
        ingredients = getActivity().getIntent().getParcelableExtra("ingredientParcel");
        steps = getActivity().getIntent().getParcelableExtra("stepParcel");

       recipeIngredients = ingredients.getIngredients();*/


        loadIngredients();
        loadSteps();


        return rootView;
    }

    public void loadSteps() {

        stepAdapter = new StepAdapter(getActivity(), (ArrayList<StepModel>) stepList);
        recyclerViewSteps.setAdapter(stepAdapter);
        stepAdapter.notifyDataSetChanged();
        recyclerViewSteps.scrollToPosition(recyclerViewStepState);





    }

    public void loadIngredients() {

        ingredientAdapter = new IngredientAdapter(getActivity(), (ArrayList<IngredientModel>) ingredientList);
        recyclerViewIngredients.setAdapter(ingredientAdapter);
        ingredientAdapter.notifyDataSetChanged();
        recyclerViewIngredients.scrollToPosition(recyclerViewStepState);


    }
}