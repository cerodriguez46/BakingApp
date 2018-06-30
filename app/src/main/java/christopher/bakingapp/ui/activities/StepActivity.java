package christopher.bakingapp.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import christopher.bakingapp.R;

public class StepActivity extends AppCompatActivity {

    String recipeName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_master_main);

        Bundle recipeDetailsBundle = getIntent().getBundleExtra("recipeBundle");
        recipeName = recipeDetailsBundle.getString("recipeNames");
        getSupportActionBar().setTitle(recipeName);

    }
}
