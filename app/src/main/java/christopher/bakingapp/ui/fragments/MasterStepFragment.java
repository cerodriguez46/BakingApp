package christopher.bakingapp.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import christopher.bakingapp.R;
import christopher.bakingapp.adapter.IngredientAdapter;
import christopher.bakingapp.adapter.StepAdapter;
import christopher.bakingapp.retrofit.IngredientModel;
import christopher.bakingapp.retrofit.RecipeModel;
import christopher.bakingapp.retrofit.StepModel;

public class MasterStepFragment extends Fragment {

    private StepAdapter stepAdapter;
    private IngredientAdapter ingredientAdapter;

    private ArrayList<StepModel> stepList;
    private ArrayList<IngredientModel> ingredientList;

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

        recyclerViewSteps.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewIngredients.setLayoutManager(new LinearLayoutManager(getActivity()));


        recyclerViewSteps.setItemAnimator(new DefaultItemAnimator());
        recyclerViewIngredients.setItemAnimator(new DefaultItemAnimator());


        Bundle recipeDetailsBundle = getArguments();
        ingredientList = recipeDetailsBundle.getParcelableArrayList("ingredients");
        stepList = recipeDetailsBundle.getParcelableArrayList("steps");

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


        recyclerViewSteps.setAdapter(new StepAdapter(getActivity(), stepList));
        stepAdapter.notifyDataSetChanged();
        recyclerViewSteps.scrollToPosition(recyclerViewStepState);


        Toast.makeText(getActivity(), "Successfully loaded", Toast.LENGTH_SHORT).show();


    }

    public void loadIngredients() {


        recyclerViewIngredients.setAdapter(new IngredientAdapter(getActivity(), ingredientList));
        ingredientAdapter.notifyDataSetChanged();
        recyclerViewIngredients.scrollToPosition(recyclerViewStepState);


    }
}
