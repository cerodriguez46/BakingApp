package christopher.bakingapp.ui;

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
import christopher.bakingapp.retrofit.Client;
import christopher.bakingapp.retrofit.RecipeModel;
import christopher.bakingapp.retrofit.Service;
import retrofit2.Call;
import retrofit2.Callback;


public class RecipeFragment extends Fragment {

    private RecipeAdapter mAdapter;

    private ArrayList<RecipeModel> recipeList = new ArrayList<>();

    private int recyclerViewState;

    public RecipeFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_recipe, container, false);
      final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_recipes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new RecipeAdapter(getActivity(), recipeList);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        Client client = new Client();

        Service service = client.getClient().create(Service.class);

        Call<ArrayList<RecipeModel>> call = service.getRecipes();

        call.enqueue(new Callback<ArrayList<RecipeModel>>() {
            @Override
            public void onResponse(Call<ArrayList<RecipeModel>> call, retrofit2.Response<ArrayList<RecipeModel>> response) {
                recipeList = response.body();


                recyclerView.setAdapter(new RecipeAdapter(getActivity(), recipeList));
                recyclerView.scrollToPosition(recyclerViewState);

                Toast.makeText(getActivity(), "It works!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ArrayList<RecipeModel>> call, Throwable t) {
                Toast.makeText(getActivity(), "It didn't work! :(", Toast.LENGTH_LONG).show();
            }
        });

return rootView;
    }
}
