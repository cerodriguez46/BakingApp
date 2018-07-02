package christopher.bakingapp.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import christopher.bakingapp.R;
import christopher.bakingapp.retrofit.IngredientModel;
import christopher.bakingapp.retrofit.StepModel;
import christopher.bakingapp.ui.fragments.MasterStepFragment;
import christopher.bakingapp.ui.fragments.PlayerFragment;

public class StepActivity extends AppCompatActivity {

    String recipeName;

    Bundle recipeDetailsBundle;
    private ArrayList<StepModel> stepList;
    private ArrayList<IngredientModel> ingredientList;

    private boolean mTwoPane;


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


        if (findViewById(R.id.two_pane) != null) {
            mTwoPane = true;

            if (savedInstanceState == null) {


                FragmentManager fragmentManager = getSupportFragmentManager();

                PlayerFragment playerFragment = new PlayerFragment();

                fragmentManager.beginTransaction()
                        .add(R.id.player_container, playerFragment)
                        .commit();
            } else {
                mTwoPane = false;


            }

        }



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

