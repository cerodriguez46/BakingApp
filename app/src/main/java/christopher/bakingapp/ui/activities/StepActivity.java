package christopher.bakingapp.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import christopher.bakingapp.R;
import christopher.bakingapp.retrofit.IngredientModel;
import christopher.bakingapp.retrofit.StepModel;
import christopher.bakingapp.ui.fragments.MasterStepFragment;

public class StepActivity extends AppCompatActivity {

    String recipeName;

    Bundle recipeDetailsBundle;
    private ArrayList<StepModel> stepList;
    private ArrayList<IngredientModel> ingredientList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_master_main);
        recipeDetailsBundle = getIntent().getBundleExtra("recipeBundle");

        recipeName = recipeDetailsBundle.getString("recipeNames");

        ingredientList = recipeDetailsBundle.getParcelableArrayList("ingredients");
        stepList = recipeDetailsBundle.getParcelableArrayList("steps");

        getSupportFragmentManager().beginTransaction().replace(R.id.master_list_fragment, new MasterStepFragment()).commit();
        getSupportActionBar().setTitle(recipeName);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public List<StepModel> getStepList() {
        return stepList;
    }

    public List<IngredientModel> getIngredientsList() {
        return ingredientList;
    }
}

