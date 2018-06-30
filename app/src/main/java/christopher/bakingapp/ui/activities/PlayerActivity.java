package christopher.bakingapp.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import christopher.bakingapp.R;
import christopher.bakingapp.ui.fragments.PlayerFragment;

public class PlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_main);

        PlayerFragment playerFragment = new PlayerFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.steps_container, playerFragment)
                .commit();
    }
}
