package example.user.githubclient.Activity;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.List;

import example.user.githubclient.CommitsAdapter;
import example.user.githubclient.Models.Commits;
import example.user.githubclient.R;
import example.user.githubclient.api.CommitsAPI;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by User on 23.03.2015.
 */
public class CommitsActivity extends ListActivity {
    public String ownerName;
    public String repoName;
    public static final String ENDPOINT = "https://api.github.com/repos";
    List<Commits> commitsList;
    public String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commits);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ownerName = extras.getString("ownerName");
            repoName = extras.getString("repoName");
        }

        SharedPreferences sPref = getApplicationContext().getSharedPreferences("My_PREFERENCE",
                MODE_PRIVATE);
        accessToken = sPref.getString(LoginActivity.ACCESS_TOKEN, "");

        requestData();
    }

    private void updateDisplay() {
        final CommitsAdapter adapter = new CommitsAdapter(this, R.layout.item_commit, commitsList);
        setListAdapter(adapter);
    }

    public void requestData() {

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .build();

        CommitsAPI api = adapter.create(CommitsAPI.class);
        api.getCommits(ownerName, repoName, accessToken, new Callback<List<Commits>>() {

            @Override
            public void success(List<Commits> commitses, Response response) {
                commitsList = commitses;
                updateDisplay();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
