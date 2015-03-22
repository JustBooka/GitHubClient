package example.user.githubclient.Activity;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

import example.user.githubclient.Models.Repositories;
import example.user.githubclient.R;
import example.user.githubclient.RepoAdapter;
import example.user.githubclient.api.RepositoriesAPI;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

//import example.user.githubclient.RepoAdapter;

/**
 * Created by alexey.bukin on 18.03.2015.
 */
public class MainActivity extends ListActivity {

    List<Repositories> repositoriesList;
    public static final String ENDPOINT = "https://api.github.com";
    public String accessToken;
    Object object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sPref = getApplicationContext().getSharedPreferences("My_PREFERENCE",
                MODE_PRIVATE);
        accessToken = sPref.getString(LoginActivity.ACCESS_TOKEN, "");

        requestData();
    }

    //
    private void updateDisplay() {
        RepoAdapter adapter = new RepoAdapter(this, R.layout.item_repo, repositoriesList);
        setListAdapter(adapter);

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                object = getSelectedItemPosition();
                ItemClick();
            }
        });
    }

    private void ItemClick() {
        Repositories repo = (Repositories) object;
        String repoName = repo.getName();
        String ownerName = repo.getOwner().getLogin();
        Intent intent = new Intent(this, CommitsActivity.class);
        intent.putExtra("repoName", String.valueOf(repoName));
        intent.putExtra("ownerName", String.valueOf(ownerName));
        startActivity(intent);
    }



    // --> add for retrofit
    public void requestData() {

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .build();

        RepositoriesAPI api = adapter.create(RepositoriesAPI.class);
        api.getRepos(accessToken, new Callback<List<Repositories>>() {
            @Override
            public void success(List<Repositories> repositorieses, Response response) {
                repositoriesList = repositorieses;
                updateDisplay();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
// <--
    }
}
