package example.user.githubclient.Activity;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;

import java.util.List;

import example.user.githubclient.Models.Repo;
import example.user.githubclient.R;
import example.user.githubclient.RepositoriesAPI;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by alexey.bukin on 18.03.2015.
 */
public class MainActivity extends ListActivity {

    public static final String ENDPOINT = "https://api.github.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void requestData(String uri){

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .build();

        RepositoriesAPI api = adapter.create(RepositoriesAPI.class);
        api.getRepos(new Callback<List<Repo>>() {

            @Override
            public void success(List<Repo> repos, Response response) {
//                repoList = arg0;
//                updateDisplay();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
