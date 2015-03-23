package example.user.githubclient.Activity;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.List;

import example.user.githubclient.Models.Repositories;
import example.user.githubclient.R;
import example.user.githubclient.RepoAdapter;
import example.user.githubclient.api.RepositoriesAPI;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


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
        if (isOnline()) {
            requestData();
        } else {
            Toast.makeText(this, "Network isn`t available", Toast.LENGTH_LONG).show();
        }
    }

    private void updateDisplay() {
        final RepoAdapter adapter = new RepoAdapter(this, R.layout.item_repo, repositoriesList);
        setListAdapter(adapter);

        getListView().setAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                object = adapter.getItem(position);
                ItemClick();
            }
        });
    }

    private void ItemClick() {
        Repositories repo = (Repositories) object;
        String repoName = String.valueOf(repo.getName());
        String ownerName = String.valueOf(repo.getOwner().getLogin());
        Intent intent = new Intent(this, CommitsActivity.class);
        intent.putExtra("repoName", String.valueOf(repoName));
        intent.putExtra("ownerName", String.valueOf(ownerName));
        startActivity(intent);
    }


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
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
}
