package example.user.githubclient.Activity;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import java.util.List;

import example.user.githubclient.Models.Repo;
import example.user.githubclient.R;
//import example.user.githubclient.RepoAdapter;
import example.user.githubclient.RepositoriesAPI;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by alexey.bukin on 18.03.2015.
 */
public class MainActivity extends ListActivity {

    List<Repo> repoList;
    public static final String ENDPOINT = "https://api.github.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    private void updateDisplay() {
//        RepoAdapter adapter = new RepoAdapter(this, R.layout.item_repo, repoList);
//        setListAdapter(adapter);
//    }


    // --> add for retrofit
    public void requestData(String uri){

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .build();

        RepositoriesAPI api = adapter.create(RepositoriesAPI.class);
        api.getRepos(new Callback<List<Repo>>() {

            @Override
            public void success(List<Repo> repos, Response response) {
                repoList = repos;
//                updateDisplay();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
// <--
}
