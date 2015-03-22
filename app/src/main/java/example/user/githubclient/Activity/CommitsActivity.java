package example.user.githubclient.Activity;

import android.app.ListActivity;
import android.os.Bundle;

import example.user.githubclient.R;

/**
 * Created by User on 23.03.2015.
 */
public class CommitsActivity extends ListActivity {
    public String ownerName;
    public String repoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commits);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ownerName = extras.getString("ownerName");
            repoName = extras.getString("repoName");
        }
    }
}
